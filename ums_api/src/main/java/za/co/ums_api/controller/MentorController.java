package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.models.Records;
import za.co.ums_api.service.InternService;
import za.co.ums_api.service.MentorService;

import java.util.List;

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

    @PostMapping("/login")
    public ResponseEntity<Mentor> login(@RequestBody Mentor mentor)
    {
        if(this.mentorService.checkMentor(mentor.getEmail()))
        {
            return  new ResponseEntity<>(this.mentorService.getUserByEmail(mentor.getEmail()), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(path="/registration")
    public ResponseEntity<Boolean> register(Mentor mentor)
    {
        return new ResponseEntity<>(mentorService.registerMentor(mentor), HttpStatus.OK);
    }

    //------------------------------------Intern Management Controllers

    @GetMapping(path = "/all-interns/")
    public ResponseEntity<List<Intern>> getInterns()
    {
        List<Intern> list = this.internService.getInterns();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/inactive-users/")
    public ResponseEntity<List<Intern>> getInactiveInterns()
    {
        List<Intern> inactiveUsers = this.internService.getInactiveInterns();

        if(inactiveUsers == null)
        {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(inactiveUsers, HttpStatus.OK);
    }

    /**
     * @param id intern to update
     * @param intern data from client
     * @return update object.
     */
    @PutMapping(path = "/edit-intern/{id}")
    public ResponseEntity<Intern> updateIntern(@PathVariable("id") Integer id, @RequestBody Intern intern)
    {
        System.out.println("New details from front end "+intern.toString());
        if(this.mentorService.checkUser(id))
        {
            this.mentorService.updateIntern(id, intern);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/intern-user/{id}")
    public ResponseEntity<Intern> getInternById(@PathVariable("id") @RequestBody Integer id)
    {
        if(this.mentorService.getUserById(id) == null)
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(this.mentorService.getUserById(id), HttpStatus.OK);
    }

    @DeleteMapping (path = "/delete-account/{id}")
    public ResponseEntity<Boolean> deleteIntern(@PathVariable("id") @RequestBody Integer id)
    {
        //Optional<Intern> intern_ = mentorService.deactivateIntern(id);
        if(this.mentorService.getUserById(id) != null)
        {
            if(this.mentorService.deleteIntern(id))
            {
                return new ResponseEntity<>(true, HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Boolean> deactivateIntern(@PathVariable("id") @RequestBody Integer id)
    {
        if(this.mentorService.checkUser(id))
        {
            this.mentorService.deactivateIntern(id);
            return new ResponseEntity<>(true, HttpStatus.OK);
        }
        return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    @GetMapping(path="/mentors")
    public ResponseEntity<List<Mentor>> getMentors()
    {
        return new ResponseEntity<>(mentorService.getMentors(), HttpStatus.OK);
    }


    //--------------------------------------Skills Controllers.
    @GetMapping(path = "/skills-offered/")
    public ResponseEntity<List<LearningSkill>> getAllSkills()
    {
        return new ResponseEntity<>(mentorService.skills(), HttpStatus.OK);
    }

    @PostMapping(path = "/add-skill/")
    public ResponseEntity<Boolean> createProgramme(@RequestBody LearningSkill task)
    {
        System.out.println("Task Name: "+task.getDueDate());
        mentorService.createTask(task);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(path="/update-skill/{id}")
    public ResponseEntity<LearningSkill> updateSkill(@PathVariable("id") Integer id,@RequestBody LearningSkill skill)
    {
        LearningSkill updated = mentorService.updateSkill(id,skill);
        return  new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove-skill/")
    public Boolean removeSkill()
    {

        return false;
    }

    //Create and Update, Routes for Training Skills Management
    @PostMapping(path="/create-task/")
    public ResponseEntity<LearningSkill> createTask(@RequestBody LearningSkill task)
    {
        LearningSkill createdTask = this.mentorService.createTask(task);
        return  new ResponseEntity<>(createdTask, HttpStatus.OK);
    }

    @GetMapping("/all-tasks/")
    public ResponseEntity<List<LearningSkill>> getAllTasks()
    {
        if(this.mentorService.tasksExist())
        {
            List<LearningSkill> tasks = this.mentorService.skills();
            return new ResponseEntity<>(tasks, HttpStatus.OK);
        }

        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/get-task/{id}")
    public ResponseEntity<LearningSkill> getTask(@PathVariable("id") @RequestBody Integer id)
    {
        if(this.mentorService.taskExist(id))
        {
            LearningSkill task = this.mentorService.getTask(id);
            return new ResponseEntity<>(task, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/create-record/{id}")
    public ResponseEntity<?> createRecord(@PathVariable("id") Integer id, @RequestBody Records data)
    {
        if(this.mentorService.createRecord(id,data))
        {
            return new ResponseEntity<>(HttpStatus.CREATED);
        };

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/get-records/")
    public ResponseEntity<?> getRecords(@RequestBody Integer email)
    {
        if(this.mentorService.checkUser(email))
        {
            List<Records> records = this.mentorService.getUserRecords(email);
            return new ResponseEntity<>(records, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
