package EST.day12.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access= AccessLevel.PROTECTED)
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

//    // 지연 로딩 설정: Member를 가져올 때 Team 객체는 로딩하지 않음
//    @ManyToOne//(fetch = FetchType.LAZY)
//    // Member라는 테이블에 team_id 라는 컬럼이 생김. 이 컬럼이 Team 테이블의 PK 참조
//    @JoinColumn(name = "team_id")
//    private Team team;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="subscription_id")
    private Subscription subscription;

    @Builder
    public Member(Long id, String name) {
        this.id = id;
        this.name = name;
    }

}
