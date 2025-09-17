package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Photo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoResponse {
    private Long id;
    private String thumbnailUrl;

    private String title;

    private String url;

    private AlbumResponse albumResponse;



}
