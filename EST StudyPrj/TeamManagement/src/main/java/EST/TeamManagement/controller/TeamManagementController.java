package EST.TeamManagement.controller;

import EST.TeamManagement.domain.Member;
import EST.TeamManagement.domain.Team;
import EST.TeamManagement.dto.*;
import EST.TeamManagement.service.TeamManagementService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TeamManagementController {

    private TeamManagementService teamManagementService;

    @Autowired
    public TeamManagementController(TeamManagementService teamManagementService) {
        this.teamManagementService = teamManagementService;
    }

    //Member 정보 추가
    @PostMapping("/teams/{team_id}/members")
    public ResponseEntity<MemberResponse> addMember(@PathVariable Long team_id, @RequestBody AddMemberRequest request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(teamManagementService.addMemberToTeam(team_id, request));
    }

    //Team 정보 추가
    @PostMapping("/teams")
    public ResponseEntity<TeamResponse> addTeam(@RequestBody AddTeamRequest request) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(teamManagementService.addTeam(request));
    }

    //Member+Team 정보 조회
    @GetMapping("/members")
    public ResponseEntity<List<MemberResponse>> getMembers() {
        List<MemberResponse> list = teamManagementService.getAllMembers();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    //Team 정보 조회
    @GetMapping("/teams")
    public ResponseEntity<List<TeamResponse>> getTeams() {
        List<TeamResponse> list = teamManagementService.getAllTeams();
        return ResponseEntity.status(HttpStatus.OK)
                .body(list);
    }

    //Team 정보 수정
    @PutMapping("/teams/{id}")
    public ResponseEntity<TeamResponse> updateTeamById(@PathVariable Long id, @RequestBody AddTeamRequest request) {
        TeamResponse response = teamManagementService.updateTeamById(id, request);
        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
