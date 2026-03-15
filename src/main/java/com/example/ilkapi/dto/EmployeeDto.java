package com.example.ilkapi.dto;

import com.example.ilkapi.entity.Department;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String employeeName;
	private Long employeeId;
	private DepartmentDto departmentDto;
}
