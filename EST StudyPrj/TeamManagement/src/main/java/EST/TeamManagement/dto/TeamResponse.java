package EST.TeamManagement.dto;

import EST.TeamManagement.domain.Member;
import EST.TeamManagement.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TeamResponse {
    private Long id;
    private String name;

    public TeamResponse(Team team){
        this.id=team.getId();
        this.name= team.getTeamName();
    }
}
