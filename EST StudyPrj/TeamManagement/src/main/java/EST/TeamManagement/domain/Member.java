package EST.TeamManagement.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="name")
    private String userName;

    @ManyToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    @Builder
    public Member(String userName, Team team){
        this.userName=userName;
        this.team=team;
    }
    public void update(String userName,Team team){
        this.userName=userName;
        this.team =team;
    }
}
