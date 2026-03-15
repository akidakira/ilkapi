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
	@NotBlank(message = "departman isim alanı boş olmamalı")
	private String departmentName;
	
	@NotBlank(message = "departman lokasyon alanı boş olmamalı")
	private String departmentLocation;
	
}
