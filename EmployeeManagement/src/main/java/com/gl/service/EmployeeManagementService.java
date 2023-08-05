package com.gl.service;

import java.util.List;

import org.springframework.ui.Model;

import com.gl.entity.EmployeeManagement;

public interface EmployeeManagementService {

	public List<EmployeeManagement> findAll();
	
	public EmployeeManagement findById(int theId);
	
	public void save(EmployeeManagement theEmployeeManagement);
	
	public void deleteById(int theId);

		
}
