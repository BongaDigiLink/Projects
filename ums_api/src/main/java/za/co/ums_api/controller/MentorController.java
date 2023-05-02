package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ums_api.models.Intern;
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

    @GetMapping(path = "/all-interns")
    public ResponseEntity<List<Intern>> getInterns()
    {
        List<Intern> list = internService.getInterns();
        return new ResponseEntity<List<Intern>>(list,HttpStatus.OK);
    }

    @GetMapping(path="/mentors")
    public ResponseEntity<List<Mentor>> getMentors()
    {
        return new ResponseEntity<>(mentorService.getMentors(), HttpStatus.OK);
    }
}
