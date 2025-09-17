package EST.SubjectDay9.service;

import EST.SubjectDay9.DTO.AddAlbumRequest;
import EST.SubjectDay9.DTO.AddUserRequest;
import EST.SubjectDay9.DTO.AlbumResponse;
import EST.SubjectDay9.entity.Album;
import EST.SubjectDay9.entity.User;
import EST.SubjectDay9.repository.AlbumRepository;
import EST.SubjectDay9.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumExternalService {
    private final AlbumRepository albumRepository;
    private final UserRepository userRepository;
    public void call(){
        String url="https://jsonplaceholder.typicode.com/albums";

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<List<Album>> response=
                restTemplate.exchange(url, HttpMethod.GET, null
                        , new ParameterizedTypeReference<>() {});

        log.info("code: {}",response.getStatusCode());
    }
    public void save(){
        String url="https://jsonplaceholder.typicode.com/albums";

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<List<AlbumResponse>> response=
                restTemplate.exchange(url, HttpMethod.GET, null
                        , new ParameterizedTypeReference<>() {});
        List<AlbumResponse> albums=response.getBody();
        log.info("데이터 개수: {}",albums.size());

        for(AlbumResponse dto:albums){
            log.info("title: {}, user: {}",dto.getTitle(),dto.getUserId());
            User user=userRepository.findById(dto.getUserId()).orElse(null);
            Album album=Album.builder()
                    .title(dto.getTitle())
                    .user(user)
                    .build();
            albumRepository.save(album);
        }
    }
}
