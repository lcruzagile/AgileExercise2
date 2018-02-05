package main.java.com.todolist.task;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TASK")
public class Task {
	
	
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int id;
	@Column(name = "STATUS")
	private int status;//0=initial,1=in progress, 2=done
	@NotNull(message = "Description cannot be null")
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DUE_DATE")
	private String dueDate = LocalDate.now().toString();

	public Task() {
	}
	
	public Task(int status, String description) {
		this.status = status;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}	
	@Override
	public String toString() {
		return " \"TASK:\" {\"id\":"+this.id+", \"description\":\""+this.description+"\", \"status\":\""+this.status+"\" }";
	}

}
