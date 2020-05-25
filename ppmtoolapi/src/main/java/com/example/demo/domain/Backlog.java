package com.example.demo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;





@Entity
public class Backlog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Integer PTSequence =0;
	public String projectIdentifier;
	
	
	//one to one with project
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="project_id", nullable= false)
	@JsonIgnore
	private Project project;
	
	
	// one to many with project task
	
	
	@OneToMany(fetch= FetchType.EAGER, cascade= CascadeType.ALL, mappedBy= "backlog")
	private List<ProjectTask> projectTasks = new ArrayList<>();
	
	public List<ProjectTask> getProjectTasks() {
		return projectTasks;
	}

	public void setProjectTasks(List<ProjectTask> projectTasks) {
		this.projectTasks = projectTasks;
	}

	public Backlog() {
		super();
		
	}
	
	//setter getter of project
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	//setter getter of all the fields
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public Integer getPTSequence() {
		return PTSequence;
	}


	public void setPTSequence(Integer pTSequence) {
		PTSequence = pTSequence;
	}


	public String getProjectIdentifier() {
		return projectIdentifier;
	}


	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}


	
	
	
	
	

}
