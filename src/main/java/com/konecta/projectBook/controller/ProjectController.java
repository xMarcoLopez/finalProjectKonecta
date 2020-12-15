package com.konecta.projectBook.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.konecta.projectBook.model.Project;
import com.konecta.projectBook.model.ProjectStatus;
import com.konecta.projectBook.model.ProjectUpdate;
import com.konecta.projectBook.model.User;
import com.konecta.projectBook.service.ProjectService;
import com.konecta.projectBook.service.ProjectStatusService;
import com.konecta.projectBook.service.ProjectUpdateService;
import com.konecta.projectBook.service.UserService;

@Controller
@RequestMapping("/views/projects")
public class ProjectController {

	@Autowired
	ProjectService projectService;
	
	@Autowired
	ProjectStatusService projectStatusService;
	
	@Autowired
	ProjectUpdateService projectUpdateService;
	
	@Autowired
	UserService userService;
	
	@GetMapping(value= {"/index", "/"})
	public String toList(Authentication auth, HttpSession session, Model model) {
		String username = auth.getName();
		if(session.getAttribute("user") == null) {
			User user = userService.findByUsername(username);
			user.setPassword(null);
			session.setAttribute("user", user);
		}
		List<Project> projectsList = projectService.toListActive();
		List<ProjectUpdate> projectUpdateList = projectUpdateService.toList();
		model.addAttribute("projectList", projectsList);
		model.addAttribute("projectUpdateList", projectUpdateList);
		return "views/projects/index";	
	}
	
	@GetMapping("/create")
	public String create(Model model) {
		Project project = new Project();
		List<ProjectStatus> listProjectStatus = projectStatusService.toList();
		model.addAttribute("title", "Crear Proyecto");
		model.addAttribute("project", project);
		model.addAttribute("projectsStatus", listProjectStatus);
		
		return "views/projects/create";	
	}
	
	@PostMapping("/save")
	public String save (@ModelAttribute Project project) {
		project.setCreationDate(new Date());
		project.setTerm(project.termCalculator(project.getEndDate(), project.getBeginDate()));
		project.setActive(1);
		projectService.create(project);
		return "redirect:/views/projects/index";
	}
	
	@GetMapping("/edit/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		Project project = projectService.findById(id);
		List<ProjectStatus> listProjectStatus = projectStatusService.toList();
		
		model.addAttribute("title", "Editar Proyecto");
		model.addAttribute("project", project);
		model.addAttribute("projectsStatus", listProjectStatus);
		
		return "/views/projects/update";	
	}
	
	@PostMapping("/update")
	public String update (@ModelAttribute Project project, Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		ProjectUpdate projectUpdate = new ProjectUpdate();
		projectUpdate.setUpdateDate(new Date());
		projectUpdate.setAction("update");
		projectUpdate.setProject(project);
		projectUpdate.setUser(user);
		project.setTerm(project.termCalculator(project.getEndDate(), project.getBeginDate()));
		projectService.create(project);
		projectUpdateService.create(projectUpdate);
		return "redirect:/views/projects/index";
	}
	
	@GetMapping("/delete/{id}")
	public String delete (@PathVariable("id") Long id, Authentication auth) {
		User user = userService.findByUsername(auth.getName());
		Project project = projectService.findById(id);
		project.setActive(0);
		ProjectUpdate projectUpdate = new ProjectUpdate();
		projectUpdate.setUpdateDate(new Date());
		projectUpdate.setAction("delete");
		projectUpdate.setProject(project);
		projectUpdate.setUser(user);
		projectService.create(project);
		projectUpdateService.create(projectUpdate);
		return "redirect:/views/projects/index";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable("id") Long id, Model model) {
		Project project = projectService.findById(id);
		List<ProjectUpdate> projectUpdateList = projectUpdateService.findByProject(project);
		
		model.addAttribute("projectUpdateList", projectUpdateList);
		
		return "/views/projects/detail";	
	}
	
	@GetMapping("/search")
	public String search(@RequestParam(name ="keyword") String keyword, Model model) {
		List<Project> searchList = projectService.search(keyword);
		
		model.addAttribute("searchList", searchList);
		return "/views/projects/search";
	}
	
	@GetMapping("/searchByDates")
	public String searchByDates(@RequestParam(name ="beginDateSearch") String beginDateSearch, 
			@RequestParam(name ="endDateSearch") String endDateSearch, Model model) {
		List<Project> searchByDateList = projectService.searchByDates(beginDateSearch, endDateSearch);	
		model.addAttribute("searchByDateList", searchByDateList);
		return "/views/projects/searchByDates";
	}
}
