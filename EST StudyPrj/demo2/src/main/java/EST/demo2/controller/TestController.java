package EST.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(@RequestParam String query, ModelAndView modelAndView) {

        modelAndView.addObject("query", query);

        return "test";
    }
}