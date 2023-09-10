package project.splitwise.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "EXPENSES")
@Data
public class ExpensesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long expenseId;

	@ManyToOne
	@JoinColumn(name = "from_user", referencedColumnName = "userid")
	@JsonIgnore
	private UserEntity from;

	@ManyToOne
	@JoinColumn(name = "to_user", referencedColumnName = "userid")
	@JsonIgnore
	private UserEntity to;

	private double amount;
	private String description;
}
