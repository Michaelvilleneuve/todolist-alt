package fr.icdc.dei.todolist.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.icdc.dei.todolist.persistence.entity.TaskStatus;

public interface TaskStatusDao extends JpaRepository<TaskStatus, Integer>{

}
