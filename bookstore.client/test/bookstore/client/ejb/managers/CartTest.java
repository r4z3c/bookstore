package bookstore.client.ejb.managers;

import java.util.ArrayList;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import bookstore.core.ejb.managers.interfaces.ICartRemote;
import bookstore.core.ejb.managers.interfaces.IShelfRemote;
import bookstore.core.exceptions.InvalidAttributeException;
import bookstore.core.models.Book;
import bookstore.core.models.IBook;
import bookstore.core.models.Order;

public class CartTest {
	
	private static final String SHELF_EJB_URL = "bookstore.ear/bookstore.ejb/Shelf!bookstore.core.ejb.managers.interfaces.IShelfRemote";
	private static final String CART_EJB_URL = "bookstore.ear/bookstore.ejb/Cart!bookstore.core.ejb.managers.interfaces.ICartRemote";
	
	private static final String BOOK_NAME_PREFIX = "test book";
	private static final String ISBN_NAME_PREFIX = BOOK_NAME_PREFIX + " isbn";
	
	private static final Integer SHELF_SIZE = 20;
	
	private InitialContext context;
	
	private IShelfRemote shelf;
	private ICartRemote cart;
	
	private Queue queue;
	private QueueConnectionFactory factory;
	private QueueConnection connection;
	private QueueSession session;
	
	@Test
	public void cartTest() throws InvalidAttributeException, NamingException, JMSException {	
		// local cart cache for further assertion
		
		List<IBook> cartCache = new ArrayList<IBook>();
		
		// asserts shelf size
		
		Assert.assertTrue(CartTest.SHELF_SIZE == shelf.listBooks().size());
		
		// assert cart empty
		
		Assert.assertTrue(cart.listBooks().size() == 0);
		
		// add 'test book 15' to cart
		
		IBook book = shelf.getBookByName(getBookNameFor(15));
		
		cart.addBook(book);
		cartCache.add(book);
		
		// add 'test book 16' and 'test book 17' to cart at the same time
		
		List<IBook> booksToAdd = new ArrayList<IBook>();
		
		book = shelf.getBookByName(getBookNameFor(16));
		
		booksToAdd.add(book);
		cartCache.add(book);
		
		book = shelf.getBookByName(getBookNameFor(17));
		
		booksToAdd.add(book);
		cartCache.add(book);
		
		cart.addAllBook(booksToAdd);
		
		// assert cart size and cart content
		
		Assert.assertTrue(cart.listBooks().size() == cartCache.size());
		
		String content = cart.printBooksInfo();
		System.out.println("\n\n"+"|i| cartTest@bookstore.client.ejb.managers.CartTest:\n"+content+"\n\n");
		
		Assert.assertEquals(content, cartCache.toString());
		
		// remove 'test book 16' from cart
		
		cart.removeBook(getBookIsbnFor(16));
		cartCache.remove(1);
		
		// assert cart size and cart content
		
		Assert.assertTrue(cart.listBooks().size() == cartCache.size());
		
		content = cart.printBooksInfo();
		System.out.println("\n\n"+"|i| cartTest@bookstore.client.ejb.managers.CartTest:\n"+content+"\n\n");
		
		Assert.assertEquals(content, cartCache.toString());
		
		// dispatch order
		
		Order order = new Order(cart.listBooks());
		
        getSender().send(session.createObjectMessage(order));
	}
	
	private QueueSender getSender() throws NamingException, JMSException {
        return session.createSender(queue);
	}
	
	public CartTest() throws NamingException, InvalidAttributeException, JMSException {
		initializeContext();
		
		shelf = lookupForShelfRemoteEjb();
		cart = lookupForCartRemoteEjb();
		
		buildOrderMdbConnection();
		
		clearShelf();
		populateShelf(CartTest.SHELF_SIZE);
	}
	
	// context initialization
	
	private void initializeContext() throws NamingException {
		context = new InitialContext();
	}
	
	private IShelfRemote lookupForShelfRemoteEjb() throws NamingException {
		return (IShelfRemote) context.lookup(SHELF_EJB_URL);
	}
	
	private ICartRemote lookupForCartRemoteEjb() throws NamingException {
		return (ICartRemote) context.lookup(CART_EJB_URL);
	}
	
	private void buildOrderMdbConnection() throws NamingException, JMSException {
		queue = (Queue) context.lookup("jms/queue/order");
		factory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
		connection =  factory.createQueueConnection("usuarioRemoto","infnet123");
        session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);
	}
	
	// factory utilities
	
	private void clearShelf() {
		shelf.clear();
	}
	
	private void populateShelf(Integer booksCount) throws InvalidAttributeException {
		for(int i=1 ; i <= booksCount ; i++) {
			shelf.addBook(getBookFor(i));
		}
	}

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
		return CartTest.BOOK_NAME_PREFIX+" "+bookSuffix;
	}
	
	private String getBookIsbnFor(Integer bookSuffix) {
		return CartTest.ISBN_NAME_PREFIX+" "+bookSuffix;
	}
	
	private List<String> getAuthorsFor(String bookName) {
		List<String> authors = new ArrayList<String>();
		
		authors.add(bookName+" author 1");
		authors.add(bookName+" author 2");
		
		return authors;
	}

}
