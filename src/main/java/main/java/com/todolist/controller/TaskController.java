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

import javassist.NotFoundException;
import main.java.com.todolist.service.TaskService;
import main.java.com.todolist.task.Task;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;
	
	// Creates logger
	final Logger logger = LogManager.getLogger(TaskController.class);

	@RequestMapping("/todolist")
	public List<Task> getTodoList(){
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist");
		return this.taskService.getTodoList();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/todolist/task")
	public void addTask(@RequestBody Task task) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/task "+task.toString());
		this.taskService.addTask(task);
	}
	
	@RequestMapping("/todolist/{id}")
	public Task getTask(@PathVariable("id") int id ) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/{id} "+id);
		
		return this.taskService.getTask(id);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/todolist/task/{id}")
	public void updateTask(@PathVariable("id") int id, @RequestBody Task task) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/task/{id} "+task);
		
		try {
			this.taskService.updateTask(task);
		} catch (NotFoundException e) {
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/todolist/task/{id}")
	public void deleteTask(@PathVariable("id") int id) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/task/{id} "+id);
		
		try {
			this.taskService.deleteTask(id);
		} catch (NotFoundException e) {
			logger.error(e.getMessage());
		}
	}
	
	@RequestMapping("/todolist/status/{statusId}")
	public List<Task> getTodoListByStatus(@PathVariable("statusId") int statusId) {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/status/{statusId} "+statusId);
		
		return this.taskService.getTodoListByStatus(statusId);
	}
	
	@RequestMapping("/todolist/fortoday")
	public List<Task> getTaskCountByStatus() {
		if(this.logger.isDebugEnabled())
			this.logger.debug("/todolist/fortoday");
		
		return this.taskService.getTaskCountByStatus(0,LocalDate.now());
	}
	
}
