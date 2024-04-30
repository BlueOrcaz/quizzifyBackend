package dev.BlueOrcaz.Quizzify.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class PingController {
    @GetMapping("/ping") // sends a GET request to ping the backend server; this was done so that google cloud keeps the server up and running by pinging it once every 15 min
    public String pinged() { // return hello when pinged
        return "Hello";
    }
}
