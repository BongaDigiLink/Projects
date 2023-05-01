package Controllers;

import models.interns.Intern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.InternService;

import java.util.List;

@RestController
@RequestMapping(path="/ums/interns")
public class InternController
{
    private final InternService internService;

    @Autowired
    private InternController(InternService internService)
    {
        this.internService = internService;
    }

    // -------------------------------------------- Intern/User Routes ---------------------------------
    @GetMapping(path = "{internId}")
    public ResponseEntity<Intern> getIntern(@PathVariable("internId") Integer id)
    {
        Intern user = internService.getInternById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public void registerIntern(Intern intern)
    {
        internService.registerIntern(intern);
    }

    @PutMapping("/update-user")
    public ResponseEntity<Intern> updateIntern(@RequestBody Intern intern)
    {
        Intern intern1 = internService.updateIntern(intern);

        return new ResponseEntity<>(intern1, HttpStatus.OK);
    }

}
