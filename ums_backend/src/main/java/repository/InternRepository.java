package repository;

import models.interns.Intern;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InternRepository extends JpaRepository<Intern, Integer>
{
    @Override
    Optional<Intern> findById(Integer integer);

    Intern getById(Integer id);

    void deleteInternByIdById(Integer id);
}
