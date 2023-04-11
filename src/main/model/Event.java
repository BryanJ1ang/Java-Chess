package model;

import java.util.Calendar;
import java.util.Date;

// Represents a Chess event.
public class Event {
    private static final int HASH_CONSTANT = 13;
    private Date dateLogged;
    private String description;

    // EFFECTS: Creates an event with given description and current date/time stamp
    public Event(String description) {
        dateLogged = Calendar.getInstance().getTime();
        this.description = description;
    }

    // EFFECTS: Returns date of an event
    public Date getDate() {
        return dateLogged;
    }


    // EFFECT: Returns description of an event
    public String getDescription() {
        return description;
    }

    // EFFECTS: Overrides equals
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }
        Event otherEvent = (Event) other;

        return (this.dateLogged.equals(otherEvent.dateLogged)
                && this.description.equals(otherEvent.description));
    }

    // EFFECTS: Overrides hashcode
    @Override
    public int hashCode() {
        return (HASH_CONSTANT * dateLogged.hashCode() + description.hashCode());
    }

    // EFFECTS: converts date into string
    @Override
    public String toString() {
        return dateLogged.toString() + "\n" + description;
    }
}