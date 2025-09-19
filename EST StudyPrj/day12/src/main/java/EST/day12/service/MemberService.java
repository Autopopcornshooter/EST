package EST.day12.service;

import EST.day12.domain.Member;
import EST.day12.dto.MemberResponseDto;
import EST.day12.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final EntityManager em;
    public Long join(Member member) {  // 회원가입

        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    public List<MemberResponseDto> findMembers() {   // 전체 회원 조회
        long startTimeMs = System.currentTimeMillis();

        return memberRepository.findAll().stream()
                .peek(member->{
                    System.out.println("Before accessing teamName: " + member.getSubscription().getClass());
                    String teamName = member.getSubscription().getContentName(); // LAZY 로딩 발생
                    System.out.println("After accessing teamName: " + teamName);
                })
                .map(MemberResponseDto::from)
                .toList();
    }
//
//    public List<MemberResponseDto> getAllMembersWithTeams() {
//        return memberRepository.findAllByTeamIsNotNull()
//                .stream()
//                .map(member -> {
//                    System.out.println("Before accessing teamName: " + member.getTeam().getClass());
//                    String teamName = member.getTeam().getName(); // LAZY 로딩 발생
//                    System.out.println("After accessing teamName: " + teamName);
//                    return MemberResponseDto.from(member);
//                })
//                .toList();
//    }

    public List<MemberResponseDto> getAllMemberWithSubscription(){
        return memberRepository.findAllByTeamIsNotNull()
                .stream()
                .map(MemberResponseDto::from)
                .toList();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다: " + member.getName());
                });
    }

   @Transactional
   public void proxyTest(){

        Member findMember=em.getReference(Member.class,2L);
        System.out.println(findMember.getClass());
        System.out.println(findMember.getName());
   }

    @Transactional
    public void persistAndFind(){
        System.out.println("=== 시작 코드 ===");
        // 1. 새로운 Member 생성
        Member member = Member.builder()
                .name("이강인")
                .build();
        System.out.println("=== 생성 호출 완료 ===");

        // 2️. 영속성 컨텍스트에 저장
        em.persist(member);

        System.out.println("=== persist 호출 완료 ===");

//        if (true) {
//            throw new RuntimeException("강제 예외 발생! 트랜잭션 롤백 확인용");
//        }

        // 3️. 같은 트랜잭션 안에서는 영속성 컨텍스트 먼저 바로 조회
        Member findMember = em.find(Member.class, member.getId());

        System.out.println("조회 결과: " + findMember.getName());
        System.out.println("같은 객체? " + (member == findMember)); // true

        // 트랜잭션 commit 시점에 flush → DB에 INSERT 실행
    }
}
