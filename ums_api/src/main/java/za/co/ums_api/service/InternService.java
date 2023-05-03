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
        Intern user = internRepository.findByUsername(intern.getUsername());
        if(user != null)
        {
            System.out.println("User already exists.");
            return false;

        }else
        {
            System.out.println("Debug Inside "+intern.getName()+" Username: "+intern.getUsername());
            internRepository.save(intern);
            return true;
        }
    }

    public List<Intern> getInterns()
    {
        return internRepository.findAll();
    }

}
