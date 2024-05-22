package project.splitwise.controller;

import lombok.Data;

@Data
public class ResponseModel<T> {

	private Boolean success;
	private String responseMessage;
	private T data;
	
}
