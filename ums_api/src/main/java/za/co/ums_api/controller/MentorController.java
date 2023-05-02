package za.co.ums_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.service.InternService;
import za.co.ums_api.service.MentorService;

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

    public Mentor login(long id){
        Mentor mentor = mentorService.getMentor(id);
        return mentor;
    }
}
