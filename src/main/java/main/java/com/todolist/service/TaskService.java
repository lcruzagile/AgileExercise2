package main.java.com.todolist.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.todolist.TodoList.TodoList;
import main.java.com.todolist.exception.NotFoundException;
import main.java.com.todolist.repository.TaskRepository;
import main.java.com.todolist.task.Task;


@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;

	// Creates logger
	final Logger logger = LogManager.getLogger(TaskService.class);

	public List<Task> getTodoList(){
		return (List<Task>) this.taskRepository.findAll();	
	}

	public Task getTask(int id) {
		Task task = this.taskRepository.findOne(id);
		if(task == null) {
			throw new NotFoundException("List not found: "+id);
		}		
		return this.taskRepository.findOne(id);
	}

	public void addTask(Task task) throws EntityExistsException {

		Integer id = task.getId();

		if (this.logger.isDebugEnabled()) {
			this.logger.debug(task.toString());
		}

		try {
			if ( this.taskRepository.exists(id)) {
				throw new EntityExistsException(task.toString());
			}
			this.taskRepository.save(task);
		}catch(IllegalArgumentException e){
			this.logger.error(e.getMessage());
		}catch(EntityExistsException e) {
			this.logger.error(e.getMessage());
		}
	}

	public void deleteTask(int taskId) throws NotFoundException{
		try {
			if(!this.taskRepository.exists(taskId)) {
				throw new NotFoundException("Task not found "+ String.valueOf(taskId));
			}
			this.taskRepository.delete(taskId);
		}catch (IllegalArgumentException e){
			this.logger.error(e.getMessage());
		}
	}

	public void updateTask(Task task) throws NotFoundException{
		try {
			if(!this.taskRepository.exists(task.getId())) {
				throw new NotFoundException("Task not found "+ task.toString());
			}
			this.taskRepository.save(task);
		}catch (IllegalArgumentException e){
			this.logger.error(e.getMessage());
		}
	}

	public List<Task> getTodoListByStatus(int statusId) {
		return this.taskRepository.findByStatus(statusId);	
	}
	
	public List<Task> getTodoListByStatus(int todoListId,int statusId) {
		List<Task> tasks =  new ArrayList<Task>();
		this.taskRepository.findByTodoListId(todoListId).stream(). filter( t -> t.getStatus() == statusId ).forEach(t -> tasks.add(t));
		return tasks;
	}

	public List<Task> getTaskCountByStatus(int statusId, LocalDate filterDate) {
		List<Task> tasks =  new ArrayList<Task>();
		this.taskRepository.findByStatus(statusId).stream(
						).filter( t -> t.getDueDate().equals(filterDate.toString()) 
								).forEach(t -> tasks.add(t)); 
		return tasks;
	}

	public List<Task> getTaskCountByStatus(int todoListId, int statusId, LocalDate filterDate) {
		List<Task> tasks =  new ArrayList<Task>();
		
		this.taskRepository.findByTodoListId(todoListId).stream().filter( t -> t.getStatus() == statusId ).filter( 
				t -> t.getDueDate().equals(filterDate.toString())
								).forEach(t -> tasks.add(t)); 
		return tasks;
	}

	public List<Task> getTaskByTodoList(int todoListId){
		return this.taskRepository.findByTodoListId(todoListId);
	}

	public TodoList getTodoList(int taskId) {
		return this.taskRepository.findOne(taskId).getTodoList();
	}
}