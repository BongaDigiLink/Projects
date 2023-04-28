package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.MentorRepository;

@Component
public class MentorService
{
    private final MentorRepository mentorRepository;

    @Autowired
    public MentorService(MentorRepository mentorRepository)
    {
        this.mentorRepository = mentorRepository;
    }
}
