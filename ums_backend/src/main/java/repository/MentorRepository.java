package repository;

import models.mentors.Mentor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MentorRepository extends JpaRepository<Mentor, Integer>
{
    public Mentor getMentorById(Integer id);
}
