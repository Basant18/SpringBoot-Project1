package com.example.demo.service;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
//import oracle.jdbc.*;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.EmployeeDTO;

import oracle.jdbc.OracleCallableStatement;
import oracle.jdbc.OracleTypes;
import oracle.jdbc.driver.OracleConnection;

@Service
public class EmployeeService {
	
	@Autowired
	EntityManager entityManager;
	
	public BigDecimal averageSalary()
	{
		Session s = this.entityManager.unwrap(Session.class);
		return s.doReturningWork((connection) -> {
			BigDecimal res;
			OracleConnection orcConn = connection.getMetaData().getConnection().unwrap(OracleConnection.class);
			CallableStatement stmt = orcConn.prepareCall("{? = call FINDAVGSALARY()}");
			stmt.registerOutParameter(1, OracleTypes.NUMBER);
			stmt.execute();
			res = stmt.getBigDecimal(1);
			return res;
		});
	}
	
	public List<EmployeeDTO> getAllEmployeesOfDep(BigDecimal deptId)
	{
		Session s = this.entityManager.unwrap(Session.class);
		return s.doReturningWork((conn)->{
			List<EmployeeDTO> result = new ArrayList<>();
			OracleConnection orcConn = conn.getMetaData().getConnection().unwrap(OracleConnection.class);
			CallableStatement stmt = orcConn.prepareCall("{call PACKAGE1.get_emp_of_dept(?,?)}");
			stmt.setBigDecimal(1, deptId);
			stmt.registerOutParameter(2, OracleTypes.CURSOR);
			stmt.execute();
			
			ResultSet rs = ((OracleCallableStatement)stmt).getCursor(2);
			while(rs.next())
			{
				EmployeeDTO emp = new EmployeeDTO();
				emp.setFirstName(rs.getString(1));
				emp.setLastName(rs.getString(2));
				emp.setDepartmentId(rs.getBigDecimal(3));
				result.add(emp);
			}
			return result;
		});
	}
	
	public boolean jobHistory(BigDecimal emp_id,Date strt_date,Date end_date,String job_id,
			BigDecimal dept_id)
	{
		Session s = this.entityManager.unwrap(Session.class);
		return s.doReturningWork((conn) ->{
			boolean isSuccess = false;
			OracleConnection orcConn = conn.getMetaData().getConnection().unwrap(OracleConnection.class);
			CallableStatement stmt = orcConn.prepareCall("{call add_job_history(?,?,?,?,?)}");
			stmt.setBigDecimal(1, emp_id);
			stmt.setDate(2, strt_date);
			stmt.setDate(3, end_date);
			stmt.setString(4, job_id);
			stmt.setBigDecimal(5, dept_id);
			
			try
			{
				stmt.execute();
				isSuccess = true;
			}
			catch(Exception e)
			{
				return false;
			}
			
			return isSuccess;
			
		});
	}
}
