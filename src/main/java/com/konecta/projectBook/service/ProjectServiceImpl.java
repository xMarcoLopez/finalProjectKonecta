package com.konecta.projectBook.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.konecta.projectBook.model.Project;
import com.konecta.projectBook.repository.ProjectRepository;

@Service
public class ProjectServiceImpl implements ProjectService{

	@Autowired
	ProjectRepository projectRepository;

	@Override
	public List<Project> toListActive() {	
		return projectRepository.activeProjects();
	}

	@Override
	public Project findById(Long id) {
		return projectRepository.findById(id).orElse(null);
	}

	@Override
	public List<Project> search(String keyword) {
		return projectRepository.search(keyword);
	}
	
	@Override
	public List<Project> searchByDates(String beginDate, String endDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDateToSearch = null;
        Date endDateToSearch = null;
        try {
        	beginDateToSearch = format.parse(beginDate);
        	endDateToSearch = format.parse(endDate);
        } 
        catch (ParseException ex) 
        {
            System.out.println(ex);
        }
		return projectRepository.searchWithDates(beginDateToSearch, endDateToSearch);
	}
	
	@Override
	public void create(Project project) {
		projectRepository.save(project);	
	}

	@Override
	public void delete(Long id) {
		projectRepository.deleteById(id);	
	}

}
