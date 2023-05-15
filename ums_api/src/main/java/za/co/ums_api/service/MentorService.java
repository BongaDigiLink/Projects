package za.co.ums_api.service;

import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@Transactional
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


    public Boolean deleteIntern(Integer id)
    {
       Boolean deletedDefault = false;

       if(this.internRepository.existsById(id))
       {
           internRepository.deleteById(id);
           return true;
       }
       else
       {
           return deletedDefault;
       }
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

    public Mentor getUserByEmail(String email)
    {
        if(this.mentorRepository.existsByEmail(email))
        {
            return mentorRepository.findByEmail(email);
        }

        return null;
    }

    public List<Mentor> getMentors()
    {
        return mentorRepository.findAll();
    }

    //--------------------------------------Skills Management Functions

    public Boolean createSkill(LearningSkill task)
    {
        learningSkillRepository.save(new LearningSkill(task.getName(), task.getDescription()));
        return true;
    }

    public LearningSkill createTask(LearningSkill task)
    {
        LearningSkill createTask = this.learningSkillRepository.save(
                new LearningSkill(task.getName(),
                        task.getFieldTraining(),
                        task.getDueDate(),
                        task.getDescription())
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


    public boolean checkUser(Integer id)
    {
        return this.internRepository.existsById(id);
    }

    /**
     *
     * @param id of intern to update.
     * @param intern data from the client
     * @return the changed object.
     */
    public Intern updateIntern(Integer id, Intern intern)
    {
        Optional<Intern> user = this.internRepository.findById(id);

        Intern update = user.get();

        update.setName(intern.getName());
        update.setSurname(intern.getSurname());
        update.setEmail(intern.getEmail());
        update.setActiveStatus(intern.getActiveStatus());
        update.setTrainingField(intern.getTrainingField());

        return this.internRepository.save(update);

    }

    public boolean checkMentor(String email)
    {
        return this.mentorRepository.existsByEmail(email);
    }
}
