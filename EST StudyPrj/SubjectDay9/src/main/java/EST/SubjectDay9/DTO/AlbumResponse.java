package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class AlbumResponse {
    private Long id;
    private String title;
    private Long userId;
}
