package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ums_api.models.Intern;
import za.co.ums_api.service.InternService;

@RestController
@RequestMapping(path = "/intern")
public class InternController
{
    private final InternService internService;

    @Autowired
    public InternController(InternService internService)
    {
        this.internService = internService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<Boolean> registerIntern(Intern intern)
    {
        Boolean bool = internService.registerIntern(intern);
        return new ResponseEntity(bool, HttpStatus.OK);
    }
}
