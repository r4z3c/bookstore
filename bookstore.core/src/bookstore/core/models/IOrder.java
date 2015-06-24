package bookstore.core.models;

import java.io.Serializable;
import java.util.List;

public interface IOrder extends Serializable {
	
	// utilities
	
	public String toString();
	
	// getters and setters
	
	public List<IBook> getBooks();
	public void setBooks(List<IBook> books);

}
