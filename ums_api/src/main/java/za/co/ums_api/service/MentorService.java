package za.co.ums_api.service;

import jakarta.transaction.Transactional;
import org.apache.catalina.Store;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.LearningSkill;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.models.Records;
import za.co.ums_api.repository.InternRepository;
import za.co.ums_api.repository.LearningSkillRepository;
import za.co.ums_api.repository.MentorRepository;
import za.co.ums_api.repository.RecordRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MentorService {
    private final MentorRepository mentorRepository;
    private final LearningSkillRepository learningSkillRepository;
    private final InternRepository internRepository;
    private final RecordRepository recordsRepo;

    public MentorService(MentorRepository mentorRepository,
                         LearningSkillRepository learningSkillRepository,
                         InternRepository internRepository,
                         RecordRepository recordsRepo)
    {
        this.mentorRepository = mentorRepository;
        this.learningSkillRepository = learningSkillRepository;
        this.internRepository = internRepository;
        this.recordsRepo = recordsRepo;
    }

    //------------------------------------Intern Auth Management Functions

    public Boolean registerMentor(Mentor mentor)
    {
        Mentor user = mentorRepository.findByEmail(mentor.getEmail());
        if (user != null) {
            System.out.println("User already exists.");
            return false;

        } else {
            //System.out.println("Debug Inside "+mentor.getName()+" Username: "+mentor.getUsername());
            mentorRepository.save(mentor);
            return true;
        }
    }

    //------------------------------------Intern Management Functions

    public Mentor getMentor(Integer id) {
        return mentorRepository.getMentor(id);
    }


    public Boolean deleteIntern(Integer id)
    {
        Boolean deletedDefault = false;

        if (this.internRepository.existsById(id)) {
            internRepository.deleteById(id);
            return true;
        } else {
            return deletedDefault;
        }
    }

    public Intern getUserById(Integer id)
    {
        Optional<Intern> user = this.internRepository.findById(id);

        if (user.isPresent()) {
            return user.get();
        }

        return null;
    }

    public Mentor getUserByEmail(String email)
    {
        if (this.mentorRepository.existsByEmail(email)) {
            return mentorRepository.findByEmail(email);
        }

        return null;
    }

    public List<Mentor> getMentors() {
        return mentorRepository.findAll();
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
        System.out.print("Intern Status :"+intern.getActiveStatus());
        update.setTrainingField(intern.getTrainingField());

        return this.internRepository.save(update);
    }

    public Intern deactivateIntern(Integer id)
    {
        Optional<Intern> user = this.internRepository.findById(id);

        Intern user_ = user.get();
        user_.setActiveStatus("INACTIVE");

        return  this.internRepository.save(user_);
    }

    public boolean checkMentor(String email)
    {
        return this.mentorRepository.existsByEmail(email);
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
                        task.getDescription(),
                        setImg(task))
        );

        return createTask;
    }

    private String setImg(LearningSkill task)
    {
        //System.out.println("String img url: "+task.getFieldTraining().toString());
        if(task.getFieldTraining().toString().equals("Data Analytics"))
        {
            return "https://images.unsplash.com/photo-1608222351212-18fe0ec7b13b?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1074&q=80";
        }
        else if (task.getFieldTraining().toString().equals("Full Stack Software Developer"))
        {
            return "https://images.unsplash.com/photo-1571171637578-41bc2dd41cd2?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1170&q=80";
        }
        else if(task.getFieldTraining().toString().equals("Software Tester"))
        {
            return "https://images.unsplash.com/photo-1573496004846-eb89fae542b1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1169&q=80";
        }else {
            return null;
        }
    }

    public LearningSkill updateSkill(LearningSkill skill)
    {
        LearningSkill edit = learningSkillRepository.findByName(skill.getName());
        if (edit != null) {
            edit = skill;
            return edit;
        } else {
            return null;
        }
    }

    public List<LearningSkill> skills() {
        return learningSkillRepository.findAll();
    }

    public Boolean tasksExist()
    {
        if(skills().isEmpty())
        {
            return false;
        }
        return true;
    }

    public Boolean taskExist(Integer id)
    {
        if(this.learningSkillRepository.existsById(id))
        {
            return true;
        }
        return false;
    }

    public LearningSkill getTask(Integer id)
    {
        Optional<LearningSkill> task =  this.learningSkillRepository.findById(id);
        LearningSkill tasks_ = task.get();

        return  tasks_;
    }

    public Boolean createRecord(Records record)
    {
        try
        {
            Intern user = this.internRepository.findByEmail(record.getUserName());

            this.recordsRepo.save(new Records(
                    user.getName()+" "+user.getSurname(),
                    record.getTaskName(),
                    record.getTaskTrainingField()
            ));

            return true;
        }
        catch (Exception e)
        {
            e.getMessage();
            return false;
        }
    }

    public List<Records> getUserRecords(Integer id)
    {
        if(!this.recordsRepo.findAll().isEmpty())
        {
            List<Records> list = this.recordsRepo.findAll();

            for (Records record: list)
            {

            }
        }
        return null;
    }


}
