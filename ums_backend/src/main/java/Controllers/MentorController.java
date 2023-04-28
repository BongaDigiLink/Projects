package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.InternService;
import services.MentorService;

@RestController
@RequestMapping(path="/ums/mentors/")
public class MentorController
{
    private final MentorService mentorService;

    @Autowired
    private MentorController(MentorService mentorService)
    {
        this.mentorService = mentorService;
    }

    @GetMapping
    public static void getMentors()
    {

    }
}
