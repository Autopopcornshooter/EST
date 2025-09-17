package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.entity.Photo;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddPhotoRequest {
    private Long id;

    private String thumbnailUrl;

    private String title;

    private String url;

    private Album album;

    public Photo toEntity(){
        return Photo.builder()
                .thumbnailUrl(this.thumbnailUrl)
                .title(this.title)
                .url(this.url)
                .album(this.album)
                .build();
    }
}
