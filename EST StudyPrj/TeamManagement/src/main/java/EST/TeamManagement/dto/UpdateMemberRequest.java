package EST.TeamManagement.dto;

import EST.TeamManagement.domain.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateMemberRequest {
    private String name;
    private Team team;
}
