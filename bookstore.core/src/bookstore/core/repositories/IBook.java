package bookstore.core.repositories;

import java.io.Serializable;
import java.util.List;

import bookstore.core.exceptions.InvalidAttributeException;

public interface IBook extends Serializable {
	
	// management
	
	public void addAll(List<bookstore.core.models.IBook> books) throws InvalidAttributeException;
	public void add(bookstore.core.models.IBook book) throws InvalidAttributeException;
	public void validate(bookstore.core.models.IBook book) throws InvalidAttributeException;
	public bookstore.core.models.IBook getByIsbn(String isbn);
	public bookstore.core.models.IBook getByName(String name);
	public List<bookstore.core.models.IBook> list();
	public void remove(String isbn) throws InvalidAttributeException;
	public void clear();
	
	// getters and setters

	public List<bookstore.core.models.IBook> getBooks();
	public void setBooks(List<bookstore.core.models.IBook> books);
	public Boolean getValidateUniqueness();
	public void setValidateUniqueness(Boolean validateUniqueness);

}