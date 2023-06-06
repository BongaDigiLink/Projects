package co.zaCrudApplication.repository;

import co.zaCrudApplication.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Contact, Integer>
{
    Contact findByEmail(String email);
}