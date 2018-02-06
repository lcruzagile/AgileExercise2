package main.java.com.todolist.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.todolist.TodoList.TodoList;
import main.java.com.todolist.exception.NotFoundException;
import main.java.com.todolist.repository.TodoListRepository;
import main.java.com.todolist.task.Task;

@Service
public class TodoListService {

	@Autowired
	private TodoListRepository todoListRepository;

	// Creates logger
	final Logger logger = LogManager.getLogger(TodoListService.class);

	public void addTodoList(TodoList todoList) {
		this.todoListRepository.save(todoList);
	}

	public TodoList getTodoList(int id ) {
		TodoList todoList = this.todoListRepository.findOne(id);
		if(todoList == null) {
			throw new NotFoundException("List not found: "+id);
		}
		return todoList;
	}
	
	public TodoList getTodoListByName(String name) {
		TodoList todoList = this.todoListRepository.findByName(name);
		if(todoList == null) {
			throw new NotFoundException("List not found: "+name);
		}
		return todoList;
	}

	public void updateTodoList(TodoList todoList) {
		if(this.todoListRepository.findOne(todoList.getId()) == null) {
			throw new NotFoundException("List not found: "+todoList);
		}
		this.todoListRepository.save(todoList);
	}

	public void deleteTodoList(int id) {
		TodoList todoList = this.todoListRepository.findOne(id);
		if(todoList == null) {
			throw new NotFoundException("List not found: "+id);
		}
		this.todoListRepository.delete(id);
	}
	
	public void addTask(int todoListId, Task task) {
		TodoList todoList = this.todoListRepository.findOne(todoListId);
		todoList.getTasks().add(task);
		this.todoListRepository.save(todoList);
	}

	public List<TodoList> getTodoLists() {
		return (List<TodoList>) this.todoListRepository.findAll();
	}
}