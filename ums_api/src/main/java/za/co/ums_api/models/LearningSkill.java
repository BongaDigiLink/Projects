package za.co.ums_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Skills")
public class LearningSkill
{
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Integer programmeDuration;

    //Number of enrolled students/trainees/interns
    private static Integer count = 0;

    public LearningSkill(String name, String description, Integer programmeDuration)
    {
        this.name = name;
        this.description = description;
        this.programmeDuration = programmeDuration;
        count +=1;
    }

    public LearningSkill(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public LearningSkill()
    {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getProgrammeDuration() {
        return programmeDuration;
    }

    public void setProgrammeDuration(Integer programmeDuration) {
        this.programmeDuration = programmeDuration;
    }

}
