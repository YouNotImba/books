package com.someco.service;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.someco.entity.Book;
import com.someco.entity.User;
import com.someco.repository.DataRepository;

/**
 * @author ikarmatsky
 *
 */
@SuppressWarnings("rawtypes")
@Service ("userService")
public class UserServiceImpl implements UserService{

	private static final Log LOGGER = LogFactory.getLog(UserServiceImpl.class);
	
	@Autowired
	@Qualifier ("userRepository")
	private DataRepository userRepository;

	public void setUserRepository(DataRepository userRepository) {
		this.userRepository = userRepository;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean add(User entity) {
		try {
			userRepository.add(entity);
			return true;
		} catch (Exception e) {
			LOGGER.error("Failed save user",e);
			return false;
		}
	}

	@Override
	public boolean update(User entity) {
		// Not need now
		return false;
	}

	@Override
	public boolean delete(User entity) {
		// Not need now
		return false;
	}

	@Override
	public List<Book> getAllUsers() {
		// Not need now
		return null;
	}

	@Override
	public boolean delete(Long id) {
		// Not need now
		return false;
	}

	@Override
	public Book loadById(Long id) {
		try {
			return (Book) userRepository.loadById(id);
		} catch (Exception e) {
			LOGGER.error("Failed load user id : " + id,e);
			return null;
		}
	}
	
}
