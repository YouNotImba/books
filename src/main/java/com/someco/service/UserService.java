package com.someco.service;

import java.util.List;

import com.someco.entity.Book;
import com.someco.entity.User;

/**
 * @author ikarmatsky
 *
 */
public interface UserService {

	boolean add (User entity);
	
	boolean update (User entity);
	
	boolean delete (User entity);
	
	List<Book> getAllUsers ();
	
	boolean delete (Long id);
	
	Book loadById (Long id);
}
