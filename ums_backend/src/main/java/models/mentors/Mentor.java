package models.mentors;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name="Mentors" )
public class Mentor
{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String surname;
    private String gender;
    private String fieldOfWork;
    private Integer cellphoneNumber;
}
