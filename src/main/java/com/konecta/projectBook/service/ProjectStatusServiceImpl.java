package com.konecta.projectBook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konecta.projectBook.model.ProjectStatus;
import com.konecta.projectBook.repository.ProjectStatusRepository;

@Service
public class ProjectStatusServiceImpl implements ProjectStatusService{

	@Autowired
	ProjectStatusRepository projectStatusRepository;
	
	@Override
	public List<ProjectStatus> toList() {
		
		return projectStatusRepository.findAll();
	}

}
