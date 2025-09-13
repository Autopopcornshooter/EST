package EST.day;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(@RequestParam(defaultValue = "익명") String name, @RequestParam String email) {
        return "Hello " + name + "! Contact me at : " + email;
    }

}
