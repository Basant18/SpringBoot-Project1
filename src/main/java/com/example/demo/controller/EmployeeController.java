package com.example.demo.controller;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DTO.EmployeeDTO;
import com.example.demo.DTO.ResponseDTO;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;


@RestController
@Api(value="EmployeeManager", description="Employee Manager API",
     tags="EmployeeManager")
public class EmployeeController {
	
	Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeService employeeService;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@ApiOperation(value="Get All Employees Pagination",nickname="getAllEmployees",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Employees found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Employees"),
			@io.swagger.annotations.ApiResponse(code=404,message="Employees not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy) 
	{
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
		
		Page<Employee> pagedResult = employeeRepository.findAll(paging);
		return pagedResult.getContent();
//		ResponseDTO searchResponse = new ResponseDTO();
//		List < Employee > resultList = employeeRepository.findAll();
//		PagedListHolder<Employee> holder = new PagedListHolder<>(resultList);
//		holder.setPage(0);
//		holder.setPageSize(2);
//		searchResponse.setPageSize(holder.getPageSize());
//		searchResponse.setPage(holder.getPage());
//		searchResponse.setTotalItemsFound(resultList.size());
//		searchResponse.setTotalPages(holder.getPageCount());
//		searchResponse.setResponseContent(holder.getPageList());
//		return ResponseEntity.ok().body(searchResponse);
	}
	
	@ApiOperation(value="Get All Employees From NativeQuery",nickname="getAllEmployees",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Employees found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Employees"),
			@io.swagger.annotations.ApiResponse(code=404,message="Employees not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/nativeQuery/employees")
	public ResponseEntity<List<Employee>> getAllEmployees() 
	{
		String sql = "select * from Employees";
		Query q = entityManager.createNativeQuery(sql);
		List<Employee> resultList = q.getResultList();
		// Employees employee = employeeRepository.findQueryEmployeeName(employeeName);
		return ResponseEntity.ok().body(resultList);
	}
	
	@ApiOperation(value="Get Employee By ID",nickname="getEmployeeByID",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Employee found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Employee"),
			@io.swagger.annotations.ApiResponse(code=404,message="Employee not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/employee/{employeeId}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long employeeId)
	{
		Optional<Employee> employee = null;
		employee = employeeRepository.findById(employeeId);
		if(employee.isPresent())
		{
			log.info("inside the executeLogger method");
			return new ResponseEntity<Employee>(employee.get(), HttpStatus.OK);
		}
		else
		{
			throw new ResourceNotFoundException("Id doesnot exist "+employeeId);
		}
	}
	
	@ApiOperation(value="Get Employee By ID criteriaBuilder",nickname="getEmployeeByID",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Employee found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Employee"),
			@io.swagger.annotations.ApiResponse(code=404,message="Employee not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/departments/criteriaBuilder/{dpId}/{employeeId}")
	public ResponseEntity<List<Department>> getEmployeeByIds(@PathVariable Long dpId
			,@PathVariable Long employeeId)
	{
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Department> query = builder.createQuery(Department.class);
		Root<Department> deptRoot = query.from(Department.class);
		Join<Department, Employee> empsRoot = deptRoot.join("empList");


		List<Predicate> conditions = new ArrayList<>();
		conditions.add(builder.equal(deptRoot.get("dpId"), dpId));
		conditions.add(builder.equal(empsRoot.get("employeeId"), employeeId));



		TypedQuery<Department> typedQuery = entityManager.createQuery(query.select(deptRoot).where(builder.and(conditions.toArray(new Predicate[] {}))));

//		List<Department> employeeList = typedQuery.getResultList();
//		return employeeList;
		
		return ResponseEntity.ok().body(typedQuery.getResultList().stream().filter(Objects::nonNull).distinct().collect(Collectors.toList()));
		//return ResponseEntity.ok(null);
	}
	
	@ApiOperation(value="Get Average Salary",nickname="getEmployeeSalary",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Avg Salary found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Avg Salary"),
			@io.swagger.annotations.ApiResponse(code=404,message="Avg Salary not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/avgsalary")
	public BigDecimal getEmployeeAvgSalary()
	{
		return employeeService.averageSalary();
	}
	
	@ApiOperation(value="Get Average Salary From deptId",nickname="getEmployeeSalary",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Avg Salary found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Avg Salary"),
			@io.swagger.annotations.ApiResponse(code=404,message="Avg Salary not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/emp-dept/{deptId}")
	public List<EmployeeDTO> getAllEmployeesOfDep(@PathVariable(name="deptId") BigDecimal deptId)
	{
		return employeeService.getAllEmployeesOfDep(deptId);
	}
	
	@ApiOperation(value="Post jobHistory",nickname="getJobHistory",notes="",
			response=Employee.class,responseContainer="list",tags= {"EmployeeManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Jobb History found",response=Employee.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Job History"),
			@io.swagger.annotations.ApiResponse(code=404,message="Job History not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@PostMapping("/jobHistory")
	public boolean jobHistory(@RequestParam BigDecimal employee_id,
			@RequestParam Date strt_date,
			@RequestParam Date end_date,
			@RequestParam String job_id,
			@RequestParam BigDecimal dept_id)
	{
		return employeeService.jobHistory(employee_id, strt_date, end_date, job_id, dept_id);
	}
}
