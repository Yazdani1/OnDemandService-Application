package yazdaniscodelab.ondemandfinalproject.Model;

/**
 * Created by Yazdani on 11/22/2018.
 */

public class Services {

    private String title;
    private String budget;
    private String skill;
    private String phone;
    private String description;
    private String date;
    private String id;

    public Services(){

    }

    public Services(String title, String budget, String skill, String phone, String description, String date, String id) {
        this.title = title;
        this.budget = budget;
        this.skill = skill;
        this.phone = phone;
        this.description = description;
        this.date = date;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
