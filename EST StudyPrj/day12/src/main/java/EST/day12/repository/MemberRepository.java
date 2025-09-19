package EST.day12.repository;

import EST.day12.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Object> findByName(String name);// SELECT m FROM Member m: Member 엔티티 객체 조회
    // JOIN FETCH m.team : m.team 연관 엔티티를 한 번에 같이 조회
//    @Query("SELECT m FROM Member m JOIN FETCH m.team")
//    List<Member> findAllByTeamIsNotNull();
    @Query("SELECT m FROM Member m JOIN FETCH m.subscription")
    List<Member> findAllByTeamIsNotNull();

}
