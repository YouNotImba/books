package com.someco.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
		model.addAttribute("books", books);
		model.addAttribute("bookToCreate", new Book());
		return "main";
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
