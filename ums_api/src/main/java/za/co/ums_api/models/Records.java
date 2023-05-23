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

    private String email;
    private String dateCompleted;
    private String taskName;
    private String taskTraining;

    //Constructors

    public Records(String userName, String taskName, String taskTrainingField)
    {
        this.email = userName;
        this.taskName = taskName;
        this.taskTraining = taskTrainingField;
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
    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTraining() {
        return taskTraining;
    }

    public void setTaskTraining(String taskTrainingField) {
        this.taskTraining = taskTrainingField;
    }
}
