package za.co.ums_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ums_api.models.Intern;
import za.co.ums_api.models.Mentor;

import java.util.List;
import java.util.Optional;

public interface MentorRepository extends JpaRepository<Mentor, Long>
{
    //private final MentorRepository mento
    public default Mentor getMentor(Long id)
    {
        Optional<Mentor> mentor = findById(id);

        return new Mentor();
    }

    Mentor findByEmail(String email);
}
