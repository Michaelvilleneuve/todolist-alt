package fr.icdc.dei.todolist.persistence.entity;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

import fr.icdc.dei.todolist.persistence.entity.Task;

public class TaskTest {

	private static final String EMPTY_STRING = "";
	private static final String TASK_NAME = "Coucou";
	private static final Task task = new Task();
	
	@BeforeClass
	public static void setUp() {
		task.setName(TASK_NAME);
	}

	@Test
	public void testTaskHasName() {
		assertNotNull(task.getName());
	}
	
	@Test
	public void testTaskNameIsNotEmpty() {
		assertNotEquals(EMPTY_STRING, task.getName());
	}
	
	@Test
	public void testTaskHasBeginDate() {
		task.setBeginDate(new Date());
		assertNotNull(task.getBeginDate());
	}

}
