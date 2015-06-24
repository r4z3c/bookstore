package bookstore.ejb.managers;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;

import bookstore.core.ejb.managers.interfaces.IShelfLocal;
import bookstore.core.ejb.managers.interfaces.IShelfRemote;
import bookstore.core.exceptions.InvalidAttributeException;
import bookstore.core.models.IBook;

@Singleton
public class Shelf implements IShelfLocal, IShelfRemote {

	private static final long serialVersionUID = 1L;
	
	private bookstore.core.repositories.IBook shelf;
	
	// management
	
	@Override
	public void addBook(IBook book) throws InvalidAttributeException {
		shelf.add(book);
	}
	
	@Override
	public void addAllBook(List<IBook> books) throws InvalidAttributeException {
		shelf.addAll(books);
	}

	@Override
	public IBook getBookByIsbn(String isbn) {
		return shelf.getByIsbn(isbn);
	}

	@Override
	public IBook getBookByName(String name) {
		return shelf.getByName(name);
	}

	@Override
	public List<IBook> listBooks() {
		return shelf.list();
	}

	@Override
	public void removeBook(String isbn) throws InvalidAttributeException {
		shelf.remove(isbn);
	}
	
	@Override
	public void clear() {
		shelf.clear();
	}
	
	@Override
	public String printBooksInfo() {
		String booksInfo = shelf.getBooks().toString();
		
		System.out.println("\n\n"+"|i| printBooksInfo@bookstore.ejb.managers.Shelf:\n"+booksInfo+"\n\n");
		
		return booksInfo;
	}
	
	// callbacks
	
	@PostConstruct
	private void populateShelf() {		
		shelf = (bookstore.core.repositories.IBook) new bookstore.core.repositories.Book();
	}

	// constructors

	public Shelf() {
    	super();
    }
	
	// getters and setters
	
	public bookstore.core.repositories.IBook getShelf() {
		return this.shelf;
	}

}