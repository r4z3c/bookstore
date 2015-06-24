package bookstore.web.webservices.soap;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;

import bookstore.core.ejb.managers.interfaces.IShelfRemote;
import bookstore.core.exceptions.InvalidAttributeException;
import bookstore.core.models.Book;
import bookstore.core.models.IBook;

public class Shef implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private bookstore.core.ejb.managers.interfaces.IShelfRemote shelf;
	
	// management
	
	public void addBook(Book book) throws InvalidAttributeException {
		shelf.addBook(book);
	}
	
	public List<IBook> listBooks() {		
		return shelf.listBooks();
	}
	
	// constructors
	
	public Shef(IShelfRemote shelf) {
		super();
		this.shelf = shelf;
	};
	
	public Shef() {
		super();
	}

}
