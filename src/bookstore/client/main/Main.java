package bookstore.client.main;

import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import bookstore.core.ejb.managers.interfaces.IRemoteBook;
import bookstore.core.models.IBook;

public class Main {
	
	private void start(String[] args) throws NamingException {
		String url = "bookstore.ear/bookstore.ejb/Shelf!bookstore.core.ejb.managers.interfaces.IRemoteBook";
		
		InitialContext context = new InitialContext();
		
		IRemoteBook shelf = (IRemoteBook) context.lookup(url);
		
		System.out.println((List<IBook>) shelf.listBooks());
	}

	public static void main(String[] args) throws NamingException {
		new Main().start(args);
	}

}
