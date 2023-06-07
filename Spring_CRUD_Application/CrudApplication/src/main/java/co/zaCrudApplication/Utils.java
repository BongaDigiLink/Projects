package co.zaCrudApplication;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Utils
{
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String hashPassword(String password)
    {
        System.out.println("Inside the hash method:");
        String hashedPassword;
        hashedPassword = passwordEncoder.encode(password);
        return  hashedPassword;
    }

    public boolean checkPasswordMatch(String password, String existingPassword)
    {
        if(passwordEncoder.matches(password, existingPassword))
        {
            return true;
        }
        return false;
    }
}
