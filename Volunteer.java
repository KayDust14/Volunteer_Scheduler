package volunteer_scheduler_project;


import java.util.List;
/*
 * Creates the volunteer class in the program
 * Information needed on each volunteer:
 * Name
 * Age
 * Skill (beer garden, ball runner, or backup for either if none given)
 * Availability (morning or afternoon and day)
 * Priority (basing based on age and skill)
 * */

public class Volunteer {
    private String name;
    private int age;
    private String skill;
    private String availability; // Morning or Afternoon
    private List<String> daysAvailable;

    public Volunteer(String name, int age, String skill, String availability, List<String> daysAvailable) {
        this.name = name;
        this.age = age;
        this.skill = skill;
        this.availability = availability;
        this.daysAvailable = daysAvailable;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSkill() {
        return skill;
    }

    public String getAvailability() {
        return availability;
    }

    public List<String> getDaysAvailable() {
        return daysAvailable;
    }
}
/*
 * Sprint 1: Set up basic Volunteer class with attributes for name, age, skill, availability, and available days.
 * Sprint 2: Added getter and setter methods to access and modify volunteer attributes.
 * Sprint 3: Implemented toString method for easy display of volunteer information.
 * Sprint 4: Testing and making it look more user-friendly in output - the emojis are my favorite part.
 */