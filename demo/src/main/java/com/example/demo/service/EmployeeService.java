package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Employee;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.result.ServiceResult;
import com.example.demo.result.ServiceResult.Status;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository  employeeRepository;
	
	public ServiceResult fillAll() {
		ServiceResult result = new ServiceResult();
		result.setData(employeeRepository.findAll());
		return result;
	}
	
	public ServiceResult findById(int id) {
	    ServiceResult result = new ServiceResult();
	    Employee employee = employeeRepository.findById(id).orElse(null);
	    result.setData(employee);
	    return result;
	  }
	  public ServiceResult create(Employee employee) {
	    ServiceResult result = new ServiceResult();
	    result.setData(employeeRepository.save(employee));
	    return result;
	  }
	  public ServiceResult update(Employee employee) {
	    ServiceResult result = new ServiceResult();
	    if (!employeeRepository.findById(employee.getId()).isPresent()) {
	      result.setStatus(Status.FAILED);
	      result.setMessage("employee Not Found");
	    } else {
	      result.setData(employeeRepository.save(employee));
	    }
	    return result;
	  }
	  public ServiceResult delete(int id) {
	    ServiceResult result = new ServiceResult();
	    Employee employee = employeeRepository.findById(id).orElse(null);
	    if (employee == null) {
	      result.setStatus(Status.FAILED);
	      result.setMessage("employee Not Found");
	    } else {
	      employeeRepository.delete(employee);
	      result.setMessage("success");
	    }
	    return result;
	  }
}
