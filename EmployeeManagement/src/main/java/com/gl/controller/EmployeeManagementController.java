package com.gl.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gl.entity.EmployeeManagement;
import com.gl.service.EmployeeManagementService;

@Controller
@RequestMapping("/employeeManagement")
public class EmployeeManagementController {

	private EmployeeManagementService employeeManagementService;
	private Object theEmployeeManagement;
	
	public EmployeeManagementController(EmployeeManagementService theemployeeService) {
		employeeManagementService = theemployeeService;
	}
	
	// add mapping for "/list"

	@GetMapping("/list")
	public String listemployeeMangement(Model theModel) {
		
		
		List<EmployeeManagement> theEmployeeManagement = employeeManagementService.findAll();
		
		// add to the spring model
		theModel.addAttribute("employeeManagement", theEmployeeManagement);
		
		return "employeeManagement/employee-list";
	}
	
	@GetMapping("/showFormForAdd")
	public String showAddForm(Model theModel) {
		EmployeeManagement employeeManagement = new EmployeeManagement();
		
        EmployeeManagement theEmployeeManagement = new EmployeeManagement();
		
		theModel.addAttribute("employeeManagement", theEmployeeManagement);
		
		return "employeeManagement/employee-form";
	}
	
	
	@GetMapping("/listAllEmployees")
	public String listallemployees(Model theModel) {
		
		
		List<EmployeeManagement> theEmployeeManagement = employeeManagementService.findAll();
		
		//adding spring model
		
		theModel.addAttribute("employeeManagement", theEmployeeManagement);
		
		return "employeeManagement/list-all-employees";
	}
	


	@PostMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeManagementId") int theId, Model theModel) {
		
		
		EmployeeManagement theEmployeeManagement = employeeManagementService.findById(theId);
		
		
		theModel.addAttribute("employeeManagement", theEmployeeManagement);
		
		// send over to our form
		return "employeeManagement/employeeUpdate-form";			
	}
	
	
	@PostMapping("/save")
	public String saveEmployeeManagement(@ModelAttribute("employeeManagement") EmployeeManagement theEmployeeManagement) {
		
		
		employeeManagementService.save(theEmployeeManagement);
		
		return "redirect:/employeeManagement/list";
	}
	
	
	@PostMapping("/delete")
	public String delete(@RequestParam("employeeManagementId") int theId) {
		
	
		employeeManagementService.deleteById(theId);
		
		
		return "redirect:/employeeManagement/list";
		
	}
	
}


















