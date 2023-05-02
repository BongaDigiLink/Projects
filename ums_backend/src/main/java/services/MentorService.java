package services;

import models.mentors.Mentor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import repository.MentorRepository;

import java.util.List;

@Service
public class MentorService
{
    private final MentorRepository mentorRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository)
    {
        this.mentorRepository = mentorRepository;
    }

    public List<Mentor> getMentors()
    {
        return mentorRepository.findAll();
    }

    public Mentor getMentor(Integer id)
    {
        Mentor mentor = mentorRepository.getMentorById(id);
        return mentor;
    }
}
