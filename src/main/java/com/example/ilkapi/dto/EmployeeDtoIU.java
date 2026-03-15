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

	@NotBlank(message = "isim alanı boş olmamalı")
	@Size(min = 3, max = 20, message = "en az 3 ve en fazla 20 karakter olmalıdır")
	private String employeeName;
	
	@NotBlank(message = "soyisim alanı boş olmamalı")
	@Size(min = 3, max = 20, message = "en az 3 ve en fazla 20 karakter olmalıdır")
	private String employeeLastname;
	
	private Date employeeBirthDate;
	
	private Long departmentId;
}
