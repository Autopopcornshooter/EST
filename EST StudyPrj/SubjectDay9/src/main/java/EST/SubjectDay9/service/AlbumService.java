package EST.SubjectDay9.service;

import EST.SubjectDay9.DTO.AddAddressRequest;
import EST.SubjectDay9.DTO.AddAlbumRequest;
import EST.SubjectDay9.DTO.AlbumResponse;
import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.repository.AlbumRepository;
import EST.SubjectDay9.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;



    public Album saveAlbum(AddAlbumRequest request) {
        Album album=new Album(request.getTitle()
                , userRepository.findById(request.getId()).orElse(null));
        return albumRepository.save(album);
    }

    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }

    private AlbumResponse toResponse(Album album) {
        return AlbumResponse.builder()
                .id(album.getId())
                .title(album.getTitle())
                .userId(album.getUser().getId())
                .build();
    }

    @Transactional
    public Album update(Long id, AddAlbumRequest request) {
        Album album = albumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id가 존재하지 않습니다: " + id));
        album.update(request.getTitle(), request.getUser().toEntity());
        return album;
    }
}
