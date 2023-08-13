package za.co.CrudApp.CrudApplication.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Utils
{
    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    //Hashing user password.
    public static String hashPassword(String password)
    {
        String hashedPassword;
        hashedPassword = passwordEncoder.encode(password);
        return  hashedPassword;
    }

    /**
     *          Method used for login password verification.
     * @param password - input password from user
     * @param existingPassword - account stored in the datasource
     * @return - boolean, true for password match. false for incorrect password.
     */
    public static boolean checkPasswordMatch(String password, String existingPassword)
    {
        if(passwordEncoder.matches(password, existingPassword))
        {
            return true;
        }
        return false;
    }
}