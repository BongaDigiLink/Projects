package services;

import models.interns.Intern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import repository.InternRepository;

import java.util.List;

@Component
public class InternService
{
    private final InternRepository internRepository;

    @Autowired
    public InternService(InternRepository internRepository)
    {
        this.internRepository = internRepository;
    }

    // ---- Get Paths ----
    public List<Intern> getAllInterns()
    {
        return internRepository.findAll();
    }

    public Intern getInternById(Integer internId)
    {
        return internRepository.getById(internId);
    }

    public Intern deleteInternById(Integer id)
    {
        Intern deleteUser = internRepository.getById(id);
        internRepository.deleteInternByIdById(deleteUser.getId());

        return  deleteUser;
    }

    // ---- Post Paths ----
    public void registerIntern(Intern intern)
    {
        internRepository.save(intern);
    }

    public Intern updateIntern(Intern intern)
    {
        Intern intern1 = internRepository.save(intern);
        return intern1;
    }
}
