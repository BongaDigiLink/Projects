package models.interns;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "interns")
public class Intern
{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    private String gender;
    private String fieldOfWork;
    private Integer cellphoneNumber;

    public Integer getId() {
        return id;
    }
}
