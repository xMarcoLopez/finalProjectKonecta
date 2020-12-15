package com.konecta.projectBook.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konecta.projectBook.model.Project;
import com.konecta.projectBook.model.ProjectUpdate;

public interface ProjectUpdateRepository extends JpaRepository <ProjectUpdate, Long>{

	List<ProjectUpdate> findByProject(Project project);
}
