package com.example.ilkapi.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.booleanThat;
import static org.mockito.ArgumentMatchers.longThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.entity.Employee;
import com.example.ilkapi.exception.BaseException;
import com.example.ilkapi.repository.IDepartmentRepository;
import com.example.ilkapi.repository.IEmployeeRepository;
import com.example.ilkapi.service.DepartmentServiceImpl;
import com.example.ilkapi.service.EmployeeServiceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.List;


@ExtendWith(MockitoExtension.class)
public class EmployeeServiceImplTest {
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;
	
	@Mock
	private IEmployeeRepository employeeRepository;
	
	@Mock
	private IDepartmentRepository departmentRepository;

	
	private final Long departmentId = 1L;
	Department department = new Department();
	
	private final Long employeeId = 1L;
	EmployeeDtoIU employeeDtoIU = new EmployeeDtoIU();
	Employee employee = new Employee();
	
	@BeforeEach
	public void employeeAndDepartmentSetUp() {
		
		department.setDepartmentId(departmentId);
		department.setDepartmentName("department-name");
		department.setDepartmentLocation("department-location");
				
		employee.setEmployeeId(employeeId);
		employee.setEmployeeName("employee-name");
		employee.setEmployeeLastname("employee-lastname");
		employee.setEmployeeBirthDate(Date.valueOf("1999-05-20"));
		employee.setDepartment(department);
		
		employeeDtoIU.setEmployeeName("employee-name");
		employeeDtoIU.setEmployeeLastname("employee-lastname");
		employeeDtoIU.setEmployeeBirthDate(Date.valueOf("1999-02-05"));
		employeeDtoIU.setDepartmentId(departmentId);
	}
	
	@Test
	public void createEmployeeTest() {
		
		
		when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
		when(employeeRepository.save(any(Employee.class))).thenReturn(employee);
		
		
		EmployeeDto result = employeeService.createEmployee(employeeDtoIU);
		
		
		assertNotNull(result);
		assertEquals("employee-name", result.getEmployeeName());
		assertEquals(employeeId, result.getEmployeeId());
		
		assertEquals("department-name", result.getDepartmentDto().getDepartmentName());
		
		
		verify(departmentRepository).findById(departmentId);
		verify(employeeRepository).save(any(Employee.class));
	}
	
	@Test
	public void findByIdEmployeeTest() {
		
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		
		EmployeeDto result = employeeService.findByIdEmployee(employeeId);
		
		assertNotNull(result);
		assertEquals("employee-name", result.getEmployeeName());
		assertEquals(employeeId, result.getEmployeeId());
		assertNotNull(result.getDepartmentDto());
		
		verify(employeeRepository).findById(employeeId);
		
	}
	
	@Test
	public void findAllEmployeesTest() {
		
		List<Employee> employeeList = List.of(employee);
		
		when(employeeRepository.findAll()).thenReturn(employeeList);
		
		List<EmployeeDto> result = employeeService.getAllEmployees();
		
		assertEquals(1, result.size());
		
		assertNotNull(result);
		assertEquals("employee-name", result.get(0).getEmployeeName());
		assertEquals(employeeId, result.get(0).getEmployeeId());
		assertNotNull(result.get(0).getDepartmentDto());
		
		verify(employeeRepository).findAll();
	}
	
	@Test
	public void deleteByIdEmployeeTest() {
		
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		
		Boolean result = employeeService.deleteByIdEmployee(employeeId);
		
		assertTrue(result);
		
		verify(employeeRepository).delete(employee);
		
	}
	
	@Test
	public void updateEmployeeByIdTest() {
		
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
		
		EmployeeDtoIU newEmployee = new EmployeeDtoIU();
		newEmployee.setEmployeeName("new-employee-name");
		newEmployee.setEmployeeLastname("new-employee-lastname");
		newEmployee.setEmployeeBirthDate(Date.valueOf("1995-03-06"));
		
		EmployeeDto result = employeeService.updateEmployeeById(employeeId, newEmployee);
		
		assertNotNull(result);
		assertEquals(employeeId,result.getEmployeeId());
		assertEquals("new-employee-name", result.getEmployeeName());
		
		verify(employeeRepository).findById(employeeId);
		verify(employeeRepository).save(any(Employee.class));
	}
	
	//Exception Test
	@Test
	public void throwEmployeeNotFoundException() {
		when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
		
		BaseException employeeNotFoundException = assertThrows(BaseException.class, () ->{
			employeeService.findByIdEmployee(employeeId);
		});
		
		assertEquals("Employee Not Found", employeeNotFoundException.getMessage());
		
		verify(employeeRepository).findById(employeeId);
		
	}
	
	@Test
	public void throwDepartmentNotFoundException() {
		when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());
		
		BaseException departmentNotFoundException = assertThrows(BaseException.class, () ->{
			departmentService.findByIdDepartment(departmentId);
		});
		
		assertEquals("Department Not Found", departmentNotFoundException.getMessage());
		
		verify(departmentRepository).findById(departmentId);
	}
	
	
	@Test
	public void throwListNotFoundException() {
		//List<Employee> dbEmployeeList = List.of(employee);
		
		when(employeeRepository.findAll()).thenReturn(Collections.emptyList());
		
		BaseException employeeListNotFoundException = assertThrows(BaseException.class, () ->{
			employeeService.getAllEmployees();
		});
		
		assertEquals("List Not Found", employeeListNotFoundException.getMessage());
		
		verify(employeeRepository).findAll();
	}
	
	
}
