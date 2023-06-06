package za.co.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.co.model.User;
import za.co.repository.UserRepository;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public boolean createUser(User newUser)
    {
        if(this.userRepository.findByEmail(newUser.getEmail()))
        {
            return false;
        }
        this.userRepository.save(new User(newUser.getName(),newUser.getSurname(), newUser.getEmail(), newUser.getPassword()));
        return true;
    }

    public List<User> getUsers()
    {
        return this.userRepository.findAll();
    }
}
