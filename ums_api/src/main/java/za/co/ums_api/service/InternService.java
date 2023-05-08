package za.co.ums_api.service;

import org.springframework.stereotype.Service;
import za.co.ums_api.models.Intern;
import za.co.ums_api.repository.InternRepository;

import java.util.List;

@Service
public class InternService {

    private final InternRepository internRepository;

    public InternService(InternRepository internRepository) {
        this.internRepository = internRepository;
    }

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

    public List<Intern> getInterns()
    {
        return internRepository.findAll();
    }

    public Intern updateIntern(Intern intern)
    {
        Intern intern_edit = internRepository.findByEmail(intern.getEmail());

        if(intern_edit == null)
        {
            return intern;
        }
        else
        {
            return intern_edit;
        }
    }
}
