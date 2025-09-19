package EST.day12.controller;

import EST.day12.domain.Member;
import EST.day12.dto.MemberResponseDto;
import EST.day12.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    /**
     * 회원 가입
     */
    @PostMapping("/members")
    public String createMember(@RequestBody Member member) {
        Long memberId = memberService.join(member);
        return "회원가입 완료! 생성된 회원 ID: " + memberId;
    }

    /**
     * 전체 회원 조회
     */
    @GetMapping("/members")
    public List<MemberResponseDto> getMembers() {
        return memberService.findMembers();
    }

    //    @GetMapping("/members/teams")
//    public List<MemberResponseDto> getAllMembersWithTeams() {
//        return memberService.getAllMembersWithTeams();
//    }
    @GetMapping("/members/subscriptions")
    public List<MemberResponseDto> getAllMemberWithSubscription() {
        return memberService.getAllMemberWithSubscription();
    }

    @GetMapping("/test")
    public void persistAndFind() {
        memberService.persistAndFind();
    }

    @GetMapping("/proxyTest")
    public void proxyTest() {
        memberService.proxyTest();
    }

}
