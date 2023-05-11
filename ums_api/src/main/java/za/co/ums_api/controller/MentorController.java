package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.service.InternService;
import za.co.ums_api.service.MentorService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/mentor")
public class MentorController
{
    private final MentorService mentorService;
    private final InternService internService;

    @Autowired
    public MentorController(MentorService mentorService, InternService internService)
    {
        this.mentorService = mentorService;
        this.internService = internService;
    }


    //-------------------------------------Mentor Login (Auth)
    @GetMapping(path = "/login")
    public Mentor login(Integer id)
    {
        Mentor mentor = mentorService.getMentor(id);
        return mentor;
    }

    @PostMapping(path="/registration")
    public ResponseEntity<Boolean> register(Mentor mentor)
    {
        return new ResponseEntity<>(mentorService.registerMentor(mentor), HttpStatus.OK);
    }

    //------------------------------------Intern Management Controllers

    @GetMapping(path = "/all-interns")
    public ResponseEntity<List<Intern>> getInterns()
    {
        List<Intern> list = this.internService.getInterns();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping(path = "/edit-intern/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable("id") Integer id, @RequestBody Intern intern)
    {
        System.out.println("New details from front end "+intern.toString());
        Intern user = this.mentorService.getUserById(id);
        if(user == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        user.setEmail(intern.getEmail());
        user.setSurname(intern.getSurname());
        user.setName(intern.getName());

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(path = "/intern/{email}")
    public ResponseEntity<Intern> getIntern(@PathVariable("email") String email)
    {
        if(this.mentorService.getUserByEmail(email) == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.mentorService.getUserByEmail(email), HttpStatus.OK);
    }

    @GetMapping("/intern-user/{email}")
    public ResponseEntity<Intern> getInternById(@PathVariable("email") @RequestBody Integer email)
    {
        if(this.mentorService.getUserById(email) == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.mentorService.getUserById(email), HttpStatus.OK);
    }

    @DeleteMapping (path = "/deactivate/{id}")
    public ResponseEntity<Intern> deleteIntern(@PathVariable("id") @RequestBody Integer id)
    {
        Optional<Intern> intern_ = mentorService.deactivateIntern(id);
        return new ResponseEntity<Intern>(HttpStatus.OK);
    }


    @GetMapping(path="/mentors")
    public ResponseEntity<List<Mentor>> getMentors()
    {
        return new ResponseEntity<>(mentorService.getMentors(), HttpStatus.OK);
    }


    //--------------------------------------Skills Controllers.
    @GetMapping(path = "/skills-offered")
    public ResponseEntity<List<LearningSkill>> getAllSkills()
    {
        return new ResponseEntity<>(mentorService.skills(), HttpStatus.OK);
    }

    @PostMapping(path = "/add-skill")
    public ResponseEntity<Boolean> createProgramme(@RequestBody LearningSkill newskill)
    {
        mentorService.createSkill(newskill);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(path="/update-skill")
    public ResponseEntity<LearningSkill> updateSkill(@RequestBody LearningSkill skill)
    {
        LearningSkill updated = mentorService.updateSkill(skill);
        return  new ResponseEntity<LearningSkill>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove-skill")
    public Boolean removeSkill()
    {

        return false;
    }

    //Create and Update, Routes for Training Skills Management
    @PostMapping(path="/create-task")
    public ResponseEntity<LearningSkill> createTask(@RequestBody LearningSkill task)
    {
        LearningSkill createdTask = this.mentorService.createTask(task);
        return  new ResponseEntity<>(createdTask, HttpStatus.OK);
    }


}
