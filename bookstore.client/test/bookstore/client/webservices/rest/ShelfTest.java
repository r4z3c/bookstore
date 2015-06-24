package bookstore.client.webservices.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import bookstore.core.models.Book;

public class ShelfTest {

	private static final String BOOK_NAME_PREFIX = "test book";
	private static final String ISBN_NAME_PREFIX = BOOK_NAME_PREFIX + " isbn";
	
	private Shelf shelf = new Shelf();
	
	@Test
	public void crudTest() {
		Book book = getBookFor(1);
		
		// clear and assert
		
		shelf.clear();
		Assert.assertTrue(shelf.list().size() == 0);
		
		// add and assert
		
		shelf.add(book);
		List<Book> foundedBooks = shelf.list();
		Assert.assertTrue(foundedBooks.size() == 1);
		Book founded = foundedBooks.get(0);
		Assert.assertEquals(book, founded);
		
		// search by name and assert
		
		founded = shelf.getByName(book.getName());
		Assert.assertNotNull(founded);
		Assert.assertEquals(book, founded);
		
		// remove and assert
		
		shelf.remove(book.getIsbn());
		Assert.assertTrue(shelf.list().size() == 0);
		Assert.assertNull(shelf.getByName(book.getName()));
	}
	
	// factory utilities
	
	private Book getBookFor(Integer bookSuffix) {
		String bookName = getBookNameFor(bookSuffix);
		
		Book book = new Book(
			bookName,
			getBookIsbnFor(bookSuffix),
			getAuthorsFor(bookName)
		);
		
		return book;
	}
	
	private String getBookNameFor(Integer bookSuffix) {
		return ShelfTest.BOOK_NAME_PREFIX+" "+bookSuffix;
	}
	
	private String getBookIsbnFor(Integer bookSuffix) {
		return ShelfTest.ISBN_NAME_PREFIX+" "+bookSuffix;
	}
	
	private List<String> getAuthorsFor(String bookName) {
		List<String> authors = new ArrayList<String>();
		
		authors.add(bookName+" author 1");
		authors.add(bookName+" author 2");
		
		return authors;
	}
	
}
