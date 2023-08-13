package za.co.CrudApp.CrudApplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import za.co.CrudApp.CrudApplication.model.Contact;

public interface UserRepository extends JpaRepository<Contact, Integer>
{
    Contact findByEmail(String email);
    Contact deleteByEmail(String email);
}