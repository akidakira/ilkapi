package com.example.ilkapi.controllertest;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.ilkapi.controller.DepartmentControllerImpl;
import com.example.ilkapi.controller.EmployeeControllerImpl;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.DepartmentDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.service.IDepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DepartmentControllerImpl.class)
@AutoConfigureMockMvc(addFilters = false)
public class DepartmentControllerImplTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@MockBean
	IDepartmentService departmentService;
	
	//Department department = new Department();
	DepartmentDto departmentDto = new DepartmentDto();
	DepartmentDtoIU departmentDtoIU = new DepartmentDtoIU();
	Long departmentId = 1L;
	
	@BeforeEach
	public void setUp() {
		departmentDtoIU.setDepartmentName("department-name");
		departmentDtoIU.setDepartmentLocation("department-location");
		
		departmentDto.setDepartmentId(departmentId);
		departmentDto.setDepartmentName(departmentDtoIU.getDepartmentName());
		departmentDto.setDepartmentLocation(departmentDtoIU.getDepartmentLocation());
		
	}
	
	
	@Test
	public void createDepartmentTest() throws Exception{
		when(departmentService.createDepartment(departmentDtoIU)).thenReturn(departmentDto);
		
		mockMvc.perform(post("/rest/api/create/department")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(departmentDtoIU)))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.code").value("DEPARTMENT_CREATED"))
		.andExpect(jsonPath("$.data.departmentName").value(departmentDto.getDepartmentName()));
		
		verify(departmentService).createDepartment(departmentDtoIU);
	}
	
	
	@Test
	public void deleteDepartmentByIdTest() throws Exception{
		when(departmentService.deleteDepartmentById(departmentId)).thenReturn(true);
		
		mockMvc.perform(delete("/rest/api/delete/department/byid/{departmentId}",departmentId))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.code").value("DEPARTMENT_DELETED"))
		.andExpect(jsonPath("$.data").value(true));
		
		verify(departmentService).deleteDepartmentById(departmentId);
				
	}
	
	@Test
	public void getDepartmentListTest() throws Exception{
		List<DepartmentDto> departmentDtoList = List.of(departmentDto);
		
		when(departmentService.getDepartmentList()).thenReturn(departmentDtoList);
		
		mockMvc.perform(get("/rest/api/get/department/list"))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.code").value("LIST_FOUND"))
		.andExpect(jsonPath("$.data[0].departmentName").value(departmentDto.getDepartmentName()));
		
		verify(departmentService).getDepartmentList();
	}
	
	@Test
	public void findByIdDepartmentTest() throws Exception{
		when(departmentService.findByIdDepartment(departmentId)).thenReturn(departmentDto);
		
		mockMvc.perform(get("/rest/api/department/findbyid/{departmentId}", departmentId))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.code").value("DEPARTMENT_FOUND"))
		.andExpect(jsonPath("$.data.departmentName").value(departmentDto.getDepartmentName()));
		
		verify(departmentService).findByIdDepartment(departmentId);
	}
	
	@Test
	public void updateDepartmentByIdTest() throws Exception{
		when(departmentService.updateDepartmentById(departmentId, departmentDtoIU)).thenReturn(departmentDto);
		
		mockMvc.perform(put("/rest/api/update/department/byid/{departmentId}",departmentId)
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(departmentDtoIU)))
		.andExpect(status().isOk())
		.andDo(print())
		.andExpect(jsonPath("$.code").value("DEPARTMENT_UPDATED"))
		.andExpect(jsonPath("$.data.departmentName").value(departmentDto.getDepartmentName()));
		
		verify(departmentService).updateDepartmentById(departmentId, departmentDtoIU);
	}
	
	// validation tests
	
	@Test
	public void createDepartmentValidationTest() throws Exception{
		
		departmentDtoIU.setDepartmentName(null);
		
		mockMvc.perform(post("/rest/api/create/department")
		.contentType(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(departmentDtoIU)))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.departmentName").value("The department name should not be left blank."));
		
		//thats it!!!
		
		
	}
}
