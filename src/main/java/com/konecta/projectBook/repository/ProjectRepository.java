package com.konecta.projectBook.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.konecta.projectBook.model.Project;


public interface ProjectRepository extends JpaRepository<Project, Long>{

	@Query(value="FROM Project p WHERE p.active=1")
	List<Project> activeProjects();
	
	@Query(value="FROM Project p WHERE p.name LIKE %?1% OR p.projectStatus.name LIKE %?1% ")
	List<Project> search(String keyword);
	
	@Query(value="SELECT * FROM projects WHERE begin_date AND end_date BETWEEN ?1 AND ?2", nativeQuery=true)
	List<Project> searchWithDates(Date beginDate, Date endDate);
}
