package EST.TeamManagement.domain;

import EST.TeamManagement.dto.AddTeamRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TEAM_ID")
    private Long id;

    @Column(name="name")
    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>(); //NPE 예방을 위한 초기화
    @Builder
    public Team(String teamName){
        this.teamName=teamName;
    }

    public void update(AddTeamRequest request){
        this.teamName= request.getName();
    }

}
