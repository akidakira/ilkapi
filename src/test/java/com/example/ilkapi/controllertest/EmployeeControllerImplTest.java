package com.example.ilkapi.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.sql.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.ilkapi.controller.EmployeeControllerImpl;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.service.IDepartmentService;
import com.example.ilkapi.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(EmployeeControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private IEmployeeService employeeService;
	
	@MockBean
	private IDepartmentService departmentService;
	
	EmployeeDto employeeDto = new EmployeeDto();
	EmployeeDtoIU employeeDtoIU = new EmployeeDtoIU();
	EmployeeDtoIU newEmployee = new EmployeeDtoIU();
	
	DepartmentDto departmentDto = new DepartmentDto();
	Department department = new Department();
	
	Long employeeId = 1L;
	Long departmentId = 1L;
	
	@BeforeEach
	public void setUp() {
		
		employeeDtoIU.setEmployeeName("employee-name");
		employeeDtoIU.setEmployeeLastname("employee-lastname");
		employeeDtoIU.setEmployeeBirthDate(Date.valueOf("1999-03-12"));
		employeeDtoIU.setDepartmentId(departmentId);
		
		newEmployee.setEmployeeName("new-employee-name");
		newEmployee.setEmployeeLastname("new-employee-lastname");
		newEmployee.setEmployeeBirthDate(Date.valueOf("1999-01-01"));
		newEmployee.setDepartmentId(departmentId);
		
		department.setDepartmentId(departmentId);
		department.setDepartmentName("department-name");
		department.setDepartmentLocation("department-location");
		
		departmentDto.setDepartmentId(department.getDepartmentId());
		departmentDto.setDepartmentName(department.getDepartmentName());
		departmentDto.setDepartmentLocation(department.getDepartmentLocation());
		
		employeeDto.setEmployeeId(employeeId);
		employeeDto.setEmployeeName(employeeDtoIU.getEmployeeName());
		employeeDto.setDepartmentDto(departmentDto);
		
	}
	
	@Test
	public void createEmployeeTest() throws Exception{
		
		when(employeeService.createEmployee(any(EmployeeDtoIU.class)))
        .thenReturn(employeeDto);

	    mockMvc.perform(post("/rest/api/create/employee")
	    .contentType(MediaType.APPLICATION_JSON)
	    .content(objectMapper.writeValueAsString(employeeDtoIU)))
	    .andDo(print())
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$.code").value("EMPLOYEE_CREATED"))
	    .andExpect(jsonPath("$.data.employeeName").value(employeeDto.getEmployeeName()))
	    .andExpect(jsonPath("$.data.departmentDto.departmentName").value(departmentDto.getDepartmentName()));
	    
	    verify(employeeService).createEmployee(any(EmployeeDtoIU.class));
		
	}
	
	@Test
	public void findByIdEmployeeTest() throws Exception{
		
		when(employeeService.findByIdEmployee(employeeId)).thenReturn(employeeDto);
		
		mockMvc.perform(get("/rest/api/findbyid/{employeeId}", employeeId))
		.andExpect(jsonPath("$.code").value("EMPLOYEE_FOUND"))
		.andExpect(jsonPath("$.data.employeeName").value(employeeDto.getEmployeeName()))
		.andDo(print())
		.andExpect(status().isOk());
		
		verify(employeeService).findByIdEmployee(employeeId);
	}
	
	@Test
	public void getAllEmployeesTest() throws Exception{
		
		List<EmployeeDto> employeeList = List.of(employeeDto);
		
		when(employeeService.getAllEmployees()).thenReturn(employeeList);
		
		mockMvc.perform(get("/rest/api/getall/employee"))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.code").value("LIST_FOUND"))
		.andExpect(jsonPath("$.data").isArray())
		.andExpect(jsonPath("$.data[0].employeeName").value(employeeDto.getEmployeeName()));
				
				
		
		verify(employeeService).getAllEmployees();
	}
	
	@Test
	public void deleteByIdEmployeeTest() throws Exception{
		
		when(employeeService.deleteByIdEmployee(employeeId)).thenReturn(true);
		
		mockMvc.perform(delete("/rest/api/delete/employee/byid/{employeeId}",employeeId))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.code").value("EMPLOYEE_DELETED"))
		.andExpect(jsonPath("$.data").value(true));
		
		verify(employeeService).deleteByIdEmployee(employeeId);
	}
	
	@Test
	public void updateEmployeeByIdTest() throws Exception{
		when(employeeService.updateEmployeeById(eq(employeeId), any())).thenReturn(employeeDto);
		
		mockMvc.perform(put("/rest/api/update/employee/byid/{employeeId}", employeeId)
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(employeeDtoIU)))
		.andDo(print())
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.code").value("EMPLOYEE_UPDATED"))
		.andExpect(jsonPath("$.data.employeeName").value(employeeDtoIU.getEmployeeName()));
		
		verify(employeeService).updateEmployeeById(eq(employeeId), any());
	}
}
