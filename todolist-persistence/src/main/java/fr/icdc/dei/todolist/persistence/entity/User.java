package fr.icdc.dei.todolist.persistence.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_user")
	private long id;
	
	@Column
	private String name;
	
	@OneToMany(fetch=FetchType.LAZY)
    private List<TaskOwner> taskOwners;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TaskOwner> getTaskOwners() {
		return taskOwners;
	}

	public void setTaskOwners(List<TaskOwner> taskOwners) {
		this.taskOwners = taskOwners;
	}

}
