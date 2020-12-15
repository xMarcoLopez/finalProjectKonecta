package com.konecta.projectBook.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.konecta.projectBook.model.ProjectStatus;

public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Long>{

}
