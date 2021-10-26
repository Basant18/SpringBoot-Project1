package com.example.demo.entity;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="EMP_DEPT")
public class EmpDeptMap implements Serializable{
	
	@EmbeddedId
	private EmpDeptMapId id;
	
	@JsonBackReference
	@ManyToOne
	@MapsId("empId")
	@JoinColumn(name="EMP_ID")
	private Employee emp;
	
	public EmpDeptMapId getId() {
		return id;
	}

	public void setId(EmpDeptMapId id) {
		this.id = id;
	}

	public Employee getEmp() {
		return emp;
	}

	public void setEmp(Employee emp) {
		this.emp = emp;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	@JsonManagedReference
	@ManyToOne
	@MapsId("deptId")
	@JoinColumn(name="DEPT_ID")
	private Department dept;
}
