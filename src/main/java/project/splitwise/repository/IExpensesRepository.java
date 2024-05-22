package project.splitwise.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.splitwise.entity.ExpensesEntity;

@Repository
public interface IExpensesRepository extends JpaRepository<ExpensesEntity, Long> {

	@Query("SELECT e FROM ExpensesEntity e WHERE (e.from.userid = ?1 OR e.from.userid = ?2) AND (e.to.userid = ?1 OR e.to.userid = ?2)")
	List<ExpensesEntity> findExpensesByUserIdss(Long friend1, Long friend2);

}