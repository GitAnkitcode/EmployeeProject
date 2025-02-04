package com.project.employee.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.project.employee.exceptions.EmployeeNotFoundException;
import com.project.employee.modal.Employee;
import com.project.employee.repository.EmployeeRepository;
import com.project.employee.response.AddressResponse;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private RestTemplate restTemplate;

	public Employee add(Employee employe) {
		Employee employeeSaved = employeeRepository.save(employe);
		AddressResponse addressResponse = employe.getAddressResponse();
		addressResponse.setUniqueId(employeeSaved.getId());
		AddressResponse savedAddressResponse = restTemplate.postForObject("http://localhost:8056/add", addressResponse,
				AddressResponse.class);
		

		return employeeSaved;
	}

	public Employee getEmployee(Long id) {

		Optional<Employee> em = employeeRepository.findById(id);
		if (em.isPresent()) {
			Employee employeeSaved = em.get();

			AddressResponse addressResponseSaved = restTemplate.getForObject("http://localhost:8056/{id}",AddressResponse.class, id);
			employeeSaved.setAddressResponse(addressResponseSaved);

			return employeeSaved;

		} else {
			throw new EmployeeNotFoundException(id);
		}

	}

	public String deleteEmployee(Long id) {
		Optional<Employee> obj= employeeRepository.findById(id);
		if(obj.isPresent())
		{
			employeeRepository.deleteById(id);
			restTemplate.delete("http://localhost:8056/{uniqueId}", id);
			return" Employee Delete Successfully";
		}
		else {
			throw new EmployeeNotFoundException(id);
		}

	}

	public String updateEmployee(Long id, Employee employee) {
		Optional<Employee> obj=employeeRepository.findById(id);
		if (obj.isPresent())
		{
			Employee savedEmployee= obj.get();
			savedEmployee.setName(employee.getName());
			savedEmployee.setEmailId(employee.getEmailId());
			savedEmployee.setDesignation(employee.getDesignation());
			savedEmployee.setContactNo(employee.getContactNo());

			AddressResponse addressResponse= employee.getAddressResponse();
			restTemplate.put("http://localhost:8056/update/{uniqueId}",addressResponse,id);
			employeeRepository.save(savedEmployee);
			return "Employee update sucessfully";
		}
		throw new EmployeeNotFoundException(id);
	}
}
