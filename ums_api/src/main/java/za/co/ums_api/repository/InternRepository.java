package za.co.ums_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ums_api.models.Intern;

import java.util.Optional;

public interface InternRepository extends JpaRepository<Intern, Long> {

    Intern findByEmail(String email);

    Boolean existsByEmail(String email);
}
