package com.example.demo.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entities.Employee;
import com.example.demo.result.ServiceResult;
import com.example.demo.service.EmployeeService;






@RestController
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@GetMapping("/employees")
	public ResponseEntity<ServiceResult> findAllEmployee() {
		return new ResponseEntity<ServiceResult>(employeeService.fillAll(), HttpStatus.OK);
	}
	/* ---------------- GET CUSTOMER BY ID ------------------------ */
	@GetMapping("/employees/{id}")
	public ResponseEntity<ServiceResult> findById(@PathVariable int id) {
		return new ResponseEntity<ServiceResult>(employeeService.findById(id), HttpStatus.OK);
	}
	/* ---------------- CREATE NEW CUSTOMER ------------------------ */
	@PostMapping("/employees")
	public ResponseEntity<ServiceResult> create(@RequestBody Employee employee) {
		return new ResponseEntity<ServiceResult>(employeeService.create(employee), HttpStatus.OK);
	}

	/* ---------------- UPDATE CUSTOMER ------------------------ */
	@PutMapping("/employees/{id}")
	public ResponseEntity<ServiceResult> update(@RequestBody Employee employee, @PathVariable int id) {
		employee.setId(id);
		return new ResponseEntity<ServiceResult>(employeeService.update(employee), HttpStatus.OK);
	}
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<ServiceResult> delete(@PathVariable int id) {
		//		  return new ResponseEntity<ServiceResult>(employeeService.delete(request.getId()), HttpStatus.OK);
		return new ResponseEntity<ServiceResult>(employeeService.delete(id), HttpStatus.OK);
	}

	@PostMapping(value="/uploadFile")
	public ResponseEntity<ServiceResult> uploadfile(@RequestParam("file") MultipartFile file) throws IOException {
		Employee employee  = new Employee();

		File mkdir = new File("demo");

		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		String filename = file.getOriginalFilename();
		File newFile= new File(mkdir, file.getOriginalFilename());
		FileOutputStream fileOutputStream = new FileOutputStream(newFile);
		fileOutputStream.close();
		employee.setImage(filename);
		employeeService.create(employee);
		return new ResponseEntity<ServiceResult>(employeeService.create(employee), HttpStatus.OK);
	}
}
