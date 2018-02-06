package main.java.com.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import main.java.com.todolist.TodoList.TodoList;

public interface TodoListRepository  extends CrudRepository<TodoList, Integer>{
	public TodoList findByName(String name);
}
