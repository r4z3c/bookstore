package bookstore.core.models;

import java.io.Serializable;
import java.util.List;

import bookstore.core.exceptions.InvalidAttributeException;

public interface IBook extends Serializable {
	
	// validations
	
	public void validate() throws InvalidAttributeException;
	public void validateName() throws InvalidAttributeException;
	public void validateIsbn() throws InvalidAttributeException;
	public void validateAuthors() throws InvalidAttributeException;
	
	// utilities
	
	public String toString();
	
	// getters and setters
	
	public String getName();
	public void setName(String name);
	public String getIsbn();
	public void setIsbn(String isbn);
	public List<String> getAuthors();
	public void setAuthors(List<String> authors);
}