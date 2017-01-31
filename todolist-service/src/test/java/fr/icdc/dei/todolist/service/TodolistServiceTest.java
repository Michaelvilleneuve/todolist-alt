package fr.icdc.dei.todolist.service;

import fr.icdc.dei.todolist.persistence.entity.Task;
import fr.icdc.dei.todolist.persistence.entity.TaskStatus;
import fr.icdc.dei.todolist.persistence.entity.User;
import fr.icdc.dei.todolist.service.enums.TaskStatusEnum;
import org.apache.commons.collections.CollectionUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TodolistServiceTest extends AbstractServiceTest {

	private static final long TASK_TO_AFFECT_ID = 1L;
	private static final long DELEGATED_TASK_ID = 2L;
	private static final long USER_ID = 1L;
	private static final long DELEGATE_USER_ID = 2L;

	private static User user = new User();
	private static Task task = new Task();
	private static TaskStatus taskStatus = new TaskStatus();

	@Autowired
	private TodolistService todolistService;

	@BeforeClass
	public static void setUp() {
		user.setId(USER_ID);
		task.setId(TASK_TO_AFFECT_ID);
	}

	@Test
	public void testArchiveFinishedTasksSinceOneMonth() {
		assertTrue(CollectionUtils.isNotEmpty(todolistService.archiveTasks()));
	}

	@Test
	public void testAffectTaskToAnotherUser() {
		assertEquals(todolistService.affectTaskToUser(task.getId() , user.getId()).getTaskOwners().size(), 2);
	}

	@Test
	public void testAffectTaskToAnotherUserGotPendingStatus() {
		assertEquals(todolistService.affectTaskToUser(task.getId() , user.getId()).getStatus().getId(), TaskStatusEnum.DELEGATION_PENDING.getValue());
	}

	@Test
	public void testAcceptDelegatedTask() {
		assertEquals(todolistService.acceptDelegatedTask(DELEGATED_TASK_ID, DELEGATE_USER_ID).getStatus().getId(), TaskStatusEnum.DELEGATED.getValue());
	}

	@Test
	public void testAddTaskWorks() {
		assertTrue(todolistService.addTask("taskName", 1).getId() > 0);
	}

	@Test
	public void testListTaskStatus() {
		assertTrue(CollectionUtils.isNotEmpty(todolistService.listTaskStatus()));
	}

	@Test
	public void testChangeTaskStatusToFinished(){
		assertEquals(TaskStatusEnum.FINISHED.getValue(), todolistService.changeTaskStatusToFinished(DELEGATED_TASK_ID).getStatus().getId());
	}

	@Test
	public void testCannotFinishTaskIfOpenTooRecently(){
		taskStatus.setId(TaskStatusEnum.DELEGATION_PENDING.getValue());
		task.setStatus(taskStatus);
		assertEquals(task.getStatus().getId(), todolistService.changeTaskStatusToFinished(task.getId()).getStatus().getId());
	}
}
