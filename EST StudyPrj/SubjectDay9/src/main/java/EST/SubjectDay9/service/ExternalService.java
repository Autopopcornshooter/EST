package EST.SubjectDay9.service;

import EST.SubjectDay9.DTO.AlbumResponse;
import EST.SubjectDay9.DTO.PhotoResponse;
import EST.SubjectDay9.DTO.UserResponse;
import EST.SubjectDay9.entity.*;
import EST.SubjectDay9.repository.AlbumRepository;
import EST.SubjectDay9.repository.PhotoRepository;
import EST.SubjectDay9.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExternalService {
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final PhotoRepository photoRepository;

    private final RestTemplate restTemplate = new RestTemplate();

    // 1. User 적재
    public void loadUsers() {
        String url = "https://jsonplaceholder.typicode.com/users";
        ResponseEntity<List<UserResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});
        List<UserResponse> users = response.getBody();

        for (UserResponse dto : users) {
            Company company = Company.builder()
                    .name(dto.getCompany().getName())
                    .catchPhrase(dto.getCompany().getCatchPhrase())
                    .bs(dto.getCompany().getBs())
                    .build();

            Geo geo = Geo.builder()
                    .lat(dto.getAddress().getGeo().getLat())
                    .lng(dto.getAddress().getGeo().getLng())
                    .build();

            Address address = Address.builder()
                    .street(dto.getAddress().getStreet())
                    .suite(dto.getAddress().getSuite())
                    .city(dto.getAddress().getCity())
                    .zipcode(dto.getAddress().getZipcode())
                    .geo(geo)
                    .build();

            User user = User.builder()
                    .id(dto.getId()) // 외부 id를 그대로 PK로 쓰고 싶으면 set 가능
                    .name(dto.getName())
                    .email(dto.getEmail())
                    .phone(dto.getPhone())
                    .company(company)
                    .address(address)
                    .password("default1234") // 외부 API에는 password 없음, 임의 값
                    .build();

            userRepository.save(user);
        }
        log.info("✅ Users 저장 완료: {}건", users.size());
    }

    // 2. Album 적재
    public void loadAlbums() {
        String url = "https://jsonplaceholder.typicode.com/albums";
        ResponseEntity<List<AlbumResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});
        List<AlbumResponse> albums = response.getBody();

        for (AlbumResponse dto : albums) {
            User user = userRepository.findById(dto.getUserId()).orElse(null);

            if (user != null) {
                Album album = Album.builder()
                        .id(dto.getId())
                        .title(dto.getTitle())
                        .user(user)
                        .build();
                albumRepository.save(album);
            }
        }
        log.info("✅ Albums 저장 완료: {}건", albums.size());
    }

    // 3. Photo 적재
    public void loadPhotos() {
        String url = "https://jsonplaceholder.typicode.com/photos";
        ResponseEntity<List<PhotoResponse>> response =
                restTemplate.exchange(url, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {});
        List<PhotoResponse> photos = response.getBody();

        for (PhotoResponse dto : photos) {
            Album album = albumRepository.findById(dto.getId()).orElse(null);

            if (album != null) {
                Photo photo = Photo.builder()
                        .id(dto.getId())
                        .thumbnailUrl(dto.getThumbnailUrl())
                        .album(album)
                        .build();
                photoRepository.save(photo);
            }
        }
        log.info("✅ Photos 저장 완료: {}건", photos.size());
    }
}
