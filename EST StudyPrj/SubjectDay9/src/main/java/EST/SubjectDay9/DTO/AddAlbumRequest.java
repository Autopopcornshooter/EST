package EST.SubjectDay9.DTO;

import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddAlbumRequest {
    private Long id;
    private String title;
    private AddUserRequest user;

    public Album toEntity(){
        return Album.builder()
                .title(this.title)
                .user(this.user.toEntity())
                .build();
    }
}
