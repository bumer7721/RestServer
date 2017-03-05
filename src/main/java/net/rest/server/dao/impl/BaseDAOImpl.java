package net.rest.server.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public E create(E entity) {
		entityManager.persist(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public E update(E entity) {
		entityManager.merge(entity);
		return entity;
	}
	
	@Override
	@Transactional
	public void delete(E entity) {
		deleteById(entity.getId());
	}
	
	@Override
	@Transactional
	public void deleteById(Long id) {
		E entity = findOne(id);
		entityManager.remove(entity);
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
	
	@Override
	public Long count() {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> query = cb.createQuery(Long.class);
		Root<E> root = query.from(clazz);
		query.select(cb.count(root));
		return entityManager.createQuery(query).getSingleResult();
	}
}
