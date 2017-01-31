package fr.icdc.dei.todolist.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.icdc.dei.todolist.persistence.entity.TaskOwner;

public interface TaskOwnerDao extends JpaRepository<TaskOwner, Long>{

}
