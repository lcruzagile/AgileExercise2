package main.java.com.todolist;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import main.java.com.todolist.TodoList.TodoList;
import main.java.com.todolist.service.TodoListService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AgileJavaExercise2TodoListApplicationTests {
	
    @Autowired 
    private TodoListService todoListService;
	
	public static void main(String[] args) {
		SpringApplication.run(AgileJavaExercise2TodoListApplication.class, args);
	}
	
	@Test
	public void contextLoads() {
		this.todoListService.addTodoList(new TodoList("test app"));
	}
	
	@Test
	public void addTodoListTest() {
		this.todoListService.addTodoList(new TodoList("test app 2"));

		assertThat(this.todoListService.getTodoListByName("test app 2").getName().equals("test app 2"));
	}
	
	@Test
	public void getTodoListTest() {
		assertThat(this.todoListService.getTodoList(1).getName().equals("test app"));
	}
	
	@Test
	public void updateTodoListTest() {
		
		TodoList todoList = new TodoList("test app update");
		todoList.setId(1);
		this.todoListService.updateTodoList(todoList);
		assertThat(this.todoListService.getTodoList(1).getName().equals("test app update"));
	}
	
	@Test
	public void addTodoListByNameTest() {
		assertThat(this.todoListService.getTodoListByName("test app update").getName().equals("test app update"));
	}
}
