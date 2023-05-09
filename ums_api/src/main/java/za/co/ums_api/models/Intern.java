package za.co.ums_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import javax.persistence.*;

@Entity
@Table(name="Interns")
public class Intern
{
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String surname;

    private String role;

    private String trainingField;
    private String password;

    //No args Constructor
    public Intern()
    {
    }

    //Default Registration Constructor
    public Intern(String email, String name, String surname, String trainingField, String password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.trainingField = trainingField;
        this.role ="intern";
    }

    //For verification. Only Store Hashed String and Return Hashed string
    public String getPassword() {
        return password;
    }

    //For changing password. Hashed string only.
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrainingField() {
        return trainingField;
    }

    public void setTrainingField(String trainingField) {
        this.trainingField = trainingField;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }


    public String toString()
    {
        return " "+this.email+
                " "+this.name+
                " "+this.surname+
                " "+this.role+
                " "+this.password;
    }

}
