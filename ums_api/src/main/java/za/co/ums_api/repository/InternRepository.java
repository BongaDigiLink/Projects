package za.co.ums_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ums_api.models.Intern;

import java.util.Optional;

public interface InternRepository extends JpaRepository<Intern, Long> {

    Intern findByEmail(String email);

    Boolean existsByEmail(String email);

    boolean existsById(Integer id);

    Optional<Intern> findById(Integer id);

    Intern getInternById(Integer integer);
    void deleteById(Integer id);
}
