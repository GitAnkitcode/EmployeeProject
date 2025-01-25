package com.project.employee.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AdviceControllerException {
	@ExceptionHandler(value = EmployeeNotFoundException.class)
	public ErrorResponse employeeNotFoundException(EmployeeNotFoundException employeeNotFoundException) {
		Long employeeId = employeeNotFoundException.getId();
		return new ErrorResponse(employeeId, "Employe Not Found This Place =" + employeeId);
	}

}
