package bookstore.ejb.managers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;

import bookstore.core.ejb.managers.interfaces.ICartLocal;
import bookstore.core.ejb.managers.interfaces.ICartRemote;
import bookstore.core.exceptions.InvalidAttributeException;
import bookstore.core.models.IBook;

@Stateful
public class Cart implements ICartLocal, ICartRemote {

	private static final long serialVersionUID = 1L;
	
	private bookstore.core.repositories.IBook cart;
	
	// management
	
	@Override
	public void addBook(IBook book) throws InvalidAttributeException {
		cart.add(book);
	}
	
	@Override
	public void addAllBook(List<IBook> books) throws InvalidAttributeException {
		cart.addAll(books);
	}
	
	@Override
	public IBook getBookByIsbn(String isbn) {
		return cart.getByIsbn(isbn);
	}

	@Override
	public IBook getBookByName(String name) {
		return cart.getByName(name);
	}

	@Override
	public List<IBook> listBooks() {
		return cart.list();
	}
	
	@Override
	public void removeBook(String isbn) throws InvalidAttributeException {
		cart.remove(isbn);
	}
	
	@Override
	public void clear() {
		cart.clear();
	}
	
	@Override
	public String printBooksInfo() {
		String booksInfo = cart.getBooks().toString();
		
		System.out.println("\n\n"+"|i| printBooksInfo@bookstore.ejb.managers.Cart:\n"+booksInfo+"\n\n");
		
		return booksInfo;
	}
	
	// callbacks
	
	@PostConstruct
	private void populateShelf() {		
		cart = (bookstore.core.repositories.IBook) new bookstore.core.repositories.Book();
	}

	// constructors

	public Cart() {
    	super();
    }
	
	// getters and setters
	
	public bookstore.core.repositories.IBook getShelf() {
		return this.cart;
	}

}