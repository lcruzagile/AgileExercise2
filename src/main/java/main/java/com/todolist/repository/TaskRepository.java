package main.java.com.todolist.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import main.java.com.todolist.task.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
	List<Task> findByStatus(int statusId);
	List<Task> findByTodoListId(int todoListId);
}
