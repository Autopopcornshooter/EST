package EST.blog.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)  //엔티티 변화를 감지해 지정한 변경점 적용?
@Getter
@Entity
@NoArgsConstructor
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", updatable = false)
    private Long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="content", nullable = false)
    private String content;

    @CreatedDate                    //생성시간
    @Column(name="created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate               //마지막 수정 시간
    @Column(name="updated_at")
    private LocalDateTime updateAt;

    @Builder
    public Article(String title, String content){
        this.title=title;
        this.content=content;
    }

    public void update(String title, String content){
        this.title=title;
        this.content=content;
    }
}
