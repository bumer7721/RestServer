package net.rest.server.dao;

import java.util.List;

import net.rest.server.domains.IdDomain;

public interface BaseDAO<E extends IdDomain> {

	E create(E entity);

	E update(E entity);

	void delete(E entity);

	void deleteById(Long id);

	E findOne(Long id);

	List<E> findAll();

	Long count();

}
