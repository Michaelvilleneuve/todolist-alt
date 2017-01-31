package fr.icdc.dei.todolist.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tasks_owners")
public class TaskOwner {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_task_owner")
	private long id;
	
	@ManyToOne
	@JoinColumn(name = "id_task")
	private Task task;
	
	@ManyToOne
	@JoinColumn(name = "id_user")
	private User owner;
	
	@Column
	private boolean creator;
	
	public TaskOwner(Task task, User owner, boolean creator) {
		this.task = task;
		this.owner = owner;
		this.creator = creator;
	}

	public TaskOwner() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public boolean isCreator() {
		return creator;
	}

	public void setCreator(boolean creator) {
		this.creator = creator;
	}

}
