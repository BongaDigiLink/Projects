package za.co.ums_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Mentor")
public class Mentor
{
    @Id
    @GeneratedValue
    private Long id;
    private String email;
    private String name;
    private String surname;
    private Integer phone;
    private String about;
    private String trainingField;
    private String password;

    public String getEmail() {
        return email;
    }

    //No args Constructor
    public Mentor()
    {
    }

    //Default Registration Constructor
    public Mentor(String email, String name, String surname, Integer phone, String password) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
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

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
