package main.java.com.todolist.controller;

import java.time.LocalDate;
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
import main.java.com.todolist.exception.NotFoundException;
import main.java.com.todolist.service.TaskService;
import main.java.com.todolist.service.TodoListService;
import main.java.com.todolist.task.Task;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	@Autowired
	private TodoListService todoListService;
	
	// Creates logger
	final Logger logger = LogManager.getLogger(TaskController.class);

	@RequestMapping("/todo-list/{id}/task")
	public List<Task> getTasksForTodoList(@PathVariable("id") int id ) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todo-list/{id}/task/ "+id);
		
		return this.todoListService.getTodoList(id).getTasks();
		//return this.taskService.getTaskByTodoList(id);
	}
	
	@RequestMapping("/todo-list/task/{id}")
	public Task getTask(@PathVariable("id") int id ) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todo-list/task/{id} "+id);
		
		return this.taskService.getTask(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/todo-list/{todoListId}/task")
	public void addTask(@PathVariable("todoListId") int todoListId, @RequestBody Task task) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todo-list/task "+task.toString());
		this.todoListService.addTask(todoListId, task);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/todo-list/{todoListId}/task/{id}")
	public void updateTask(@PathVariable("id") int id, @RequestBody Task task) throws NotFoundException {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/task/{id} "+task);
		
			this.taskService.updateTask(task);
	}

	@RequestMapping(method=RequestMethod.DELETE, value="/todo-list/{todoListId}/task/{id}")
	public void deleteTask(@PathVariable("todoListId") int todoListId, @PathVariable("id") int id) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/task/{id} "+id);

		TodoList todoList = this.todoListService.getTodoList(todoListId);
		Task task = this.taskService.getTask(id);

		todoList.getTasks().remove(task);

		this.todoListService.updateTodoList(todoList);
	}
	
	@RequestMapping("/todo-list/{todoListId}/status/{statusId}")
	public List<Task> getTodoListByStatus(@PathVariable("todoListId") int todoListId, @PathVariable("statusId") int statusId) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/status/{statusId} "+statusId);

		return this.taskService.getTodoListByStatus(todoListId, statusId); 
	}
	
	@RequestMapping("/todo-list/for-today")
	public List<Task> getTaskCountByStatus() {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/fortoday");
		
		return this.taskService.getTaskCountByStatus(0,LocalDate.now());
	}
	
}
