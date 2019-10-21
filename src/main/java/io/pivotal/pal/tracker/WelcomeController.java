package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    String welcomeMessage = null;

    @GetMapping("/")
    public String sayHello() {
        return welcomeMessage;
    }

    public  WelcomeController(@Value("${welcome.message}") String message) {
        welcomeMessage = message;

    }
}