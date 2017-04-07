package com.someco;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import com.someco.config.JpaConfig;
import com.someco.entity.Book;
import com.someco.repository.BookDataRepository;
import com.someco.repository.DataRepository;

import junit.framework.Assert;

/**
 * @author ikarmatsky
 *
 */
/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {JpaConfig.class , BookDataRepository.class})*/
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class DaoTestsImpl {

	@Autowired
	@Qualifier("bookRepository")
	private DataRepository bookRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Test
	public void addBookTest(){
		Book book = new Book("test","test");
		List<Book> books = bookRepository.getAllData();
		int sizeBeforeAdding = books.size();
		bookRepository.add(book);
		List<Book> afterAdding = bookRepository.getAllData();
		int sizeAfter = afterAdding.size();
		Assert.assertEquals(sizeBeforeAdding + 1, sizeAfter);
		Book addedBook = afterAdding.get(5);
		assertEquals(book.getTitle(), addedBook.getTitle());
		assertEquals(book.getAuthor(), addedBook.getAuthor());

	}
	
	@Test
	public void testLocks() {
		
	}
	
}
