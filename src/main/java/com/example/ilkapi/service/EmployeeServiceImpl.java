package com.example.ilkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.prefs.NodeChangeListener;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ilkapi.apiresponse.ResponseType;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.entity.Employee;
import com.example.ilkapi.exception.BaseException;
import com.example.ilkapi.repository.IDepartmentRepository;
import com.example.ilkapi.repository.IEmployeeRepository;

@Service
public class EmployeeServiceImpl implements IEmployeeService{

	@Autowired
	private IEmployeeRepository employeeRepository;
	
	@Autowired
	private IDepartmentRepository departmentRepository;
	
	
	@Override
	public EmployeeDto createEmployee(EmployeeDtoIU employee) {
		
		Optional<Department> optional = departmentRepository.findById(employee.getDepartmentId());
		
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.DEPARTMENT_NOT_FOUND);
		}
		
		Department department = optional.get();
		DepartmentDto departmentDto = new DepartmentDto();
		EmployeeDto employeeDto = new EmployeeDto();
		Employee dbEmployee = new Employee();
		
		BeanUtils.copyProperties(employee, dbEmployee);
		
		dbEmployee.setDepartment(department);
		
		BeanUtils.copyProperties(department, departmentDto);

		Employee savedEmployee = employeeRepository.save(dbEmployee);
		
		BeanUtils.copyProperties(savedEmployee, employeeDto);
		
		employeeDto.setDepartmentDto(departmentDto);
		
		return employeeDto;
		
	}

	@Override
	public EmployeeDto findByIdEmployee(Long id) {
		
		Optional<Employee> optional = employeeRepository.findById(id);
		
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.EMPLOYEE_NOT_FOUND);
		}
		
		EmployeeDto employeeDto = new EmployeeDto();
		DepartmentDto departmentDto = new DepartmentDto();
		
		Employee employee = optional.get();
		Department department = optional.get().getDepartment();
		
		BeanUtils.copyProperties(department, departmentDto);
		BeanUtils.copyProperties(employee, employeeDto);
		
		employeeDto.setDepartmentDto(departmentDto);
		
		return employeeDto;
		
	}

	@Override
	public List<EmployeeDto> getAllEmployees() {
		List<Employee> dbEmployeeList = employeeRepository.findAll();
		ArrayList<EmployeeDto> employeeDtoList = new ArrayList<>();
		if(dbEmployeeList.isEmpty()) {
			throw new BaseException(ResponseType.LIST_NOT_FOUND);
		}
		for (Employee employee : dbEmployeeList) {
			
			EmployeeDto employeeDto = new EmployeeDto();
			Department department = employee.getDepartment();
			DepartmentDto departmentDto = new DepartmentDto();
			
			BeanUtils.copyProperties(department, departmentDto);
			BeanUtils.copyProperties(employee, employeeDto);
			
			employeeDto.setDepartmentDto(departmentDto);
			
			employeeDtoList.add(employeeDto);
		}
		return employeeDtoList;
	}

	@Override
	public Boolean deleteByIdEmployee(Long id) {
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.EMPLOYEE_NOT_FOUND);
		}
		Employee dbEmployee = optional.get();
		employeeRepository.delete(dbEmployee);
		return true;
	}

	@Override
	public EmployeeDto updateEmployeeById(Long id, EmployeeDtoIU employee) {
		
		Optional<Employee> optional = employeeRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.EMPLOYEE_NOT_FOUND);
		}
		
		Employee dbEmployee = optional.get();
		Department department = optional.get().getDepartment();
		
		EmployeeDto employeeDto = new EmployeeDto();
		DepartmentDto departmentDto = new DepartmentDto();
		
		dbEmployee.setEmployeeName(employee.getEmployeeName());
		dbEmployee.setEmployeeLastname(employee.getEmployeeLastname());
		dbEmployee.setEmployeeBirthDate(employee.getEmployeeBirthDate());
		
		employeeRepository.save(dbEmployee);
		
		BeanUtils.copyProperties(department, departmentDto);
		BeanUtils.copyProperties(dbEmployee, employeeDto);
		
		employeeDto.setDepartmentDto(departmentDto);
		
		return employeeDto;
	}

}
