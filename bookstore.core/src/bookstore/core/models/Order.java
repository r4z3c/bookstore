package bookstore.core.models;

import java.util.List;

public class Order implements IOrder {

	private static final long serialVersionUID = 1L;
	
	private List<IBook> books;
	
	// utilities
	
	@Override
	public String toString() {
		return "Order [books=" + books + "]";
	}
	
	// constructors
	
	public Order(List<IBook> books) {
		super();
		this.books = books;
	}

	public Order() {
	}

	// getters and setters

	public List<IBook> getBooks() {
		return books;
	}
	public void setBooks(List<IBook> books) {
		this.books = books;
	}

}
