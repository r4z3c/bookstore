package bookstore.web.webservices.rest;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import bookstore.core.ejb.managers.interfaces.IShelfRemote;
import bookstore.core.exceptions.InvalidAttributeException;
import bookstore.core.models.Book;
import bookstore.core.models.IBook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Path("/shelf")
@Stateless
public class Shelf implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@EJB @Inject
	private IShelfRemote shelf;
	
	// management
	
	@POST @Path("/add")
	@Produces("application/json")
	@Consumes("application/json")
	public Response addBook(Book book) {
		try {
			shelf.addBook(book);
			return Response.ok().build();
		} catch (InvalidAttributeException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

	@GET @Path("/get-by-isbn/{isbn}")
	@Produces("application/json")
	public Response getBookByIsbn(@PathParam("isbn") String isbn) {
		IBook book = shelf.getBookByIsbn(isbn);
		return Response.ok(jsonParser().toJson(book)).build();
	}

	@GET @Path("/get-by-name/{name}")
	@Produces("application/json")
	public Response getBookByName(@PathParam("name") String name) {
		IBook book = shelf.getBookByName(name);
		return Response.ok(jsonParser().toJson(book)).build();
	}
	
	@GET @Path("/list")
	@Produces("application/json")
	public Response listBooks() {
		List<IBook> books = shelf.listBooks();
		return Response.ok(jsonParser().toJson(books)).build();
	}

	@DELETE @Path("/remove/{isbn}")
	@Produces("application/json")
	public Response removeBook(@PathParam("isbn") String isbn) {
		try {
			shelf.removeBook(isbn);
			return Response.ok().build();
		} catch (InvalidAttributeException e) {
			return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}
	
	@DELETE @Path("/clear")
	@Produces("application/json")
	public Response clear() {
		shelf.clear();
		return Response.ok().build();
	}
	
	// util
	
	private Gson jsonParser(){
		return new GsonBuilder().create();
	}
	
	// constructors
	
	public Shelf(bookstore.core.ejb.managers.interfaces.IShelfRemote shelf) {
		super();
		this.shelf = shelf;
	}

	public Shelf() throws NamingException {
		super();
	}
	
	// getters and setters

	public IShelfRemote getShelf() {
		return shelf;
	}
	public void setShelf(IShelfRemote shelf) {
		this.shelf = shelf;
	}

}