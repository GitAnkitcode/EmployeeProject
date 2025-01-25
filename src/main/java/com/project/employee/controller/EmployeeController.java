package com.project.employee.controller;

import org.springframework.web.bind.annotation.*;

import com.project.employee.exceptions.EmployeeNotFoundException;
import com.project.employee.modal.Employee;
import com.project.employee.service.EmployeeService;

import org.springframework.beans.factory.annotation.Autowired;

@RestController

public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/add")
    public Employee add(@RequestBody Employee employe) {

        return employeeService.add(employe);
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) throws EmployeeNotFoundException {

        return employeeService.getEmployee(id);
    }

    @DeleteMapping("/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        return employeeService.deleteEmployee(id);
    }

    @PutMapping("/update/{id}")
    public String updateEmployee(@PathVariable Long id,@RequestBody Employee employee)
    {
        return employeeService.updateEmployee(id,employee);
    }
}
