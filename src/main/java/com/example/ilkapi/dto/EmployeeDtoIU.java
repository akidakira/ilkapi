package com.example.ilkapi.dto;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoIU {

	@NotBlank(message = "The name should not be left blank.")
	@Size(min = 3, max = 20, message = "It must be at least 3 and at most 20 characters long.")
	private String employeeName;
	
	@NotBlank(message = "The lastname should not be left blank.")
	@Size(min = 3, max = 20, message = "It must be at least 3 and at most 20 characters long.")
	private String employeeLastname;
	
	private Date employeeBirthDate;
	
	private Long departmentId;
}
