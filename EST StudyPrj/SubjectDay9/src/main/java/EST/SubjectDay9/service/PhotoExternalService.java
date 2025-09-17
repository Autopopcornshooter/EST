package EST.SubjectDay9.service;

import EST.SubjectDay9.entity.Photo;
import EST.SubjectDay9.repository.PhotoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class PhotoExternalService {
    private final PhotoRepository repository;

    public PhotoExternalService(PhotoRepository repository){
        this.repository=repository;
    }

    public void call(){
        String url="jsonplaceholder.typicode.com";

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<List<Photo>> response=
                restTemplate.exchange(url, HttpMethod.GET,null
                ,new ParameterizedTypeReference<>() {});

        log.info("code: {}",response.getStatusCode());
    }
}
