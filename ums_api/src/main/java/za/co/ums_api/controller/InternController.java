package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.service.InternService;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping(path = "/intern")
public class InternController
{
    @Autowired
    InternRepository internRepository;

    @Autowired
    InternService internService;

    //------------------------------------Intern Login/Reg (Auth)
    @PostMapping("/register/")
    public ResponseEntity<Intern> registerIntern(@RequestBody Intern intern)
    {
        if(this.internService.createNewUser(intern))
        {
            return new ResponseEntity<>(
                    this.internService.getUserByEmail(intern.getEmail()), HttpStatus.CREATED);
        }
        else
        {
            System.out.println("Issue registering user. http Status-> "+HttpStatus.CONFLICT);
            return new ResponseEntity<>(null, HttpStatus.CONFLICT);
        }
    }

    @PostMapping ("/login/")
    public ResponseEntity<Intern> login(@RequestBody Intern intern)
    {
        System.out.println("Data from API (auth data) : "+intern.getEmail()+"-"+intern.getPassword());
        if(this.internService.checkUser(intern.getEmail()))
        {
            if(compareIncomingPassword(intern.getEmail(), hashPassword(intern.getPassword())))
            {
                return new ResponseEntity<>(this.internService.getUserByEmail(intern.getEmail()),
                        HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * compares input password and password already in database.
     * @param password from user
     * @return
     */
    private String hashPassword(String password)
    {
        try
        {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytePassword = digest.digest(
                    password.getBytes());

            BigInteger no = new BigInteger(1, bytePassword);
            String hashedPassword = no.toString(16);

            while (hashedPassword.length() < 32) {
                hashedPassword = "0" + hashedPassword;
            }
            return hashedPassword;

        }
        catch (NoSuchAlgorithmException e)
        {
            throw new RuntimeException(e);
        }
    }

    private Boolean compareIncomingPassword(String email, String inPassword)
    {
        Intern user = this.internService.getUserByEmail(email);

        if(user.getPassword().toString().equals(inPassword))
        {
            return true;
        }
        return false;
    }



    //------------------------------------Intern PUT update routes

    @GetMapping("/intern-user/{id}")
    public ResponseEntity<Intern> getInternUser(@PathVariable("id") @RequestBody Integer id)
    {
        return new ResponseEntity<>(this.internService.getInternById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable("id") Long id,@RequestBody Intern intern)
    {
        Intern upDatedUser = this.internService.updateIntern((intern));
        return new ResponseEntity<>(upDatedUser, HttpStatus.CREATED);
    }

    //------------------------------------Intern skills routes
    @GetMapping("/skills")
    public ResponseEntity<List<LearningSkill>> getSkills()
    {
        return new ResponseEntity<>(this.internService.getAllSkills(), HttpStatus.OK);
    }

    @GetMapping("/tasks/")
    public ResponseEntity<List<LearningSkill>> getTasks()
    {
        List<LearningSkill> all = this.internService.getAllSkills();
//        List<LearningSkill> tasks = this.internService.getTasks();

        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/skills-tasks/{name}")
    public ResponseEntity<List<LearningSkill>> getTasks(@PathVariable("name") String name)
    {
        //List<LearningSkill> all = this.internService.getAllSkills();
        List<LearningSkill> tasks = this.internService.getTasks(this.internService.getAllSkills(), name);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

}
