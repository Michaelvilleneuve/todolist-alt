package fr.icdc.dei.todolist.service.impl;

import fr.icdc.dei.todolist.persistence.dao.TaskDao;
import fr.icdc.dei.todolist.persistence.dao.TaskOwnerDao;
import fr.icdc.dei.todolist.persistence.dao.TaskStatusDao;
import fr.icdc.dei.todolist.persistence.dao.UserDao;
import fr.icdc.dei.todolist.persistence.entity.Task;
import fr.icdc.dei.todolist.persistence.entity.TaskOwner;
import fr.icdc.dei.todolist.persistence.entity.TaskStatus;
import fr.icdc.dei.todolist.persistence.entity.User;
import fr.icdc.dei.todolist.service.TodolistService;
import fr.icdc.dei.todolist.service.enums.TaskStatusEnum;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class TodolistServiceImpl implements TodolistService {

    private static final int SEVENDAYS = 7;

    @Autowired
    private UserDao userDao;

    @Autowired
    private TaskDao taskDao;

    @Autowired
    private TaskStatusDao taskStatusDao;

    @Autowired
    private TaskOwnerDao taskOwnerDao;

    @Override
    public List<Task> archiveTasks() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        List<Task> tasksToArchive = taskDao.findAllByClosedDateBefore(cal.getTime());
        for (Task task : tasksToArchive) {
            task.setStatus(new TaskStatus(TaskStatusEnum.ARCHIVED.getValue()));
            taskDao.save(task);
        }
        return tasksToArchive;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Task affectTaskToUser(long idTask, long idUser) {
        Task task = taskDao.findOne(idTask);
        User userReciever = userDao.findOne(idUser);
        TaskOwner taskOwner = new TaskOwner(task, userReciever, false);
        taskOwnerDao.save(taskOwner);
        task.getTaskOwners().add(taskOwner);
        task.setStatus(new TaskStatus(TaskStatusEnum.DELEGATION_PENDING.getValue()));
        return taskDao.save(task);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Task acceptDelegatedTask(long delegatedTaskId, long delegateUserId) {
        Task task = taskDao.findOne(delegatedTaskId);
        task.setStatus(new TaskStatus(TaskStatusEnum.DELEGATED.getValue()));
        return taskDao.save(task);
    }

    @Override
    public List<Task> listTasks() {
        return taskDao.findAll();
    }

    @Override
    public Task addTask(String taskName, int statusId) {
        return taskDao.save(new Task(taskName, statusId));

    }

    @Override
    public List<TaskStatus> listTaskStatus() {
        return taskStatusDao.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public Task changeTaskStatusToFinished(long idTask) {
        Task task = taskDao.findOne(idTask);
        DateTime beginDate = new DateTime(task.getBeginDate());
        DateTime endDate = beginDate.plus(Period.weeks(1));

        if (System.currentTimeMillis() - endDate.getMillis() > 0) {
            task.setStatus(new TaskStatus(TaskStatusEnum.FINISHED.getValue()));
            task.setClosedDate(new Date());
            return taskDao.save(task);
        }
        return task;

    }
}
