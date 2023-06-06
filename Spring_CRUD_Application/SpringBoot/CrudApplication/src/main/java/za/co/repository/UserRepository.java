package za.co.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.model.User;

public interface UserRepository extends JpaRepository<User, Integer>
{
    boolean findByEmail(String email);
}
