package tfalc.parking.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@ApiIgnore
@RequestMapping("/")
public class HelloController {

    public String hello() {
        return "Welcome to the parking application!";
    }
}
