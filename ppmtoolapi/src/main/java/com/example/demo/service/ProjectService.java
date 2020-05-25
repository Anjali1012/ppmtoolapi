package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Backlog;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectTask;
import com.example.demo.exception.ProjectIdException;
import com.example.demo.repositories.BacklogRepository;
import com.example.demo.repositories.ProjectRepository;
import com.example.demo.repositories.ProjectTaskRepository;

@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private BacklogRepository backlogRepository;
	

	public Project saveOrUpdateProject(Project project) {

		try {
			// While saving the project, backlog should be saved
			// When project is not having id, it is a new project - you need to create a
			// backlog

			if (project.getId() == null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);// one to one
				backlog.setProject(project);// one to one relatiionship
				backlog.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());

			}

			// In case of updating the project, backlog should not be null, same
			// projectIdentifier
			// should be set in the backlog

			if (project.getId() != null) {
				project.setBacklog(
						backlogRepository.findByProjectIdentifier(project.getProjectIdentifier().toUpperCase()));
			}

			return projectRepository.save(project);
		} catch (Exception ex) {
			throw new ProjectIdException(
					"Project Id '" + project.getProjectIdentifier().toUpperCase() + "' already exists");
		}

	}

	public Project findProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
		if (project == null) {
			throw new ProjectIdException("Project Id '" + projectId.toUpperCase() + "' does not exists");
		}
		return project;
	}

	public Iterable<Project> findAllProjcts() {

		return projectRepository.findAll();
	}

	public void deleteProjectByIdentifier(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

		if (project == null) {
			throw new ProjectIdException(
					"Cannot delete Project with ID '" + projectId + "'. This project does not exist");
		}

		projectRepository.delete(project);
	}

}
