package EST.day12.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Generated;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String contentName;

    @OneToMany(mappedBy = "subscription")
    private List<Member> members=new ArrayList<>();

    @Builder
    public Subscription(String contentName){
        this.contentName=contentName;
    }
}
