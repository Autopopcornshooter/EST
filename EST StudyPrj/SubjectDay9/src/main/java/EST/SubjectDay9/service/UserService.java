package EST.SubjectDay9.service;

import EST.SubjectDay9.DTO.AddUserRequest;
import EST.SubjectDay9.DTO.UserResponse;
import EST.SubjectDay9.entity.User;
import EST.SubjectDay9.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponse addUser(AddUserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .company(request.getCompany())
                .phone(request.getPhone())
                .city(request.getCity())
                .password(request.getPassword())
                .build();
        return toResponse(userRepository.save(user));
    }

    public List<UserResponse> findByCity(String city) {
        return userRepository.findByCity(city)
                .stream().map(this::toResponse).toList();
    }

    private UserResponse toResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .company(user.getCompany())
                .city(user.getCity())
                .build();
    }
}
