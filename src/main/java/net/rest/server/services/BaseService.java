package net.rest.server.services;

import java.util.List;

import net.rest.server.dto.IdDomainDTO;

public interface BaseService<D extends IdDomainDTO> {

	D create(D dto);

	D update(D dto);

	void delete(D dto);

	void deleteById(Long id);

	D findOne(Long id);

	List<D> findAll();

	Long count();

}
