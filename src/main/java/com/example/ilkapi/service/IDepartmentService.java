package com.example.ilkapi.service;

import java.util.List;

import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.DepartmentDtoIU;
 

public interface IDepartmentService {

	public DepartmentDto createDepartment(DepartmentDtoIU department);
	public boolean deleteDepartmentById(Long id);
	public List<DepartmentDto> getDepartmentList();
	public DepartmentDto findByIdDepartment(Long id);
	public DepartmentDto updateDepartmentById(Long id, DepartmentDtoIU department);
}
