package project.splitwise.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.splitwise.dto.UserDTO;
import project.splitwise.service.UserService;
import project.splitwise.serviceimpl.UserServiceImpl;

@RestController
@RequestMapping(value = "/api")
public class UserController extends BaseController {

	@Autowired
	UserServiceImpl userService;

	@PostMapping("/user")
	public ResponseEntity<ResponseModel<UserDTO>> addUser(@RequestBody UserDTO user) {
		UserDTO newUser = userService.addUser(user);
		if (Objects.nonNull(newUser)) {
			return getResponseEntityForCreate(newUser, "New User Added");
		}
		return getResponseEntityForBadRequest(newUser, "User Not Added");
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<ResponseModel<UserDTO>> getUser(@PathVariable Long userId) {
		UserDTO user = userService.getUser(userId);
		if (Objects.nonNull(user)) {
			return getResponseEntityForOK(user, "user present");
		}
		return getResponseEntityForBadRequest(user, "user not present");

	}

	@GetMapping("/users")
	public ResponseEntity<ResponseModel<List<UserDTO>>> getUsers() {
		List<UserDTO> users = userService.getUsers();
		if (Objects.nonNull(users)) {
			return getResponseEntityForOK(users, "user present");
		}
		return getResponseEntityForBadRequest(users, "user not present");

	}

	@GetMapping("/userTotalExpenses")
	public ResponseEntity<ResponseModel<Double>> getTotalExpense(Long userid) {
		Double result = userService.getTotalExpense(userid);
		System.out.println("Result.." + result);
		return getResponseEntityForOK(result, "total expenses calculated");

	}

}
