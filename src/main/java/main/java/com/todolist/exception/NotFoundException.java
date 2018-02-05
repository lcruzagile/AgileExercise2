package main.java.com.todolist.exception;

public class NotFoundException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String notFoundValue;

	public NotFoundException(String notFoundValue) {
		this.notFoundValue = notFoundValue;
	}
	
	public String getNotFoundValue() {
		return notFoundValue;
	}
	
}