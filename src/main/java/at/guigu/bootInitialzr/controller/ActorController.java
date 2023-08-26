package at.guigu.bootInitialzr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actor")
public class ActorController {

    @GetMapping("/name/{id}")
    public String getName(Long id){
        return "Kai Yang" + id;
    }

    @GetMapping("/name2")
    public String getName2(Long id){
        return "Kai Yang";
    }
}
