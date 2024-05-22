package project.splitwise.dto;

import java.util.Date;

import lombok.Data;

@Data
public class TransactionDTO {

	private Double amount;
	private String description;
	private Date date;
}
