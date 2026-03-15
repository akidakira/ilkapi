package com.example.ilkapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ilkapi.entity.Department;

@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long>{

}
