package services;

import models.interns.Intern;
import org.springframework.beans.factory.annotation.Autowired;
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

    // ---- Post Paths ----
    public void registerIntern(Intern intern)
    {
        internRepository.save(intern);
    }
}
