package bookstore.core.ejb.managers.interfaces;

import java.io.Serializable;
import java.util.List;

import bookstore.core.exceptions.InvalidAttributeException;

public interface IBook extends Serializable {
	
	// management
	
	public void addBook(bookstore.core.models.IBook book) throws InvalidAttributeException;
	public void addAllBook(List<bookstore.core.models.IBook> books) throws InvalidAttributeException;
	public bookstore.core.models.IBook getBookByIsbn(String isbn);
	public bookstore.core.models.IBook getBookByName(String name);
	public List<bookstore.core.models.IBook> listBooks();
	public void removeBook(String isbn) throws InvalidAttributeException;
	public void clear();
	public String printBooksInfo();
	
	// getters and setters
	
	public bookstore.core.repositories.IBook getShelf();

}