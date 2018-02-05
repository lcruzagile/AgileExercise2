package main.java.com.todolist;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import main.java.com.todolist.repository.TaskRepository;
import main.java.com.todolist.task.Task;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TodoListTest {
	
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private TaskRepository taskRepository;

	@Test
	public void addTaskTest() {
		this.entityManager.persist(new Task(1, "1234"));
        Task task = this.taskRepository.findByStatus(1).get(0);
        assertThat(task.getStatus()).isEqualTo(1);
        assertThat(task.getDescription()).isEqualTo("1234");
	}

}
