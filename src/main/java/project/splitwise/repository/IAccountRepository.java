package project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.splitwise.entity.UserEntity;
import project.splitwise.entity.UserTotalAmountEntity;

@Repository
public interface IAccountRepository extends JpaRepository<UserTotalAmountEntity, Long> {

	public UserTotalAmountEntity getByUserId(UserEntity user);

	@Query("SELECT u FROM UserTotalAmountEntity u WHERE u.userId.userid=?1")
	public UserTotalAmountEntity findByUserId(Long user);

}
