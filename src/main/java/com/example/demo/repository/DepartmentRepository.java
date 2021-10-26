package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{
	
	/*Example dpName should be DpName in findBy*/
	
	List<Department> findBydpId(Long dpId);
	List<Department> findBydpNameIgnoreCase(String dpName);
	List<Department> findByDpIdAndDpNameIgnoreCaseContains(Long dpId ,String dpName);
	
	@Query("Select d FROM Department d")
	List<Department> findAllQueryDepartments();
	
	@Query(value = "Select d FROM Department d WHERE d.dpName = :name")
	Department findQueryDepartmentName(@Param("name") String name);
	
	@Query("Select d FROM Department d WHERE d.dpName = ?1 and d.mgId = ?2 and d.location = ?3")
	Department findQueryDepartment(String dpName,Long mgId,Long location);
	
	@Transactional
	@Modifying
	@Query("update Department d set d.dpName = :name where d.dpId = :id")
	int updateUserSetStatusForName(@Param("name") String name, 
	  @Param("id") Long id);
	
	@Transactional
	@Modifying
	@Query(value = "insert into DEPARTMENTS (DEPARTMENT_ID,DEPARTMENT_NAME,MANAGER_ID,LOCATION_ID) values "
			+ "(:id,:name,:mgid,:location)",nativeQuery = true)
	void createDepartmentQuery(@Param("id") Long id,@Param("name") String name,
			@Param("mgid") Long mgid,@Param("location") Long location);
	
//	@Query(value="Select * FROM DEPARTMENTS d LEFT_JOIN EMPLOYEES e on d.DEPARTMENT_ID"
//			+ " = e.DEPARTMENT_ID")
//	List<Department> leftJoinDepartmet();
}
