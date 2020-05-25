package com.example.demo.service;

import javax.websocket.server.ServerEndpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Backlog;
import com.example.demo.domain.ProjectTask;
import com.example.demo.repositories.BacklogRepository;
import com.example.demo.repositories.ProjectTaskRepository;

@Service
public class ProjectTaskService {
	
	@Autowired
	private BacklogRepository backlogRepository;
	
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		//Exception Project Not found
		
		//Project Tasks to be added into specific project, project!=null, Backlog Exists
		Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
		 
		//Set the backlog to project task
		projectTask.setBacklog(backlog);
		
		Integer backlogSequence= backlog.getPTSequence();
		//Update the backlog sequence
		backlogSequence++;
		backlog.setPTSequence(backlogSequence);
		
		//Add backlogSequence to Project Task
		projectTask.setProjectSequence(projectIdentifier + "-" + backlogSequence);
		
		projectTask.setProjectIdentifier(projectIdentifier);
		
		//Setting the default priority an status
		//If project task is created first then set the priority as 3
		if(projectTask.getPriority()==null) {
			projectTask.setPriority(3);
		}
		
		if(projectTask.getStatus()=="" || projectTask.getStatus()==null) {
			projectTask.setStatus("To-Do");
		}
		
		
	
		return projectTaskRepository.save(projectTask);
	}

}
