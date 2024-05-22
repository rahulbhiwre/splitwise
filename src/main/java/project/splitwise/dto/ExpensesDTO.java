package project.splitwise.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import project.splitwise.entity.UserEntity;

@Data
public class ExpensesDTO {
    private Long expenseId;
    private UserDTO from;
    private UserDTO to;
    private double amount;
    private String description;

}
