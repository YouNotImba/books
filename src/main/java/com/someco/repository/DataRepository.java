package com.someco.repository;

import java.util.List;

import com.someco.entity.DomainObject;

/**
 * @author ikarmatsky
 *
 * 
 */
public interface DataRepository <T extends DomainObject>{

	void add(T entity);
	void update(T entity);
	List<T> getAllData();
	void delete(T entity);
	void delete(Long id);
	T loadById(Long id);
}
