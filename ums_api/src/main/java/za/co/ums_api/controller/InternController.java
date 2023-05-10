package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.service.InternService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/intern")
public class InternController
{
    //private final InternService internService;
    //private final InternRepository internRepository;

    @Autowired
    InternRepository internRepository;

    @Autowired
    InternService internService;

//    @Autowired
//    public InternController(InternService internService, InternRepository internRepository)
//    {
//        this.internService = internService;
//        this.internRepository = internRepository;
//    }

    //------------------------------------Intern Login/Reg (Auth)
    @PostMapping("/register/")
    public ResponseEntity<Intern> registerIntern(@RequestBody Intern intern)
    {
        //System.out.println("Inputs from front end : "+intern.toString());
        try
        {
            Intern internObj = internRepository.save(
                    new Intern(intern.getEmail(), intern.getName(), intern.getSurname(), intern.getTrainingField(), intern.getPassword()));

            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/Login/")
    public ResponseEntity<Intern> login(@RequestBody Intern intern)
    {
        Intern foundUser = this.internService.login(intern);

        if( foundUser != null)
        {
            String password = foundUser.getPassword();

            if(password.equals(intern.getPassword()))
            {
                return new ResponseEntity<Intern>(foundUser,HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        else
        {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //------------------------------------Intern PUT update routes
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

    @GetMapping("/skills-tasks/{name}")
    public ResponseEntity<List<LearningSkill>> getTasks(@PathVariable("name") String name)
    {
        //List<LearningSkill> all = this.internService.getAllSkills();
        List<LearningSkill> tasks = this.internService.getTasks(this.internService.getAllSkills(), name);

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }
}
