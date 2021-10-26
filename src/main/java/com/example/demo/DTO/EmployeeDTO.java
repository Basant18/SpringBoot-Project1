package com.example.demo.DTO;

import java.math.BigDecimal;

public class EmployeeDTO {
	
	private String firstName;
	private String lastName;
	private BigDecimal departmentId;
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public BigDecimal getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(BigDecimal departmentId) {
		this.departmentId = departmentId;
	}
	

}
