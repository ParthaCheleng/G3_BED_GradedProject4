package com.gl.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gl.dao.EmployeeManagementRepository;
import com.gl.entity.EmployeeManagement;

@Service
public class EmployeeManagementServiceImpl implements EmployeeManagementService {

	
	private EmployeeManagementRepository employeeManagementRepository;

	@Autowired
	public EmployeeManagementServiceImpl(EmployeeManagementRepository theemployeeManagementRepository) {
		employeeManagementRepository = theemployeeManagementRepository;
	}

	@Override
	public List<EmployeeManagement> findAll() {

		List<EmployeeManagement> theEmployeeManagement = employeeManagementRepository.findAll();
		return theEmployeeManagement;
	}

	@Override
	public EmployeeManagement findById(int theId) {
		Optional<EmployeeManagement> result = employeeManagementRepository.findById(theId);

		EmployeeManagement theemployeeManagement = null;

		if (result.isPresent()) {
			theemployeeManagement = result.get();
		} else {
			
			throw new RuntimeException("Did not find employee id - " + theId);
		}

		return theemployeeManagement;
	}

	@Override
	public void save(EmployeeManagement theemployeeManagement) {
		employeeManagementRepository.save(theemployeeManagement);
	}

	@Override
	public void deleteById(int theId) {
		employeeManagementRepository.deleteById(theId);
	}

}
