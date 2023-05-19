package za.co.ums_api.models;

import jakarta.persistence.*;
import org.hibernate.Length;

import java.util.Date;

@Entity
@Table(name="Skills")
public class LearningSkill
{

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Column(length=1255)
    private String description;
    private Integer programmeDuration;
    private Date dueDate;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public void setFieldTraining(String fieldTraining) {
        this.fieldTraining = fieldTraining;
    }

    private String fieldTraining;

    //Number of enrolled students/trainees/interns
    private static Integer count = 0;

    public LearningSkill(String name, String fieldTraining, Date date,String description, String url)
    {
        this.name = name;
        this.fieldTraining = fieldTraining;
        this.description = description;
        this.dueDate = date;
        count +=1;
        this.imgUrl = url;
    }

    public LearningSkill(String name, String description)
    {
        this.name = name;
        this.description = description;
    }

    public LearningSkill()
    {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
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

    public String getFieldTraining() {
        return this.fieldTraining;
    }

    public Date getDueDate() {
        return this.dueDate;
    }
}
