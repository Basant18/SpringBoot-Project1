package com.example.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
@Table(name="Departments" ,schema="HR")
public class Department {
	@Id
	@SequenceGenerator(name = "Department", sequenceName = "DEPARTMENTS_SEQ",allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="Department")
	@Column(name="DEPARTMENT_ID")
	private Long dpId;
	
	@Column(name="DEPARTMENT_NAME")
	private String dpName;
	
	@Column(name="MANAGER_ID")
	private Long mgId;
	
	@Column(name="LOCATION_ID")
	private Long location;

	public Long getDpId() {
		return dpId;
	}

	public void setDpId(Long dpId) {
		this.dpId = dpId;
	}

	public String getDpName() {
		return dpName;
	}

	public void setDpName(String dpName) {
		this.dpName = dpName;
	}

	public Long getMgId() {
		return mgId;
	}

	public void setMgId(Long mgId) {
		this.mgId = mgId;
	}

	public Long getLocation() {
		return location;
	}

	public void setLocation(Long location) {
		this.location = location;
	}
	
	//@JsonIgnore
	@OneToMany(mappedBy="department",fetch=FetchType.EAGER,cascade=CascadeType.ALL,orphanRemoval=true)
	//@JoinColumn(name="EMPLOYEE_ID", nullable=false,insertable=false,updatable=false)
	private List<Employee> empList = new ArrayList<>();

	public List<Employee> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Employee> empList) {
		this.empList = empList;
	}
	
//	@ManyToOne
//	@MapsId("empId")
//	@JoinColumn(name="EMP_ID")
//	private EmpDeptMap empDeptMap;
//
//	public EmpDeptMap getEmpDeptMap() {
//		return empDeptMap;
//	}
//
//	public void setEmpDeptMap(EmpDeptMap empDeptMap) {
//		this.empDeptMap = empDeptMap;
//	}
	
//	//@JsonIgnore
//	@ManyToMany(mappedBy = "empDept")
//	Set<Employee> emps;
//
//	public Set<Employee> getEmps() {
//		return emps;
//	}
//
//	public void setEmps(Set<Employee> emps) {
//		this.emps = emps;
//	}
	
//	@JsonBackReference
//	@OneToMany(mappedBy="dept")
//	private List<EmpDeptMap> empss = new ArrayList<>();

}
