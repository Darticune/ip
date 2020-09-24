package duke.tasklist.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Event class, the class for the Event objects
 */
public class Event extends Task{
    /**
     * Constructor for Event class for creation from user input
     * @param description String that contains description of Event
     * @param duration String that contains timeline of Event
     * @param taskType String that contains fixed taskType for Event objects
     */
    public Event(String description, String duration, String taskType) {
        super(description, duration, taskType);
        parseForDate();
    }
    /**
     * Constructor for Event class for creation from savedData
     * @param taskType String that contains fixed taskType for Event objects
     * @param state String that contains the isDone state of the Event object
     * @param description String that contains description of Event
     * @param timeline String that contains timeline of Event
     */
    public Event(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }


    /**
     * Returns formatted String ready to be printed to console
     * @return String with the formatted output for Event timelines
     */
    @Override
    public String getTimeline() {
        return (" (at: " + timeline + ")");
    }
    /**
     * Returns formatted String ready to be saved into savedData file
     * @return String with the formatted Event details
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + SEPARATOR + timeline;
    }
}
