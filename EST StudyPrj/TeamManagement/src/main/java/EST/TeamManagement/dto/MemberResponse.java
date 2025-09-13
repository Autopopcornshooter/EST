package EST.TeamManagement.dto;

import EST.TeamManagement.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class MemberResponse {
    private Long id;
    private String name;
    private TeamResponse team;

    public MemberResponse(Member member){
        this.id=member.getId();
        this.name=member.getUserName();
        this.team=new TeamResponse(member.getTeam());
    }

}
