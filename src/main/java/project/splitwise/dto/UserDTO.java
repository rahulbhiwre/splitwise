package project.splitwise.dto;

import lombok.Data;
import lombok.Setter;

@Data
public class UserDTO {

	private Long userid;
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String city;

}
