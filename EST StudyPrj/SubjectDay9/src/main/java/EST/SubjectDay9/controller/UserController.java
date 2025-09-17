package EST.SubjectDay9.controller;

import EST.SubjectDay9.DTO.AddUserRequest;
import EST.SubjectDay9.DTO.UserResponse;
import EST.SubjectDay9.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody AddUserRequest request) {
        return userService.addUser(request);
    }

    @GetMapping("/city/{city}")
    public List<UserResponse> findByCity(@PathVariable String city) {
        return userService.findByCity(city);
    }
}
