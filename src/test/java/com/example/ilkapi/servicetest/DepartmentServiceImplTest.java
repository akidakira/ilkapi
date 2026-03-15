package com.example.ilkapi.servicetest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.DepartmentDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.exception.BaseException;
import com.example.ilkapi.repository.IDepartmentRepository;
import com.example.ilkapi.service.DepartmentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceImplTest {
	
	@InjectMocks
	private DepartmentServiceImpl departmentService;
	
	@Mock
	private IDepartmentRepository departmentRepository;
	
	Department department = new Department();
	DepartmentDtoIU departmentDtoIU = new DepartmentDtoIU();
	Long departmentId = 1L;
	
	@BeforeEach
	public void departmentSetUp() {
		
		department.setDepartmentName("department-name");
		department.setDepartmentLocation("department-location");
		department.setDepartmentId(departmentId);
		
		
		departmentDtoIU.setDepartmentName("department-name");
		departmentDtoIU.setDepartmentLocation("department-location");
	}
	
	@Test
	public void createDepartmentTest() {
		
		when(departmentRepository.save(any(Department.class))).thenReturn(department);
		
		DepartmentDto result = departmentService.createDepartment(departmentDtoIU);
		
		assertNotNull(result);
		assertEquals(departmentId, result.getDepartmentId());
		assertEquals("department-name", result.getDepartmentName());
		assertEquals("department-location", result.getDepartmentLocation());
		
		verify(departmentRepository).save(any(Department.class));
		
	}
	
	@Test
	public void deleteDepartmentByIdTest() {
		
		when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
		
		Boolean result = departmentService.deleteDepartmentById(departmentId);
		
		assertTrue(result);
		
		verify(departmentRepository).delete(department);
	}
	
	@Test
	public void getDepartmentListTest() {
		
		List<Department> departmentList = List.of(department);
		
		when(departmentRepository.findAll()).thenReturn(departmentList);
		
		List<DepartmentDto> resultList = departmentService.getDepartmentList();
		
		assertNotNull(resultList);
		assertEquals(1L, resultList.get(0).getDepartmentId());
		assertEquals("department-name", resultList.get(0).getDepartmentName());
		assertEquals("department-location", resultList.get(0).getDepartmentLocation());
		
		verify(departmentRepository).findAll();
	}
	
	@Test
	public void findByIdDepartmentTest() {
		
		when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
		
		DepartmentDto result = departmentService.findByIdDepartment(departmentId);
		
		assertNotNull(result);
		assertEquals(1L, result.getDepartmentId());
		
		verify(departmentRepository).findById(departmentId);
	}
	
	@Test
	public void updateDepartmentByIdTest() {
		
		DepartmentDtoIU newDepartment = new DepartmentDtoIU();
		
		newDepartment.setDepartmentName("new-department-name");
		newDepartment.setDepartmentLocation("new-department-location");
		
		when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
		
		DepartmentDto result = departmentService.updateDepartmentById(departmentId, newDepartment);
		
		assertNotNull(result);
		assertEquals(1L, result.getDepartmentId());
		assertEquals("new-department-name", result.getDepartmentName());
		assertEquals("new-department-location", result.getDepartmentLocation());
		
		verify(departmentRepository).findById(departmentId);
		verify(departmentRepository).save(any(Department.class));
	}
	
	
	@Test
	public void throwDepartmentNotFoundException() {
		
		when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());
		
		BaseException departmentNotFoundException = assertThrows(BaseException.class, ()->{
			departmentService.findByIdDepartment(departmentId);
		});
		
		assertEquals("Department Not Found", departmentNotFoundException.getMessage());
		
		verify(departmentRepository).findById(departmentId);
	}

}
