package bookstore.core.exceptions;

public class InvalidAttributeException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidAttributeException(String attribute) {
		super(attribute+" is invalid");
	}
	
	public InvalidAttributeException(String attribute, String situation) {
		super(attribute+" "+situation);
	}

}