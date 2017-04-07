package com.someco.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.someco.entity.User;

/**
 * @author ikarmatsky
 *
 */
@Repository ("userRepository")
public class UserDataRepository implements DataRepository<User>{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void add(User entity) {
		entityManager.persist(entity);		
	}

	@Override
	public void update(User entity) {
		entityManager.merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllData() {
		return entityManager.createQuery("select u from User u").getResultList();
	}

	@Override
	public void delete(User entity) {
		entityManager.remove(entity);
	}

	@Override
	public void delete(Long id) {
		entityManager.createQuery("delete from User where id=:id").setParameter("id", id).executeUpdate();
	}

	@Override
	public User loadById(Long id) {
		return (User) entityManager.createQuery("select u from User u where u.id = :id").setParameter("id", id).getSingleResult();
	}
	
}
