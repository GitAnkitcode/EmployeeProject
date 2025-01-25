package com.project.employee.exceptions;

public class ErrorResponse {
	private Long id;
	private String massage;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}


	public ErrorResponse(Long id, String massage) {
	
		this.id = id;
		this.massage = massage;
	}

}
