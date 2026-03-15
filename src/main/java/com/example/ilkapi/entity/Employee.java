package com.example.ilkapi.entity;

import java.sql.Date;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "employee")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	
	@Column(name = "employeeName", nullable = false)
	private String employeeName;
	
	@Column(name = "employeeLastname", nullable = false)
	private String employeeLastname;
	
	@Column(name = "employeeBirtDate", nullable = false)
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date employeeBirthDate;
	
	@ManyToOne
	@JoinColumn(name = "department_id", nullable = true)
	private Department department;
}
