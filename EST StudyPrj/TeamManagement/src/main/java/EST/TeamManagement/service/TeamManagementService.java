package EST.TeamManagement.service;

import EST.TeamManagement.domain.Member;
import EST.TeamManagement.domain.Team;
import EST.TeamManagement.dto.*;
import EST.TeamManagement.repository.MemberRepository;
import EST.TeamManagement.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamManagementService {
    private MemberRepository memberRepository;
    private TeamRepository teamRepository;

    @Autowired
    public TeamManagementService(MemberRepository memberRepository, TeamRepository teamRepository) {
        this.memberRepository = memberRepository;
        this.teamRepository = teamRepository;
    }

    //Member 정보 추가
    public MemberResponse addMemberToTeam(Long team_id, AddMemberRequest request) {
        Team team = teamRepository.findById(team_id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 팀은 존재하지 않습니다: " + team_id));
        Member member = memberRepository.save(new Member(request.getName(), team));
        return new MemberResponse(member);
    }

    // Team 정보 추가
    public TeamResponse addTeam(AddTeamRequest request) {
        Team team = teamRepository.save(request.toEntity());
        return new TeamResponse(team);
    }

    //Member+Team 정보 조회
    public List<MemberResponse> getAllMembers() {
        return memberRepository.findAll()
                .stream()
                .map(MemberResponse::new)
                .toList();
    }

    // Team 정보 조회
    public List<TeamResponse> getAllTeams() {
        return teamRepository.findAll()
                .stream()
                .map(TeamResponse::new)
                .toList();
    }

    // Team 정보 수정
    public TeamResponse updateTeamById(Long id, AddTeamRequest request) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id의 Team이 존재하지 않습니다: " + id));
        team.update(request);
        teamRepository.save(team);
        return new TeamResponse(team);
    }


}
