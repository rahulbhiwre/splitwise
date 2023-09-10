package project.splitwise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import project.splitwise.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

}
