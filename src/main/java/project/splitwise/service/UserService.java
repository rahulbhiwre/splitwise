package project.splitwise.service;

import java.util.List;

import project.splitwise.dto.UserDTO;

public interface UserService {

	public UserDTO addUser(UserDTO user);

	public UserDTO getUser(Long userId);

	public List<UserDTO> getUsers();

}
