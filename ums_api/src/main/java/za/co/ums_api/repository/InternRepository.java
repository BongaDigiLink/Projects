package za.co.ums_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.ums_api.models.Intern;

public interface InternRepository extends JpaRepository<Intern, Long> {
}
