package clcstudy.crudSample.controller;

import clcstudy.crudSample.domain.User;
import clcstudy.crudSample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    @ResponseBody
    public List<User> getAllUser(){
        return userService.findUsers();
    }

    @PostMapping("login")
    @ResponseBody
    public Object login(@RequestBody UserForm form){
        User u = new User();
        u.setEmail(form.getEmail());
        u.setPasswd(form.getPasswd());

        Long userId = userService.login(u);
        Map<String, Long> result = new HashMap<>();
        result.put("userId", userId);

        return result;
    }

    @PostMapping("/user")
    @ResponseBody
    public Object join(@RequestBody UserForm form){
        User u = new User();
        u.setEmail(form.getEmail());
        u.setPasswd(form.getPasswd());
        u.setName(form.getName());

        long userId = userService.join(u);
        Map<String, Long> result = new HashMap<>();
        result.put("userId", userId);

        return result;
    }

    @PutMapping("/user/{id}")
    @ResponseBody
    public String updateUser(@RequestBody UserForm form, @PathVariable(name="id") Long id){
        User u = new User();
        u.setEmail(form.getEmail());
        u.setPasswd(form.getPasswd());
        u.setName(form.getName());
        System.out.println(u);
        return userService.updateUser(id, u);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public String deleteUser(@PathVariable(name="id") Long id){
        return userService.deleteUser(id);
    }

}
