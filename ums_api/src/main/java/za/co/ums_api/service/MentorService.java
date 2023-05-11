package za.co.ums_api.service;

import org.springframework.stereotype.Service;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.repository.LearningSkillRepository;
import za.co.ums_api.repository.MentorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;
    private final LearningSkillRepository learningSkillRepository;
    private final InternRepository internRepository;

    public MentorService(MentorRepository mentorRepository, LearningSkillRepository learningSkillRepository, InternRepository internRepository)
    {
        this.mentorRepository = mentorRepository;
        this.learningSkillRepository = learningSkillRepository;
        this.internRepository = internRepository;
    }

    //------------------------------------Intern Auth Management Functions

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

    //------------------------------------Intern Management Functions

    public Mentor getMentor(Integer id)
    {
        return mentorRepository.getMentor(id);
    }


    public Optional<Intern> deleteIntern(Integer id)
    {
        Optional<Intern> remove = this.internRepository.findById(id);

        if(remove != null)
        {

        }
        else {
            internRepository.deleteById(id);
            return remove;
        }
        return remove;
    }

    public Optional<Intern> deactivateIntern(Integer id)
    {
        Optional<Intern> deactivated = this.internRepository.findById(id);
        deactivated.get().setRole("offline");

        return  deactivated;
    }

    public Intern getUserById(Integer id) {
        Optional<Intern> user = this.internRepository.findById(id);

        if(user.isPresent())
        {
            return user.get();
        }

        return null;
    }

    public Intern getUserByEmail(String email)
    {
        if(this.internRepository.existsByEmail(email))
        {
            return internRepository.findByEmail(email);
        }

        return null;
    }

    public List<Mentor> getMentors()
    {
        return mentorRepository.findAll();
    }

    //--------------------------------------Skills Management Functions

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

    public LearningSkill createTask(LearningSkill task)
    {
        LearningSkill createTask = this.learningSkillRepository.save(
                new LearningSkill(task.getName(), task.getDescription())
        );

        return createTask;
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
