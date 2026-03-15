package com.example.ilkapi.dto;

import com.example.ilkapi.entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

	private Long departmentId;
	private String departmentName;
	private String departmentLocation;
}
