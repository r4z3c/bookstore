package bookstore.core.models;

import java.util.ArrayList;
import java.util.List;

import bookstore.core.exceptions.InvalidAttributeException;

public class Book implements IBook {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String isbn;
	private List<String> authors = new ArrayList<String>();
	
	// validations
	
	public void validate() throws InvalidAttributeException {
		validateName();
		validateIsbn();
		validateAuthors();
	}
	
	public void validateName() throws InvalidAttributeException {
		if(name == null || name.isEmpty()) throw new InvalidAttributeException("name");
	}
	
	public void validateIsbn() throws InvalidAttributeException {
		if(isbn == null || isbn.isEmpty()) throw new InvalidAttributeException("isbn");
	}
	
	public void validateAuthors() throws InvalidAttributeException {
		if(authors == null || authors.isEmpty()) throw new InvalidAttributeException("authors");
	}
	
	// utilities
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", isbn=" + isbn + ", authors=" + authors
				+ "]";
	}

	@Override
	public boolean equals(Object obj) {
		return ((Book) obj).getIsbn().equals(isbn);
	}
	
	// constructors

	public Book(String name, String isbn, List<String> authors) {
		super();
		this.name = name;
		this.isbn = isbn;
		this.authors = authors;
	}

	public Book() {}
	
	// getters and setters
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public List<String> getAuthors() {
		return authors;
	}
	public void setAuthors(List<String> authors) {
		this.authors = authors;
	}
	
}