package com.someco.service;

import java.util.List;

import com.someco.entity.Book;

/**
 * @author ikarmatsky
 *
 */
public interface BookService {

	boolean add (Book entity);
	
	boolean update (Book entity);
	
	boolean delete (Book entity);
	
	List<Book> getAllBooks ();
	
	boolean delete (Long id);
	
	Book loadById (Long id);
}
