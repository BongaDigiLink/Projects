package za.co.ums_api.service;

import org.springframework.stereotype.Service;
import za.co.ums_api.models.Mentor;
import za.co.ums_api.repository.MentorRepository;

import java.util.List;

@Service
public class MentorService {
    private final MentorRepository mentorRepository;


    public MentorService(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    public Mentor getMentor(Long id)
    {
        return mentorRepository.getMentor(id);
    }

    public List<Mentor> getMentors()
    {
        return mentorRepository.findAll();
    }

    public List<String> skills()
    {
         return "";
    }
}
