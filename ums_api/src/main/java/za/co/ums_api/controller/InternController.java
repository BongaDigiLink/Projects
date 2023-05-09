package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ums_api.models.Intern;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.service.InternService;

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
}
