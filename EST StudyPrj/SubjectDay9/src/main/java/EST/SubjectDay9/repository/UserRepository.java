package EST.SubjectDay9.repository;

import EST.SubjectDay9.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    Optional<User> findByEmail(String email); //email로 사용자 정보를 가져옴
    Optional<User> findByCity(String city);
}
