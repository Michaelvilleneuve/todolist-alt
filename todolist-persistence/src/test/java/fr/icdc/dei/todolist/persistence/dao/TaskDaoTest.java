package fr.icdc.dei.todolist.persistence.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import fr.icdc.dei.todolist.persistence.entity.Task;

public class TaskDaoTest extends AsbtractDaoTest {

	private static final String TASK_FINISHED_DATE_MORE_THAN_ONE_MONTH = "11/09/2016";
	private static final int TASK_STATUS_UNFINISHED_ID = 1;
	private static final String TASK_NAME = "Hikingyo";
	
	private SimpleDateFormat dateFormater = new SimpleDateFormat("dd/M/yyyy");

	@Autowired
	private TaskDao taskDao;
	
	private final static Task task = new Task();

	@Test
	public void testCreateTask() {
		task.setName("");
		assertNotNull(taskDao.save(task));
	}
	
	@Test
	public void testFindTaskByName( ){
		assertNotNull(taskDao.findByName(TASK_NAME));
	}
	
	@Test
	public void testFindTaskByNameExactly( ){
		assertEquals(TASK_NAME,taskDao.findByName(TASK_NAME).getName());
	}
	
	@Test
	public void findCorrectAmountOfUnfinishedTasks() {
		assertEquals(taskDao.findAllByStatusId(TASK_STATUS_UNFINISHED_ID).size(), 2);
	}
	
	@Test
	public void listFinishedTaskAfterDate() throws ParseException {
		assertEquals(taskDao.findAllByClosedDateBefore(dateFormater.parse(TASK_FINISHED_DATE_MORE_THAN_ONE_MONTH)).size(), 1);
	}
}
