package za.co.ums_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.repository.LearningSkillRepository;
import za.co.ums_api.repository.MentorRepository;

import java.util.List;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;
    private final LearningSkillRepository learningSkillRepository;

    public Boolean registerMentor(Mentor mentor)
    {
        Mentor user = mentorRepository.findByEmail(mentor.getEmail());
        if(user != null)
        {
            System.out.println("User already exists.");
            return false;

        }else
        {
            //System.out.println("Debug Inside "+mentor.getName()+" Username: "+mentor.getUsername());
            mentorRepository.save(mentor);
            return true;
        }
    }


    public MentorService(MentorRepository mentorRepository, LearningSkillRepository learningSkillRepository)
    {
        this.mentorRepository = mentorRepository;
        this.learningSkillRepository = learningSkillRepository;
    }

    public Mentor getMentor(Long id)
    {
        return mentorRepository.getMentor(id);
    }

    public List<Mentor> getMentors()
    {
        return mentorRepository.findAll();
    }

    //Service calls for Training Skills Management

    public Boolean createSkill(LearningSkill learningSkill)
    {
        LearningSkill check = learningSkillRepository.findByName(learningSkill.getName());

        if(check != null)
        {
            return false;
        }
        else
        {
            learningSkillRepository.save(learningSkill);
            return true;
        }
    }

    public LearningSkill updateSkill(LearningSkill skill)
    {
        LearningSkill edit = learningSkillRepository.findByName(skill.getName());
         if(edit !=null)
         {
             edit = skill;
             return edit;
         }
         else
         {
             return null;
         }
    }

    public List<LearningSkill> skills()
    {
         return learningSkillRepository.findAll();
    }
}
