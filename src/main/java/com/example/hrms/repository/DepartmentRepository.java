package com.example.hrms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hrms.model.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer>{
    
}
