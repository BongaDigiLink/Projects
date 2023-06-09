package za.co.CrudApp.CrudApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.CrudApp.CrudApplication.model.Contact;
import za.co.CrudApp.CrudApplication.service.UserService;
import za.co.CrudApp.CrudApplication.util.CustomResponse;

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
    public String createUser(@RequestBody Contact contact)
    {
        CustomResponse customResponse;
        if(this.userService.createUser(contact))
        {
            customResponse = new CustomResponse(new ResponseEntity<>(contact, HttpStatus.CREATED));
            return customResponse.response();
        }
        customResponse = new CustomResponse(new ResponseEntity<>("null",HttpStatus.METHOD_NOT_ALLOWED));
        return customResponse.response();
    }

    @PostMapping("create-user")
    public ResponseEntity<Contact> createContact(@RequestBody Contact contact)
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

    @PutMapping("update-user/{email}")
    public String updateUser(@PathVariable("email") String email, @RequestBody Contact contact)
    {
        CustomResponse customResponse;
        if(this.userService.updateContact(email, contact))
        {
            customResponse = new CustomResponse(new ResponseEntity<>(HttpStatus.ACCEPTED));
            return customResponse.response();
        }
        customResponse = new CustomResponse(new ResponseEntity<>("Operation unsuccessful",HttpStatus.BAD_REQUEST));
        return customResponse.response();
    }

    @DeleteMapping("delete-user/{email}")
    public String deleteUser(@PathVariable("email") String email)
    {
        CustomResponse customResponse;
        if(this.userService.deleteUser(email))
        {
            customResponse = new CustomResponse(new ResponseEntity<>("User account deleted.",HttpStatus.OK));
            return  customResponse.response();
        }
        customResponse = new CustomResponse(new ResponseEntity<>("Account not deleted.", HttpStatus.BAD_REQUEST));
        return customResponse.response();
    }

    @GetMapping(value = {"/",""})
    public String homePage()
    {
        return "This is a default access page (:8080, /...), no authentication required.";
    }

    @GetMapping(value = "home")
    public String authHome()
    {
        return "This is a home page (8080:/home), it requires authentication.";
    }
}