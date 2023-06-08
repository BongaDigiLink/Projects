package za.co.CrudApp.CrudApplication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import za.co.CrudApp.CrudApplication.util.Utils;
import za.co.CrudApp.CrudApplication.model.Contact;
import za.co.CrudApp.CrudApplication.repository.UserRepository;

import java.util.List;

@Service
public class UserService implements UserDetailsService
{
    private UserRepository userRepository;
    private Utils utils = new Utils();
    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public boolean createUser(Contact newContact)
    {
        if(!(this.userRepository.findByEmail(newContact.getEmail()) == null))
        {
            return false;
        }
        String hashedPassword = utils.hashPassword(newContact.getPassword());

        this.userRepository.save(new Contact(newContact.getName(), newContact.getSurname(), newContact.getEmail(), hashedPassword));
        return true;
    }

    public boolean updateContact(String email, Contact contact)
    {
        if( this.userRepository.findByEmail(contact.getEmail()) == null )
        {
            //Do the update
            Contact toUpdateContact = this.userRepository.findByEmail(email);
            if(toUpdateContact == null)
            {
                return false;
            }
            toUpdateContact.setName(contact.getName());
            toUpdateContact.setSurname(contact.getSurname());
            //First check before changing - each contact unique email address.
            toUpdateContact.setEmail(contact.getEmail());

            this.userRepository.save(toUpdateContact);
            return true;
        }
        return false;
    }

    public boolean deleteUser(String email)
    {
        if(this.userRepository.findByEmail(email) != null)
        {
            Contact _del = this.userRepository.findByEmail(email);
            this.userRepository.deleteById(_del.getUser_id());
            return true;
        }
        return false;
    }

    public List<Contact> getUsers()
    {
        return this.userRepository.findAll();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}