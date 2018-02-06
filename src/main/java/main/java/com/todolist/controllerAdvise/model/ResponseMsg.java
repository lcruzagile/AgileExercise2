package main.java.com.todolist.controllerAdvise.model;

public class ResponseMsg {
	private String message;
 
	public ResponseMsg(String msg){
		this.message = msg;
	}
	
	public String getMessage() {
		return message;
	}
 
	public void setMessage(String message) {
		this.message = message;
	}
}
