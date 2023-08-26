package at.guigu.bootInitialzr.controller;

import at.guigu.bootInitialzr.bean.User;
import at.guigu.bootInitialzr.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all/*")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/userID/{val}")
    public List<User> findByUserID(@PathVariable("val") Integer userID) {
        List<User> users = userService.findByUserID(userID);
        if (users == null || users.isEmpty()) {
            users = userService.findByUserID(userID.toString());
        }
        return users;
    }

    @GetMapping("/userId/{val}")
    public User findUserByUserID(@PathVariable("val") String userID){
        return userService.findUserByUserID(userID);
    }

    @GetMapping("/Id/{val}")
    public User findById(@PathVariable("val") String id) {
        return userService.findById(id);
    }
    @GetMapping("/findUserByGender/{val}")
    public User findUserByGender(@PathVariable("val") String gender){
        return userService.findUserByGender(gender);
    }
    @GetMapping("/findUsersByGender/{val}")
    public List<User> findUsersByGender(@PathVariable("val") String gender){
        return userService.findUsersByGender(gender);
    }

    @GetMapping("/findOneUserID/user")
    public User findUserByUserIDWithRequestParam(@RequestParam(value = "userId", required = false, defaultValue = "") Integer userId) {
        return userService.findUserByUserID(userId);
    }

    @GetMapping("/lastName/{val}")
    public List<User> findByLastName(@PathVariable("val") String lastName) {
        return userService.findByLastName(lastName);
    }

    @GetMapping("/firstName/{val}")
    public List<User> findByFirstName(@PathVariable("val") String firstName) {
        return userService.findByFirstName(firstName);
    }

    @GetMapping("/stringUserID/{val}")
    public List<User> findUsersByUserIDIs(@PathVariable("val") String userID){
        return userService.findUsersByUserIDIs(userID);
    }
    @GetMapping("/integerUserID/{val}")
    public List<User> findUsersByUserIDIs(@PathVariable("val") Integer userID){
        return userService.findUsersByUserIDIs(userID);
    }

    @PostMapping("/post")
    public User addNewUser(@RequestBody User user) {
        return userService.addNewUser(user);
    }

}

