package com.project.employee.exceptions;

public class EmployeeNotFoundException extends RuntimeException {

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public EmployeeNotFoundException(Long id) {
		super();
		this.id = id;
	}
}
