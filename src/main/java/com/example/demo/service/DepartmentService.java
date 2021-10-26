package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Department;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	public List<Department> getAllDepartments()
	{
		return departmentRepository.findAll();
	}
	
	public ResponseEntity<Department> getDepartmentById(Long dpId)
	{
		Optional<Department> department = null;
		department = departmentRepository.findById(dpId);
		if(department.isPresent())
		{
			return new ResponseEntity<>(department.get(), HttpStatus.OK);
		}
		else
		{
			throw new ResourceNotFoundException("Id doesnot exist "+dpId);
		}
	}
	
	public Department createDepartment(Department department)
	{
		return departmentRepository.saveAndFlush(department);
	}
	
	public Department createDepartment1(String dpName,Long mgId,
			Long location)
	{
		Department department = new Department();
		//department.setDpId(dpId);
		department.setDpName(dpName);
		department.setMgId(mgId);
		department.setLocation(location);
		return departmentRepository.saveAndFlush(department);
	}
	
	public ResponseEntity<Department> updateDepartmentById(long dpId,Department 
			department)
	{
		Department dp = departmentRepository.findById(dpId)
				.orElseThrow(() -> new ResourceNotFoundException("Id doesnot exist "+dpId));
		dp.setDpName(department.getDpName());
		if(dp.getDpName() == null)
		{
			throw new ResourceNotFoundException("dpName is null");
		}
		dp.setMgId(department.getMgId());
		if(dp.getMgId() == null)
		{
			throw new ResourceNotFoundException("mgId is null");
		}
		dp.setLocation(department.getLocation());
		if(dp.getLocation() == null)
		{
			throw new ResourceNotFoundException("location is null");
		}
		
		final Department updateddepartment = departmentRepository.save(dp);
		
		return ResponseEntity.ok(updateddepartment);
	}
	
	public ResponseEntity<Department> updateDepartmentByQuery(Long dpId,String dpName,
			Long mgId,Long location)
	{
		Department dp = departmentRepository.findById(dpId)
				.orElseThrow(() -> new ResourceNotFoundException("Id doesnot exist "+dpId));
		dp.setDpName(dpName);
		if(dp.getDpName() == null)
		{
			throw new ResourceNotFoundException("dpName is null");
		}
		dp.setMgId(mgId);
		if(dp.getMgId() == null)
		{
			throw new ResourceNotFoundException("mgId is null");
		}
		dp.setLocation(location);
		if(dp.getLocation() == null)
		{
			throw new ResourceNotFoundException("location is null");
		}
		
		final Department updateddepartment = departmentRepository.save(dp);
		
		return ResponseEntity.ok(updateddepartment);
		
	}
	
	public Map<String,Boolean> deleteDepartment(Long dpId)
	{
		Department department = departmentRepository.findById(dpId)
				.orElseThrow(()->new ResourceNotFoundException("Id doesnot exist "+dpId));
		
		departmentRepository.delete(department);
		
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted",Boolean.TRUE);
		return response;
	}

}
