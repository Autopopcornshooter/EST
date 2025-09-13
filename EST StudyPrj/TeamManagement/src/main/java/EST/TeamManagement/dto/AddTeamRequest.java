package EST.TeamManagement.dto;

import EST.TeamManagement.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddTeamRequest {
    private String name;

    public Team toEntity(){
        return Team.builder()
                .teamName(name)
                .build();
    }

}
