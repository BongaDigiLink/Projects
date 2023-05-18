package za.co.ums_api.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.repository.LearningSkillRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<Intern> getInterns()
    {
        List<Intern> list = internRepository.findAll();
        List<Intern> activeUsers = new ArrayList<>();

        for(Intern user: list)
        {
            if(user.getActiveStatus().equals(true))
            {
                activeUsers.add(user);
            }
        }

        return  activeUsers;
    }

    public List<Intern> getInactiveInterns()
    {
        List<Intern> list = internRepository.findAll();
        List<Intern> inactiveUsers = new ArrayList<>();

        if(list.isEmpty())
        {
            return null;
        }

        for(Intern user: list)
        {
            if(user.getActiveStatus().equals(false))
            {
                inactiveUsers.add(user);
            }
        }

        return  inactiveUsers;
    }

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

    public Intern getInternById(Integer id)
    {
        Optional<Intern> user = this.internRepository.findById(id);

        if(user.isPresent())
        {
            return user.get();
        }

        return null;
    }

    public boolean checkUser(String email)
    {
        return this.internRepository.existsByEmail(email);
    }

    public Intern getUserByEmail(String email)
    {
        return this.internRepository.findByEmail(email);
    }

    public Boolean createNewUser(Intern intern)
    {
        if(checkUser(intern.getEmail()))
        {
            return false;
        }
        else
        {
            try
            {
                this.internRepository.save(
                        new Intern(intern.getEmail(),
                                intern.getName(),
                                intern.getSurname(),
                                intern.getTrainingField(),
                                intern.getPassword()));

                return true;
            }
            catch (Exception e)
            {
                System.out.println("Error creating user. "+e.getMessage());
                return false;
            }
        }
    }
}
