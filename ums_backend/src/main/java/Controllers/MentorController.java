package Controllers;

import models.interns.Intern;
import models.mentors.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.InternService;
import services.MentorService;

import java.util.List;

@RestController
@RequestMapping(path="/ums")
public class MentorController
{
    private final MentorService mentorService;
    private final InternService internService;

    @Autowired
    private MentorController(MentorService mentorService, InternService internService)
    {
        this.mentorService = mentorService;
        this.internService = internService;
    }

    // ---------------------------------------------- Admin/Mentor Routes --------------------------------
    @DeleteMapping("/deregister-intern/{id}")
    public ResponseEntity<Intern> deregisterIntern(@PathVariable("id") Integer id)
    {
        Intern deletedIntern = internService.deleteInternById(id);
        return new ResponseEntity<>(deletedIntern, HttpStatus.OK);
    }

    @PutMapping("/admin/update-intern")
    public ResponseEntity<Intern> adminUpdateIntern(@RequestBody Intern intern)
    {
        Intern intern1 = internService.updateIntern(intern);

        return new ResponseEntity<>(intern1, HttpStatus.OK);
    }

    @GetMapping("/all-interns")
    ResponseEntity<List<Intern>> getAllInterns()
    {
        List<Intern> interns = internService.getAllInterns();
        return new ResponseEntity<>(interns, HttpStatus.OK);
    }

    @GetMapping("/all-colleagues")
    public ResponseEntity<List<Mentor>> getMentors()
    {
        List<Mentor> mentorsList = mentorService.getMentors();
        return new ResponseEntity<>(mentorsList, HttpStatus.OK);
    }

    @GetMapping("/view-mentor/{id}")
    public ResponseEntity<Mentor> viewMentor(@PathVariable("id") Integer id)
    {
        Mentor mentor = mentorService.getMentor(id);
        return new ResponseEntity<>(mentor, HttpStatus.OK);
    }

    @GetMapping("/view-intern/{id}")
    public ResponseEntity<Intern> viewIntern(@PathVariable("id") Integer id)
    {
        Intern intern = internService.getInternById(id);
        return new ResponseEntity<>(intern, HttpStatus.OK);
    }
}
