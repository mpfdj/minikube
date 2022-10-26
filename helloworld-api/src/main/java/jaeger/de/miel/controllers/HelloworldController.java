package jaeger.de.miel.controllers;

import jaeger.de.miel.model.Hello;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class HelloworldController {

    @RequestMapping("/hello")
    public Hello hello(@RequestParam(name="name", defaultValue = "world") String name) {
        Hello h = new Hello();
        if (name.isEmpty()) h.setGreeting("Hello world");
        else h.setGreeting("Hello " + name);
        return h;
    }

    @RequestMapping("/ping")
    public String ping() {
        return "pong";
    }

}
