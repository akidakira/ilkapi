package com.example.ilkapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ilkapi.apiresponse.ResponseType;
import com.example.ilkapi.dto.DepartmentDto;
import com.example.ilkapi.dto.DepartmentDtoIU;
import com.example.ilkapi.entity.Department;
import com.example.ilkapi.exception.BaseException;
import com.example.ilkapi.repository.IDepartmentRepository;


@Service
public class DepartmentServiceImpl implements IDepartmentService{

	@Autowired
	private IDepartmentRepository departmentRepository;
	
	@Override
	public DepartmentDto createDepartment(DepartmentDtoIU department) {
		
		DepartmentDto departmentDto = new DepartmentDto();
		Department dbDepartment = new Department();
		
		BeanUtils.copyProperties(department, dbDepartment);
		
		Department savedDepartment = departmentRepository.save(dbDepartment);
		
		BeanUtils.copyProperties(savedDepartment, departmentDto);
		return departmentDto;
	}

	@Override
	public boolean deleteDepartmentById(Long id) {
		Optional<Department> optional = departmentRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.DEPARTMENT_NOT_FOUND);
		}
		Department department = optional.get();
		departmentRepository.delete(department);
		return true;
	}

	@Override
	public List<DepartmentDto> getDepartmentList() {
		
		List<Department> departmentList = departmentRepository.findAll();
		List<DepartmentDto> departmentDtoList = new ArrayList<>();
		
		for (Department department : departmentList) {
			
			DepartmentDto departmentDto = new DepartmentDto();
			
			BeanUtils.copyProperties(department, departmentDto);
			
			departmentDtoList.add(departmentDto);
		}
		return departmentDtoList;
	}

	@Override
	public DepartmentDto findByIdDepartment(Long id) {
		Optional<Department> optional = departmentRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.DEPARTMENT_NOT_FOUND);
		}
		DepartmentDto departmentDto = new DepartmentDto();
		Department department = optional.get();
		BeanUtils.copyProperties(department, departmentDto);
		return departmentDto;
	}

	@Override
	public DepartmentDto updateDepartmentById(Long id, DepartmentDtoIU department) {
		Optional<Department> optional = departmentRepository.findById(id);
		if(optional.isEmpty()) {
			throw new BaseException(ResponseType.DEPARTMENT_NOT_FOUND);
		}
		Department dbdepartment = optional.get();
		DepartmentDto departmentDto = new DepartmentDto();
		
		dbdepartment.setDepartmentName(department.getDepartmentName());
		dbdepartment.setDepartmentLocation(department.getDepartmentLocation());
		
		departmentRepository.save(dbdepartment);
		
		BeanUtils.copyProperties(dbdepartment, departmentDto);
		
		return departmentDto;
	}

}
