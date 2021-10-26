package com.example.demo.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.Pojo.DepartmentPojo;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.DepartmentRepository;
import com.example.demo.repository.EmployeeRepository;
//import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value="DepartmentManager", description="Department Manager API",
     tags="DepartmentManager")
public class DepartmentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	DepartmentRepository departmentRepository;
	
	private final DepartmentService departmentService;
	
	public DepartmentController(DepartmentService departmentService)
	{
		this.departmentService = departmentService;
	}
	
	@ApiOperation(value="Get All Departments",nickname="getAllDepartments",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Department found",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Departments not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/")
	public List<Department> getAllDepartments()
	{
		LOGGER.info("DepartmentController list of users");
		//return departmentService.getAllDepartments();
		return departmentRepository.findAllQueryDepartments();
	}
	
//	@ApiOperation(value="Get All LEFT JOIN Departments",nickname="LeftJoinDepartments",notes="",
//			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
//	@ApiResponses(value= {
//			@io.swagger.annotations.ApiResponse(code=200,message="Department found",response=Department.class,responseContainer="List"),
//			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
//			@io.swagger.annotations.ApiResponse(code=404,message="Departments not found"),
//			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
//	@GetMapping("/leftJoin/")
//	public List<Department> leftJoinDepartment()
//	{
//		LOGGER.info("DepartmentController list of users");
//		//return departmentService.getAllDepartments();
//		return departmentRepository.leftJoinDepartmet();
//	}
	
	@ApiOperation(value="Get Department By ID",nickname="getDepartmentById",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Department with ID found",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/findById/{dpId}")
	//@GetMapping("/query")
	public ResponseEntity<Department> getDepartmentById(@PathVariable long dpId
			/*@RequestParam Long dpId*/)
	{
		return departmentService.getDepartmentById(dpId);
	}
	
	@ApiOperation(value="Get Department By Name",nickname="getDepartmentByName",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Department with Name found",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/findByName/{dpName}")
	//@GetMapping("/query")
	public Department/*ResponseEntity<Department>*/ getDepartmentById(@PathVariable String dpName
			/*@RequestParam Long dpId*/)
	{
		//return departmentService.getDepartmentById(dpId);
		return departmentRepository.findQueryDepartmentName(dpName);
	}
	
	@ApiOperation(value="Get Department By Name",nickname="getDepartmentBydpName",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Department with Name found",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping("/queryBy/dpName")
	public ResponseEntity<List<Department>> getDepartmentBydpName(
			@RequestParam(required=false) Long dpId,
			@RequestParam(required=false) String dpName)
	{
		if(dpId == null && dpName != null)
		{
			return new 
					ResponseEntity<List<Department>>(departmentRepository
							.findBydpNameIgnoreCase(dpName),HttpStatus.OK);
		}
		else if(dpName == null && dpId != null)
		{
			return new ResponseEntity<List<Department>>(
					departmentRepository.findBydpId(dpId),HttpStatus.OK);
		}
		else if(dpId == null && dpName == null)
		{
			throw new ResourceNotFoundException("Required Queries are not present"); 
		}
		
		else if(dpId != null && dpName != null)
		{
//			 List<Department> d1 = departmentRepository.findBydpId(dpId);
//			 List<Department> d2 = departmentRepository.findBydpNameIgnoreCase(dpName);
//			 if(!d1.equals(d2))
//			 {
//				 throw new ResourceNotFoundException("Required Queries are invalid"); 
//			 }
			System.out.print("It is working.......");
			 return new ResponseEntity<List<Department>>(
					 departmentRepository.findByDpIdAndDpNameIgnoreCaseContains(dpId, dpName),
					 HttpStatus.OK);
		}
		
		return new ResponseEntity<List<Department>>(
				departmentRepository.findBydpId(dpId),HttpStatus.OK);
		
	}
	
	@ApiOperation(value="Post Department",nickname="createDepartment",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Department created",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	//@PostMapping("/add")
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public Department createDepartment(@RequestBody Department department)
	{
		//throw new ResourceNotFoundException();
		return departmentService.createDepartment(department);
	}
	
	@ApiOperation(value="Get Department using queryParams",nickname="createDepartment1",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Department created using queryParams",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@PostMapping("/add1")
	//@GetMapping("/add1")
	public void /*Department*/ createDepartment1(@RequestParam Long dpId,
			@RequestParam String dpName,@RequestParam Long mgId,
			@RequestParam Long location)
	{
		//throw new ResourceNotFoundException();
		//return departmentService.createDepartment1(dpName,mgId,location);
		//return departmentRepository.findQueryDepartment(dpName, mgId, location);
		departmentRepository.createDepartmentQuery(dpId,dpName, mgId, location);
	}
	
	@ApiOperation(value="Update Department Record",nickname="updateDepartmentById",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Update Department",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@PutMapping("/update/{dpId}")
//	public ResponseEntity<Department> updateDepartmentById(@PathVariable(value = "dpId") 
//	Long dpId,@RequestBody Department department)
	public int updateDepartmentById(@RequestParam String dpName,
			@PathVariable(value = "dpId")Long dpId)
	{
		//return departmentService.updateDepartmentById(dpId,department); 
		return departmentRepository.updateUserSetStatusForName(dpName,dpId);
	}
	
	@ApiOperation(value="Update Department Record using queryparams",nickname="updateDepartmentById",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Update Department",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@PutMapping("/update/query")
	public ResponseEntity<Department> updateDepartmentByQuery(@RequestParam Long dpId, 
	@RequestParam String dpName,@RequestParam Long mgId,@RequestParam Long location)
	{
		return departmentService.updateDepartmentByQuery(dpId,dpName,mgId,location); 
	}
	
	@ApiOperation(value="Delete Department",nickname="deleteDepartment",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Delete Department",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	//@DeleteMapping("/delete/{dpId}")
	@DeleteMapping("/delete")
	public Map<String,Boolean> deleteDepartment(/*@PathVariable Long dpId*/
			@RequestParam Long dpId)
	{
		return departmentService.deleteDepartment(dpId);
	}
	
	@Autowired
	RestTemplate restTemplate;
	@Autowired
	ApplicationContext appContext;
	@Autowired
	DepartmentPojo dpPojo;
	

	@ApiOperation(value="Get RestTemplate Depart",nickname="getDepartment",notes="",
			response=Department.class,responseContainer="list",tags= {"DepartmentManager"})
	@ApiResponses(value= {
			@io.swagger.annotations.ApiResponse(code=200,message="Delete Department",response=Department.class,responseContainer="List"),
			@io.swagger.annotations.ApiResponse(code=400,message="Invalid Department"),
			@io.swagger.annotations.ApiResponse(code=404,message="Department not found"),
			@io.swagger.annotations.ApiResponse(code=500,message="Internal Server Error")})
	@GetMapping(value="/templateDepartment")
	public ResponseEntity<Object> getDepart()
	{
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity <String> entity = new HttpEntity<String>(headers);
	    
		DepartmentPojo ob = (DepartmentPojo) appContext.getBean("departmentMethod");
		String body = restTemplate.exchange("http://localhost:8035/departments",
				HttpMethod.GET,entity,String.class).getBody();
		ob.setBody(body);
		System.out.println("Ob ID"+ob);
		System.out.println("dpPojo ID"+dpPojo);
		return new ResponseEntity<>(ob,HttpStatus.OK);
		/*return restTemplate.exchange("http://localhost:8035/departments",
				HttpMethod.GET,entity,String.class).getBody();*/
	}

}
