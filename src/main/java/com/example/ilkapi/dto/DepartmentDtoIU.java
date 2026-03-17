package com.example.ilkapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDtoIU {

	@Size(min = 3, max = 50)
	@NotBlank(message = "The department name should not be left blank.")
	private String departmentName;
	
	@NotBlank(message = "The department location should not be left blank.")
	private String departmentLocation;
	
}
