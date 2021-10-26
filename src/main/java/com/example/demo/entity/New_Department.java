package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name="New_Departments" ,schema="HR")
public class New_Department {

	@Id
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
	
}
