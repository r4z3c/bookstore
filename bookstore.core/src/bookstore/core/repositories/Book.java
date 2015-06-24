package bookstore.core.repositories;

import java.util.ArrayList;
import java.util.List;

import bookstore.core.exceptions.InvalidAttributeException;

public class Book implements IBook {
	
	private static final long serialVersionUID = 1L;
	
	private List<bookstore.core.models.IBook> books = new ArrayList<bookstore.core.models.IBook>();
	private Boolean validateUniqueness = true;
	
	// management
	
	@Override
	public void addAll(List<bookstore.core.models.IBook> books) throws InvalidAttributeException {
		for(bookstore.core.models.IBook book : books) {
			add(book);
		}
	}
	
	@Override
	public void add(bookstore.core.models.IBook book) throws InvalidAttributeException {
		validate(book);
		this.books.add(book);
	}
	
	@Override
	public void validate(bookstore.core.models.IBook book) throws InvalidAttributeException {
		book.validate();
		if(validateUniqueness && getByIsbn(book.getIsbn()) != null) throw new InvalidAttributeException("isbn","already taken");
	}
	
	@Override
	public bookstore.core.models.IBook getByIsbn(String isbn) {
		bookstore.core.models.IBook found = null;
		
		for(bookstore.core.models.IBook book : books) {
			if(book.getIsbn().equals(isbn)) {
				found = book;
				break;
			}
		}
		
		return found;
	}
	
	@Override
	public bookstore.core.models.IBook getByName(String name) {
		bookstore.core.models.IBook found = null;
		
		for(bookstore.core.models.IBook book : books) {
			if(book.getName().equals(name)) {
				found = book;
				break;
			}
		}
		
		return found;
	}
	
	@Override
	public List<bookstore.core.models.IBook> list() {
		return books;
	}
	
	@Override
	public void remove(String isbn) throws InvalidAttributeException {
		for(int i=0 ; i < books.size() ; i++) {
			if(books.get(i).getIsbn().equals(isbn)) {
				books.remove(i);
				return;
			}
		}
		
		throw new InvalidAttributeException("isbn","not found");
	}
	
	@Override
	public void clear() {
		books.clear();
	}
	
	// constructors

	public Book(List<bookstore.core.models.IBook> books, Boolean validateUniqueness) {
		super();
		this.books = books;
		this.validateUniqueness = validateUniqueness;
	}
	
	public Book() {}
	
	// getters and setters

	public List<bookstore.core.models.IBook> getBooks() {
		return books;
	}
	public void setBooks(List<bookstore.core.models.IBook> books) {
		this.books = books;
	}
	public Boolean getValidateUniqueness() {
		return validateUniqueness;
	}
	public void setValidateUniqueness(Boolean validateUniqueness) {
		this.validateUniqueness = validateUniqueness;
	}

}