package com.benz.info.model;

public class ErrorMessage {

	private int error_code;
	private String errorMsg;
	private String documentation;
	
	
public ErrorMessage() {
	}

public ErrorMessage(int error_code, String errorMsg, String documentation) {
	this.error_code = error_code;
	this.errorMsg = errorMsg;
	this.documentation = documentation;
}

public int getError_code() {
	return error_code;
}

public void setError_code(int error_code) {
	this.error_code = error_code;
}

public String getErrorMsg() {
	return errorMsg;
}

public void setErrorMsg(String errorMsg) {
	this.errorMsg = errorMsg;
}

public String getDocumentation() {
	return documentation;
}

public void setDocumentation(String documentation) {
	this.documentation = documentation;
}


	
	
}
