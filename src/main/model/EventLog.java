package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;



// Represents log of Chess events
public class EventLog implements Iterable<Event> {
    /** the only EventLog in the system (Singleton Design Pattern) */
    private static EventLog theLog;
    private Collection<Event> events;

    /**
     * Prevent external construction.
     * (Singleton Design Pattern).
     */
    // EFFECT: Constructor for event log
    private EventLog() {
        events = new ArrayList<Event>();
    }

    // MODIFIES: this
    // EFFECT: Returns only instance of EventLog
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }
        return theLog;
    }

    // MODIFIES: this
    // EFFECT: adds an event to eventlog
    public void logEvent(Event e) {
        events.add(e);
    }

    // EFFECT: clears event log and logs the event
    public void clear() {
        events.clear();
        logEvent(new Event("Event log cleared."));
    }

    // EFFECT: overrides iterator
    @Override
    public Iterator<Event> iterator() {
        return events.iterator();
    }
}