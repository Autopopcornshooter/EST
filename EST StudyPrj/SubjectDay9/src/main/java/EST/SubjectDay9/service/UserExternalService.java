package EST.SubjectDay9.service;

import EST.SubjectDay9.entity.User;
import EST.SubjectDay9.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Service
public class UserExternalService {
    private final UserRepository userRepository;

    public UserExternalService(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    public void call(){
        String url="https://jsonplaceholder.typicode.com/users";

        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<List<User>> response=
                restTemplate.exchange(url, HttpMethod.GET,null
                ,new ParameterizedTypeReference<>(){});

        log.info("code: {}",response.getStatusCode());
        List<User> user=response.getBody();
        log.info("content: {}",user);
    }
}
