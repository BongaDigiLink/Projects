package Controllers;

import models.interns.Intern;
import org.springframework.beans.factory.annotation.Autowired;
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

    // -------- Get Paths ---
    @GetMapping("/all-interns/")
    List<Intern> getAllInterns()
    {
        return internService.getAllInterns();
    }

    @GetMapping(path = "{internId}")
    public Intern getIntern(@PathVariable("internId") Integer id)
    {
        return internService.getInternById(id);
    }

    // -------- Post Path ---
    @PostMapping
    public void registerIntern(Intern intern)
    {
        internService.registerIntern(intern);
    }
}
