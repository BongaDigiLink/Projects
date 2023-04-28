package Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.InternService;

@RestController
@RequestMapping(path="/ums/interns/")
public class InternController
{
    private final InternService internService;

    @Autowired
    private InternController(InternService internService)
    {
        this.internService = internService;
    }
}
