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

    @GeneratedValue
    private Integer id;
    private String email;
    private String name;
    private String surname;
    private String role;

    private String activeStatus;
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
        this.role ="Intern";
        this.activeStatus = "ACTIVE";
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    //For verification. Only Store Hashed String and Return Hashed string
    public String getPassword() {
        return this.password;
    }

    //For changing password. Hashed string only.
    public void setPassword(String password) {
        this.password = password;
    }

    public String getTrainingField() {
        return this.trainingField;
    }

    public void setTrainingField(String trainingField) {
        this.trainingField = trainingField;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public String getRole() {
        return this.role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return this.email;
    }

    public String getActiveStatus() {
        return this.activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }


    public String toString()
    {
        return " "+this.email+
                " "+this.name+
                " "+this.surname+
                " "+this.role+
                " "+this.password+
                " "+this.activeStatus;
    }

}
