package co.zaCrudApplication.controller;

import co.zaCrudApplication.model.User;
import co.zaCrudApplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path="/")
public class UserController
{
    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("create-user")
    public ResponseEntity<User> createUser(@RequestBody User user)
    {
        if(this.userService.createUser(user))
        {
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers()
    {
        List<User> all_users = this.userService.getUsers();
        return new ResponseEntity<>(all_users, HttpStatus.OK);
    }

    @GetMapping(value = {"/",""})
    public String homePage()
    {
        return "Hello World.";
    }
}