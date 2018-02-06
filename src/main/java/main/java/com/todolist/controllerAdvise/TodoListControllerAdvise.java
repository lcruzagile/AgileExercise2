package main.java.com.todolist.controllerAdvise;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import main.java.com.todolist.controllerAdvise.model.ResponseMsg;
import main.java.com.todolist.exception.NotFoundException;

@RestControllerAdvice
public class TodoListControllerAdvise {
	@ExceptionHandler(NotFoundException.class)
	public ResponseMsg handleNotFoundException(NotFoundException ex) {
		ResponseMsg responseMsg = new ResponseMsg(ex.getMessage());
		return responseMsg;
	}
}
