package com.example.demo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name="Employees", schema="HR")
public class Employee {
	
	@Id
	@Column(name="EMPLOYEE_ID")
	private Long employeeId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="PHONE_NUMBER")
	private String phoneNo;
	
	@Column(name="HIRE_DATE")
	private Date hireDate;
	
	@Column(name="JOB_ID")
	private String jobId;
	
	@Column(name="SALARY")
	private Long salary;
	
	@Column(name="COMMISSION_PCT")
	private Long commPct;
	
	@Column(name="MANAGER_ID")
	private Long mgId;
	
//	@Column(name="DEPARTMENT_ID")
//	private Long departmentId;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

	public Long getCommPct() {
		return commPct;
	}

	public void setCommPct(Long commPct) {
		this.commPct = commPct;
	}

	public Long getMgId() {
		return mgId;
	}

	public void setMgId(Long mgId) {
		this.mgId = mgId;
	}

//	public Long getDepartmentId() {
//		return departmentId;
//	}
//
//	public void setDepartmentId(Long departmentId) {
//		this.departmentId = departmentId;
//	}
	
//	//@OneToOne
	@JsonIgnore
	@ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="DEPARTMENT_ID", nullable=false,insertable=false,updatable=false)
	private Department department;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
	
//	@ManyToOne
//	@MapsId("deptId")
//	@JoinColumn(name="DEPT_ID")
//	private EmpDeptMap empDeptMap;
//
//	public EmpDeptMap getEmpDeptMap() {
//		return empDeptMap;
//	}
//
//	public void setEmpDeptMap(EmpDeptMap empDeptMap) {
//		this.empDeptMap = empDeptMap;
//	}
	
//	@JsonIgnore
//	@ManyToMany
//	@JoinTable(name = "EMP_DEPT", joinColumns = @JoinColumn(name = "EMP_ID"), 
//	           inverseJoinColumns = @JoinColumn(name = "DEPT_ID"))
//	Set<Department> empDept;
//
//	public Set<Department> getEmpDept() {
//		return empDept;
//	}
//
//	public void setEmpDept(Set<Department> empDept) {
//		this.empDept = empDept;
//	}
	
//	@JsonManagedReference
//	@OneToMany(mappedBy="emp",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//	private List<EmpDeptMap> depts = new ArrayList<>();
	
}
	
	
