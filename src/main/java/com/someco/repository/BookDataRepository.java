package com.someco.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.someco.entity.Book;

/**
 * @author ikarmatsky
 *
 */
@Repository("bookRepository")
@Transactional
public class BookDataRepository implements DataRepository<Book> {

	@PersistenceContext
	EntityManager entityManager;

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void add(Book entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(Book entity) {
		// entityManager.merge(entity);
		entityManager
				.createQuery("update Book set author = :author, title = :title where id = :id")
				.setParameter("author", entity.getAuthor()).setParameter("title", entity.getTitle())
				.setParameter("id", entity.getId()).executeUpdate();
	}

	@Override
	public List<Book> getAllData() {
		@SuppressWarnings("unchecked")
		List<Book> books = entityManager.createQuery("select b from Book b").getResultList();
		return books;
	}

	@Override
	public void delete(Book entity) {
		entityManager.remove(entity);
	}

	@Override
	public void delete(Long id) {
		entityManager.createQuery("delete from Book b where b.id = :id").setParameter("id", id).executeUpdate();
	}

	@Override
	public Book loadById(Long id) {
		Book book = (Book) entityManager.createQuery("select b from Book b where b.id = :id").setParameter("id", id)
				.getSingleResult();
		return book;
	}

}
