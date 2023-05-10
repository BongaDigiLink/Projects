package za.co.ums_api.service;

import org.springframework.stereotype.Service;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.repository.LearningSkillRepository;

import java.util.List;

@Service
public class InternService
{

    private final InternRepository internRepository;
    private final LearningSkillRepository learningSkillRepository;

    public InternService(InternRepository internRepository, LearningSkillRepository learningSkillRepository)
    {
        this.internRepository = internRepository;
        this.learningSkillRepository = learningSkillRepository;
    }

    //------------------------------------Intern auth (login/reg)
    public boolean registerIntern(Intern intern)
    {
        Intern user = internRepository.findByEmail(intern.getEmail());
        if(user != null)
        {
            System.out.println("User already exists. "+intern.getEmail()+" User name: "+intern.getName());
            return false;

        }else
        {
            System.out.println("User name "+intern.getName()
                    +" User email: "+intern.getEmail()
                    +" Password: "+intern.getPassword()+
                    " Surname: "+intern.getSurname());
            internRepository.save(intern);
            return true;
        }
    }

    public Intern login(Intern intern)
    {
        Intern foundUser = this.internRepository.findByEmail(intern.getEmail());

        if(foundUser != null)
        {
            return  foundUser;
        }
        else
        {
            return null;
        }
    }

    //------------------------------------Edit, PUT

    public Intern updateIntern(Intern intern)
    {
        Intern user = this.internRepository.findByEmail(intern.getEmail());

        user.setName(intern.getName());
        user.setSurname(intern.getSurname());
        user.setTrainingField(intern.getTrainingField());

        if (user.getEmail().equals(intern.getEmail()))
        {
            //email not changed. Do nothing.
        } else
        {
            intern.setEmail(" ");//null this email first.
            Intern o = this.internRepository.findByEmail(intern.getEmail());
            if (o == null)
            {
                //Change email
                user.setEmail(intern.getEmail());
            } else
            {
                return user;
            }
        }
        return user;
    }

    public void deactivateIntern(Intern user)
    {

    }

    //------------------------------------Get routes

    public List<LearningSkill> getAllSkills()
    {
        return this.learningSkillRepository.findAll();
    }

    public List<LearningSkill> getTasks(List<LearningSkill> all, String name)
    {
        List<LearningSkill> myTasks = null;
        for(LearningSkill task: all)
        {
            if(task.getName().equals(name))
            {
                myTasks.add(task);
            }
        }
        return myTasks;
    }
}
