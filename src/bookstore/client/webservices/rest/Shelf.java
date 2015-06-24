package bookstore.client.webservices.rest;

import java.lang.reflect.Type;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;

import bookstore.core.models.Book;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class Shelf {
	
	private Client client;
	
	public void add(Book book) {
		handleResponse(
			getResource("add").type("application/json").post(ClientResponse.class, getGson().toJson(book))
		);
	}
	
	public Book getByName(String bookName) {
		String encodedBookName = encodeString(bookName);
		
		String json = handleResponse(
			getResource("get-by-name/"+encodedBookName).get(ClientResponse.class)
		);
		
		return getGson().fromJson(json, Book.class);
	}
	
	public List<Book> list() {
		String json = handleResponse(
			getResource("list").get(ClientResponse.class)
		);
		
		return getGson().fromJson(json, getCollectionType());
	}
	
	public void remove(String bookIsbn) {
		String encodedBookIsbn = encodeString(bookIsbn);
		
		handleResponse(
			getResource("remove/"+encodedBookIsbn).delete(ClientResponse.class)
		);
	}
	
	public void clear() {
		handleResponse(
			getResource("clear").delete(ClientResponse.class)
		);
	}
	
	// private methods
	
	private String handleResponse(ClientResponse response) {
		Integer status = response.getStatus();
		String body = response.getEntity(String.class);
		
		if(status != 200) {
			String message = "\n|E| REST call returned "+status+":\n"+body+"\n";
			throw new RuntimeException(message);
		}
		
		return body;
	}
	
	@SuppressWarnings("deprecation")
	private String encodeString(String str) {
		return URLEncoder.encode(str).replaceAll("\\+", "%20");
	}
	
	private Gson getGson(){
		return new Gson();
	}
	
	private WebResource getResource(String action) {
		String path = "http://localhost:8080/bookstore.web/rest/shelf/"+action;
		//System.out.println("|d| resquest for: "+path);
		return client.resource(path);
	}
	
	private Type getCollectionType() {
		return new TypeToken<Collection<Book>>(){}.getType();
	}
	
	// constructors

	public Shelf(Client client) {
		super();
		this.client = client;
	}
	
	public Shelf() {
		client = Client.create();
	}
	
	// getters and setters
	
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}	
}