//package EST.day10.repository;
//
//import EST.day10.domain.Member;
//import org.apache.ibatis.annotations.Mapper;
//
//import java.util.List;
//import java.util.Optional;
//
//@Mapper
//public interface MemberMapper {
//    // MyBatis는 insert sql 동작시 영향받은 행 수 반환(데이터 개수 반환)
//    void save(Member member);
//    // select sql 동작시 조회된 객체 반환
//    Optional<Member> findById(Long id);
//    Optional<Member> findByName(String name);
//    List<Member> findAll();
//}
