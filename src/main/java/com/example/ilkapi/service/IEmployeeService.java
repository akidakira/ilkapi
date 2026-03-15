package com.example.ilkapi.service;

import java.util.List;

import com.example.ilkapi.dto.DepartmentDtoIU;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;

public interface IEmployeeService {

	public EmployeeDto createEmployee(EmployeeDtoIU employee);
	public EmployeeDto updateEmployeeById(Long id, EmployeeDtoIU employee);
	public EmployeeDto findByIdEmployee(Long id);
	public List<EmployeeDto> getAllEmployees();
	public Boolean deleteByIdEmployee(Long id);
}
