package EST.day10.controller;

import EST.day10.service.MemberService;
import org.springframework.web.bind.annotation.*;
import EST.day10.domain.Member;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    /**
     * 회원 가입 (POST)
     */
    @PostMapping
    public String join(@RequestBody Member member) {
        Long memberId = memberService.join(member);
        return "가입 완료! 회원 ID = " + memberId;
    }

    /**
     * 전체 회원 조회 (GET)
     */
    @GetMapping
    public List<Member> getMembers() {
        return memberService.findMembers();
    }

    /**
     * 특정 회원 조회 (GET)
     */
    @GetMapping("/{id}")
    public Optional<Member> getMember(@PathVariable Long id) {
        return memberService.findOne(id);
    }
}
