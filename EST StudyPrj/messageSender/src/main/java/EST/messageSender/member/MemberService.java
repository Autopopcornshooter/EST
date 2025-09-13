package EST.messageSender.member;

import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){

        this.memberRepository = memberRepository;
    }

    public void insertMember(){
        memberRepository.save(new Member(1L,"길동"));
    }


    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMemberById(Long id){
        return memberRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 ID의 멤버가 없습니다: "+id));
    }
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
