package com.konecta.projectBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konecta.projectBook.model.Project;
import com.konecta.projectBook.model.ProjectUpdate;
import com.konecta.projectBook.repository.ProjectUpdateRepository;

@Service
public class ProjectUpdateServiceImpl implements ProjectUpdateService{

	@Autowired
	ProjectUpdateRepository projectUpdateRepository;
	
	@Override
	public void create(ProjectUpdate projectUpdate) {
		projectUpdateRepository.save(projectUpdate);
	}

	@Override
	public List<ProjectUpdate> toList() {
		return projectUpdateRepository.findAll();
	}

	@Override
	public List<ProjectUpdate> findByProject(Project project) {
		return projectUpdateRepository.findByProject(project);
	}

}
