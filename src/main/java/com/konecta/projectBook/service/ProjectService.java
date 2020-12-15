package com.konecta.projectBook.service;

import java.util.List;

import com.konecta.projectBook.model.Project;


public interface ProjectService {

	List<Project> toListActive();
	List<Project> search(String keyword);
	List<Project> searchByDates(String beginDate, String endDate);
	Project findById(Long id);
	void create(Project project);
	void delete(Long id);
}
