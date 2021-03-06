package com.someco.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.someco.entity.Book;
import com.someco.service.BookService;

/**
 * @author ikarmatsky
 *
 */
@Controller
public class BookController {

	@Autowired
	@Qualifier ("bookService")
	private BookService bookService;

	public BookService getBookService() {
		return bookService;
	}

	public void setBookService(BookService bookService) {
		this.bookService = bookService;
	}
	
	@RequestMapping ("/index")
	public String index(){
		return "index";
	}
	
	@RequestMapping (value = {"/main","/"},method = RequestMethod.GET)
	public String main(Model model){
		List<Book> books = bookService.getAllBooks();
		Set<Book> books1 = new HashSet<>();
		for (Book book : books) {
			if(book.getUser() != null)
				books1.add(book);
		}
		model.addAttribute("books", books1);
		model.addAttribute("bookToCreate", new Book());
		return "main";
	}
	
	@RequestMapping (value = "/api/books" ,method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Book>> getAllBooksInJson(){
		List<Book> books = bookService.getAllBooks();
		return new ResponseEntity<List<Book>>(books, HttpStatus.OK);
	}
	
	@RequestMapping (value = "/main" , params = {"addBook"} , method = RequestMethod.POST)
	public String createBook(Book bookToCreate, BindingResult bindingResult, ModelMap model){
		bookService.add(bookToCreate);
		model.clear();
		return "redirect:/main";
	}
	
	@RequestMapping (value = "/edit/{id}" , method = RequestMethod.POST)
	public String deleteBook(@PathVariable Long id){
		bookService.delete(id);
		return "redirect:/main";
	}
	
	@RequestMapping (value = "/edit/{id}" , method = RequestMethod.GET)
	public String editBook(@PathVariable Long id, Model model){
		Book book = bookService.loadById(id);
		model.addAttribute("editedBook",book);
		return "edit";
	}
	
	@RequestMapping (value = "/merge" , method = RequestMethod.POST)
	public String mergeBook(@ModelAttribute ("editedBook") Book editedBook,BindingResult bindingResult, ModelMap model){
		bookService.update(editedBook);
		model.clear();
		return "redirect:/main";
	}
}
