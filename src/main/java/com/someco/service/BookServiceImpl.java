package com.someco.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.someco.entity.Book;
import com.someco.repository.DataRepository;

/**
 * @author ikarmatsky
 *
 */
@Service ("bookService")
@SuppressWarnings("rawtypes")
@Transactional
public class BookServiceImpl implements BookService{

	private static final Log LOGGER = LogFactory.getLog(BookServiceImpl.class);
	
	@Autowired
	@Qualifier ("bookRepository")
	private DataRepository dataRepository;
	
	public DataRepository getDataRepository() {
		return dataRepository;
	}

	public void setDataRepository(DataRepository dataRepository) {
		this.dataRepository = dataRepository;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(Book entity) {
		try{
			dataRepository.add(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed add book",e);
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean update(Book entity) {
		try {
			dataRepository.update(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed update book");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean delete(Book entity) {
		try{
			dataRepository.delete(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed delete book");
			return false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getAllBooks() {
		List<Book> books = dataRepository.getAllData();
		return books;
	}

	@Override
	public boolean delete(Long id) {
		try{
			dataRepository.delete(id);
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed delete book id : " + id , e);
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public Book loadById(Long id) {
		try{
			return (Book) dataRepository.loadById(id);
		} catch (Exception e) {
			LOGGER.error("failed load book with id : " + id);
			return null;
		}
		
	}
	
}
