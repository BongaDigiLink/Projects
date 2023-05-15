package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.service.InternService;

import java.util.List;
import java.util.Optional;

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
            return  new ResponseEntity<>(this.internService.getUserByEmail(intern.getEmail()),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
