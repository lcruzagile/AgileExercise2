package main.java.com.todolist.controller;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import main.java.com.todolist.TodoList.TodoList;
import main.java.com.todolist.service.TodoListService;

@RestController
public class TodoListController {
	
	@Autowired
	private TodoListService todoListService;
	
	// Creates logger
	final Logger logger = LogManager.getLogger(TodoListController.class);
	
	@RequestMapping("/todo-list")
	public List<TodoList> getTodoLists(){
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist");
		return this.todoListService.getTodoLists();
	}

	@RequestMapping("/todo-list/{id}")
	public TodoList getTodoList(@PathVariable int id ){
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todo-list");
		return this.todoListService.getTodoList(id);
	}

	@RequestMapping(method=RequestMethod.POST, value="/todo-list")
	public void addTodoList(@RequestBody TodoList todoList) {
		this.todoListService.addTodoList(todoList);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/todo-list/{id}")
	public void updateTodoList(@PathVariable("id") int id, @RequestBody TodoList todoList) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/{id} ");
		
		this.todoListService.updateTodoList(todoList);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/todo-list/{id}")
	public void deleteTodoList(@PathVariable("id") int id) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todo-list/{id} "+id);
		
		this.todoListService.deleteTodoList(id);
	}
}