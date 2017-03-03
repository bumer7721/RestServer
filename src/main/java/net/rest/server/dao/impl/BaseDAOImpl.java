package net.rest.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import net.rest.server.dao.BaseDAO;
import net.rest.server.domains.IdDomain;

public abstract class BaseDAOImpl<E extends IdDomain> implements BaseDAO<E> {
	
	private Class<E> clazz;

	@PersistenceContext
	protected EntityManager entityManager;
	
	protected final void setClazz(Class<E> clazz) {
		this.clazz = clazz;
	}
	
	@Override
	public E create(E entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	public E update(E entity) {
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	public void delete(E entity) {
		entityManager.remove(entity);
	}
	
	@Override
	public void deleteById(Long id) {
		E entity = findOne(id);
		delete(entity);
	}
	
	@Override
	public E findOne(Long id) {
		return entityManager.find(clazz, id);
	}
	
	@Override
	public List<E> findAll() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<E> query = cb.createQuery(clazz);
		Root<E> root = query.from(clazz);
		query.select(root);
		return entityManager.createQuery(query).getResultList();
	}
}
