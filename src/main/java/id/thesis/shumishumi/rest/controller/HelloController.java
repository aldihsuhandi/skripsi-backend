package id.thesis.shumishumi.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(String name) {
        if (name == null) {
            name = "World";
        }
        return "Hello " + name + "!";
    }
}
