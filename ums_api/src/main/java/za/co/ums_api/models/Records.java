package za.co.ums_api.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;

@Entity
@Table(name="Records")
public class Records
{
    @Id
    @GeneratedValue
    private Integer recordID;

    public String getEmail()
    {
        return email;
    }

    private String email;
    private String dateCompleted;
    private String userName;
    private String taskName;
    private String taskTrainingField;

    //Constructors

    public Records(String userName, String taskName, String taskTrainingField)
    {
        this.userName = userName;
        this.taskName = taskName;
        this.taskTrainingField = taskTrainingField;
        this.dateCompleted = "Submitted "+LocalDateTime.now();
    }

    public Records() {

    }


    //Getter and Setters.

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTrainingField() {
        return taskTrainingField;
    }

    public void setTaskTrainingField(String taskTrainingField) {
        this.taskTrainingField = taskTrainingField;
    }
}
