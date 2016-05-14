package com.abmv.angular.attack.rest.services;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abmv.angular.attack.entities.es.BookES;
import com.abmv.angular.attack.entities.sql.Book;
import com.abmv.angular.attack.entities.sql.User;
import com.abmv.angular.attack.service.BookService;
import com.abmv.angular.attack.service.UserService;

@RestController
@RequestMapping("/book")
public class BookRestService {

	@Autowired
	BookService bookSer;
	
	@Autowired
	UserService usrSer;
	
	@RequestMapping(value= "/addBook", method = RequestMethod.POST,
			consumes={MediaType.APPLICATION_JSON_VALUE})
	public Book addBook(@RequestBody Book b){
		b=bookSer.addBook(b);
		return b;
	}
	
	@RequestMapping("/getBooks")
	public Iterable<BookES> getBooks() throws Exception{
		return bookSer.getAllBooks();
	}
	
	@RequestMapping("/getLibrary/{id}")
	public Collection<Book> getLibrary(@PathVariable Long id){
		return bookSer.getLibrary(usrSer.getUserById(id));
	}
	
	@RequestMapping("/fuzzySearch")
	public List<BookES> fuzzySearch(@RequestParam(defaultValue="lard") String text) throws Exception{
		return bookSer.searchFuzzy(text);
	}
	
	@RequestMapping("/getUsersHaving/{id}")
	public List<User> getAllUser(@PathVariable Long id){
		return bookSer.findAllUserHavingBook(id);
	}
}
