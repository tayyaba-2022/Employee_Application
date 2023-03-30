package com.employee.controller;

import java.util.List;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.employee.entity.Employee;
import com.employee.service.EmployeeService;

@Controller
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@Value("${application.default.page}")
	private String defaultPage; 
	
	@RequestMapping("/welcome")
	public ModelAndView welcome() {
		ModelAndView mv = new ModelAndView();  
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth.isAuthenticated());
		if(!auth.getName().equalsIgnoreCase("anonymousUser")) {
			mv.addObject("username", auth.getName());
			mv.addObject("isAuthenticated", true);
		}else {
			mv.addObject("isAuthenticated", false);
		}
		mv.setViewName("welcome");
		
	    return mv;
	}
	
	@RequestMapping("/home")
	public ModelAndView getAllEmployees() {
		ModelAndView mv =  new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	 
		boolean isAdmin=auth.getAuthorities().stream()
				.anyMatch(e->e.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
	    mv.addObject("username", auth.getName());
	    mv.addObject("isadmin",isAdmin);
		List<Employee> employees = employeeService.getAllEmployees();
		mv.addObject("employees", employees);
		mv.setViewName(defaultPage);
		return mv;
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addEmployee(Employee employee) {
		ModelAndView mv =  new ModelAndView();
		Employee employeeAdded = employeeService.addEmployee(employee);
		mv.addObject("employeeAdded", employeeAdded);
		List<Employee> employees = employeeService.getAllEmployees();
		mv.addObject("employees", employees);
		mv.setViewName("home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		 
		boolean isAdmin=auth.getAuthorities().stream()
				.anyMatch(e->e.getAuthority().equalsIgnoreCase("ROLE_ADMIN"));
	   // mv.addObject("username", auth.getName());
	    mv.addObject("isadmin",isAdmin);
		return mv;
	}
	
	@PreDestroy
	public void preDestroy() {
		System.out.println("In pre destroy");
	}
}
