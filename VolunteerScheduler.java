package volunteer_scheduler_project;

import java.util.*;

public class VolunteerScheduler {
    private List<Volunteer> allVolunteers;
    private Map<String, List<Shift>> shifts;

    public VolunteerScheduler() {
        this.allVolunteers = new ArrayList<>();
        this.shifts = new HashMap<>();
        initializeShifts();
    }

    private void initializeShifts() {
        // Initialize shifts for each day of the week and morning/afternoon time slots
        String[] days = {"Friday", "Saturday", "Sunday"};
        String[] timeOfDay = {"Morning", "Afternoon"};
        
        for (String day : days) {
            List<Shift> dayShifts = new ArrayList<>();
            for (String time : timeOfDay) {
                dayShifts.add(new Shift(day, time));
            }
            shifts.put(day, dayShifts);
        }
    }

    public void addVolunteer(Volunteer volunteer) {
        allVolunteers.add(volunteer);
        assignVolunteerToShifts(volunteer);
    }

    private void assignVolunteerToShifts(Volunteer volunteer) {
        for (String day : volunteer.getDaysAvailable()) {
            // Assign to both morning and afternoon shifts based on availability
            for (Shift shift : shifts.get(day)) {
                if (volunteer.getAvailability().equals(shift.getTimeOfDay())) {
                    shift.addVolunteer(volunteer);
                }
            }
        }
    }

    public void assignVolunteers() {
        // Generate the schedule by combining all shifts
        StringBuilder schedule = new StringBuilder();
        for (List<Shift> dayShifts : shifts.values()) {
            for (Shift shift : dayShifts) {
                schedule.append(shift).append("\n");
            }
        }
    }

    public String getFullScheduleText() {
        StringBuilder schedule = new StringBuilder();
        for (List<Shift> dayShifts : shifts.values()) {
            for (Shift shift : dayShifts) {
                schedule.append(shift).append("\n");
            }
        }
        return schedule.toString();
    }
}


/*
 * Sprint 1: Set up queues for ball runners and beer garden to hold the names of volunteers.
 * Sprint 2: Implemented addVolunteer method to assign volunteers based on their skill (ball runner or beer).
 * Sprint 3: Created assignVolunteers and getFullScheduleText methods to generate and display volunteer assignments.
 * Sprint 4: Testing and improving the user interface; optimized the scheduling logic.
 */
