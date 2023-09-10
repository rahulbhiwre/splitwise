package project.splitwise.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaseController {

	@ExceptionHandler(Exception.class)
	public <T> ResponseEntity<ResponseModel<T>> handleException(Exception ex) {
		return getResponseEntityForException(ex);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForOK(T object) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setData(object);
		responseModel.setResponseMessage(HttpStatus.OK.name());
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForOK(T object, String message) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setData(object);
		responseModel.setResponseMessage(message);
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForCreate(T object) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setData(object);
		responseModel.setResponseMessage(HttpStatus.CREATED.name());
		return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForCreate(T object, String message) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(true);
		responseModel.setData(object);
		responseModel.setResponseMessage(message);
		return new ResponseEntity<>(responseModel, HttpStatus.CREATED);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForBadRequest(T object) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setData(object);
		responseModel.setResponseMessage(HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForBadRequest(String message) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setResponseMessage(message);
		return new ResponseEntity<>(responseModel, HttpStatus.OK);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForBadRequest(T object, String message) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setData(object);
		responseModel.setResponseMessage(message);
		return new ResponseEntity<>(responseModel, HttpStatus.BAD_REQUEST);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForException(Exception ex) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setResponseMessage(ex.getMessage());
		return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForException(String message) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setResponseMessage(message);
		return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public <T> ResponseEntity<ResponseModel<T>> getResponseEntityForException(Exception ex, String message) {
		ResponseModel<T> responseModel = new ResponseModel<>();
		responseModel.setSuccess(false);
		responseModel.setResponseMessage(message);
		return new ResponseEntity<>(responseModel, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
