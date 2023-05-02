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

    public List<Intern> getInterns()
    {
        return internRepository.findAll();
    }

}
