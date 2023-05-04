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

    @GetMapping(path = "/login")
    public Mentor login(long id)
    {
        Mentor mentor = mentorService.getMentor(id);
        return mentor;
    }

    @PostMapping(path="/registration")
    public ResponseEntity<Boolean> register(Mentor mentor)
    {
        return new ResponseEntity<>(mentorService.registerMentor(mentor), HttpStatus.OK);
    }

    @GetMapping(path = "/all-interns")
    public ResponseEntity<List<Intern>> getInterns()
    {
        List<Intern> list = internService.getInterns();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = "/skills-offered")
    public ResponseEntity<List<LearningSkill>> getAllSkills()
    {
        return new ResponseEntity<>(mentorService.skills(), HttpStatus.OK);
    }

    @PostMapping(path = "/add-skill")
    public ResponseEntity<Boolean> createProgramme(LearningSkill newskill)
    {
        mentorService.createSkill(newskill);
        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }

    @PutMapping(path="/update-skill")
    public ResponseEntity<LearningSkill> updateSkill(LearningSkill skill)
    {
        LearningSkill updated = mentorService.updateSkill(skill);
        return  new ResponseEntity<LearningSkill>(updated, HttpStatus.OK);
    }

    @PutMapping(path = "/edit-intern")
    public ResponseEntity<Intern> updateIntern(Intern intern)
    {
        Intern intern_ = internService.updateIntern(intern);
        return new ResponseEntity<Intern>(intern_, HttpStatus.OK);
    }

    @GetMapping(path="/mentors")
    public ResponseEntity<List<Mentor>> getMentors()
    {
        return new ResponseEntity<>(mentorService.getMentors(), HttpStatus.OK);
    }

    @DeleteMapping(path = "/remove-skill")
    public Boolean removeSkill()
    {

        return false;
    }

    //Routes for Training Skills Management


}
