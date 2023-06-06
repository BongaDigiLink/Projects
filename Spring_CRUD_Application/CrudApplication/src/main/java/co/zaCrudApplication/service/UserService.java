package co.zaCrudApplication.service;

import co.zaCrudApplication.model.Contact;
import co.zaCrudApplication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;
    //private PasswordEncoder passwordEncoder;

    public boolean createUser(Contact newContact)
    {

        if(!(this.userRepository.findByEmail(newContact.getEmail()) == null))
        {
            return false;
        }
        this.userRepository.save(new Contact(newContact.getName(), newContact.getSurname(), newContact.getEmail(), newContact.getPassword()));
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

    public List<Contact> getUsers()
    {
        return this.userRepository.findAll();
    }
}