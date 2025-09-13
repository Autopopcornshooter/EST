package EST.blog.service;

import EST.blog.domain.User;
import EST.blog.dto.AddUserRequest;
import EST.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder encoder;
    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder encoder){
        this.userRepository=userRepository;
        this.encoder=encoder;
    }

    public User save(AddUserRequest dto){
        return userRepository.save(
                User.builder()
                .email(dto.getEmail())
                .password(encoder.encode(dto.getPassword()))    //패스워드 암호화
                .build()
        );
    }
}
