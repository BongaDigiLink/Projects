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
        if(!(this.userRepository.findByEmail(email) == null))
        {
            return false;
        }
        //Do the update
        Contact toUpdateContact = this.userRepository.findByEmail(contact.getEmail());
        toUpdateContact.setName(contact.getEmail());
        toUpdateContact.setSurname(contact.getSurname());
        //First check before changing - each contact unique email address.
        if(toUpdateContact.getEmail().equals(contact.getEmail()))
        {
            //ignore or paste same password again.
            toUpdateContact.setEmail(contact.getEmail());
        }
        else
        {
            if( this.userRepository.findByEmail(contact.getEmail()) == null)
            {
                toUpdateContact.setEmail(contact.getEmail());
            }
            return false;
        }

        this.userRepository.save(toUpdateContact);

        return true;
    }

    public List<Contact> getUsers()
    {
        return this.userRepository.findAll();
    }
}