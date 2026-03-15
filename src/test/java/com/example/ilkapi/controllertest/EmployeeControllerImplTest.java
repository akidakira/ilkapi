package com.example.ilkapi.controllertest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import java.sql.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.EmployeeDto;
import com.example.ilkapi.dto.EmployeeDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.entity.Employee;
import com.example.ilkapi.service.IEmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerImplTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	private IEmployeeService employeeService;
	
	EmployeeDto employeeDto = new EmployeeDto();
	EmployeeDtoIU employeeDtoIU = new EmployeeDtoIU();
	
	Long employeeId = 1L;
	Long departmentId = 1L;
	
	@BeforeEach
	public void setUp() {
		
		
		
		employeeDtoIU.setEmployeeName("employee-name");
		employeeDtoIU.setEmployeeLastname("employee-lastname");
		employeeDtoIU.setEmployeeBirthDate(Date.valueOf("1999-03-12"));
		employeeDtoIU.setDepartmentId(departmentId);
		
		employeeDto.setEmployeeId(employeeId);
		employeeDto.setEmployeeName(employeeDtoIU.getEmployeeName());
		
	}
	
	@Test
	public void createEmployeeTest() throws Exception{
		
		when(employeeService.createEmployee(any(EmployeeDtoIU.class)))
        .thenReturn(employeeDto);

	    mockMvc.perform(post("/rest/api/create/employee")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(employeeDtoIU)))
	    		.andExpect(jsonPath("$.code").value("EMPLOYEE_CREATED"))
	          	.andExpect(jsonPath("$.data.employeeName").value(employeeDto.getEmployeeName()))
	            .andDo(print())
	            .andExpect(status().isOk());
	    		
	    
	    employeeService.createEmployee(employeeDtoIU);
	    
	    verify(employeeService).createEmployee(employeeDtoIU);
		
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
	
	
	
}
