package co.zaCrudApplication.controller;

import co.zaCrudApplication.model.Contact;
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

    @PostMapping("create-contact")
    public ResponseEntity<Contact> createUser(@RequestBody Contact contact)
    {
        if(this.userService.createUser(contact))
        {
            return new ResponseEntity<>(contact, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @GetMapping("users")
    public ResponseEntity<List<Contact>> getUsers()
    {
        List<Contact> all_contacts = this.userService.getUsers();
        return new ResponseEntity<>(all_contacts, HttpStatus.OK);
    }

    @PutMapping("/update-user/{email}")
    public ResponseEntity<?> updateUser(@PathVariable("email") String email, @RequestBody Contact contact)
    {
        if(this.userService.updateContact(email, contact))
        {
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Operation unsuccessful",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = {"/",""})
    public String homePage()
    {
        return "Hello World.";
    }
}