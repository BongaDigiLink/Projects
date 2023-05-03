package za.co.ums_api.models;

public class LearningSkill
{
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
