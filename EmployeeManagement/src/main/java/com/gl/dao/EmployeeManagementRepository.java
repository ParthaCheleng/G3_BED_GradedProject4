package com.gl.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gl.entity.EmployeeManagement;

public interface EmployeeManagementRepository extends JpaRepository<EmployeeManagement, Integer> {

}
