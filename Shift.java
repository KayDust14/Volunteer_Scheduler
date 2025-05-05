package volunteer_scheduler_project;


import java.util.ArrayList;
import java.util.List;

public class Shift {
    private String day;
    private String timeOfDay; // Morning or Afternoon
    private List<Volunteer> volunteers;

    public Shift(String day, String timeOfDay) {
        this.day = day;
        this.timeOfDay = timeOfDay;
        this.volunteers = new ArrayList<>();
    }

    public String getDay() {
        return day;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void addVolunteer(Volunteer volunteer) {
        this.volunteers.add(volunteer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Shift: ").append(day).append(" - ").append(timeOfDay).append("\n");
        sb.append("Volunteers: \n");
        for (Volunteer v : volunteers) {
            sb.append("- ").append(v.getName())
              .append(" ").append(getSkillEmoji(v.getSkill())) // Add emoji based on skill assignment
              .append("\n");
        }
        return sb.toString();
    }

   
    private String getSkillEmoji(String skill) {
        switch (skill) {
            case "beer":
                return "🥤"; // Beer emoji
            case "ball runner":
                return "🏉"; // Ball runner emoji
            default:
                return ""; // No emoji
        }
    }
}
/*
 * Sprint 1: Created Shift class to store shift details (day, time) and a list of volunteers assigned to the shift.
 * Sprint 2: Added method to add volunteers to a shift and display shift details.
 * Sprint 3: Implemented toString method to output shift information along with assigned volunteers.
 * Sprint 4: Testing and improving user interface, ensuring smooth functionality in the output.
 */