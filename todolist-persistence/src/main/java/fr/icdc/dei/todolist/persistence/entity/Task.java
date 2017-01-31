package fr.icdc.dei.todolist.persistence.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_task")
	private long id;
	
	@Column
	private String name;
	
	@Column
	private Date beginDate;
	
	@Column
	private Date closedDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_task_status")
	private TaskStatus status;
	
	@OneToMany
    private List<TaskOwner> taskOwners;

	public Task(String taskName, int statusId) {
		this.name = taskName;
		this.beginDate = new Date();
		this.closedDate = new Date();
		this.status = new TaskStatus(statusId);
	}
	
	public Task() {}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TaskStatus getStatus() {
		return status;
	}

	public void setStatus(TaskStatus status) {
		this.status = status;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public List<TaskOwner> getTaskOwners() {
		return taskOwners;
	}

	public void setTaskOwners(List<TaskOwner> taskOwners) {
		this.taskOwners = taskOwners;
	}

}
