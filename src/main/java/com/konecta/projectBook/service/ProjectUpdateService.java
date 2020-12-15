package com.konecta.projectBook.service;

import java.util.List;

import com.konecta.projectBook.model.Project;
import com.konecta.projectBook.model.ProjectUpdate;

public interface ProjectUpdateService {

	List<ProjectUpdate> toList();
	void create(ProjectUpdate projectUpdate);
	List<ProjectUpdate> findByProject(Project project);
}
