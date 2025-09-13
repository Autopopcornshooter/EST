package EST.TeamManagement.dto;

import EST.TeamManagement.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMemberRequest {
    private String name;


    public Member toEntity(){
        return Member.builder()
                .userName(name)
                .build();
    }

}
