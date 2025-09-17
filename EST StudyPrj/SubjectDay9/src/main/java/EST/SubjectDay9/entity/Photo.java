package EST.SubjectDay9.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name = "thumbnailUrl")
    private String thumbnailUrl;


    @Column(name="url")
    private String url;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "ALBUM_ID")
    private Album album;

}
