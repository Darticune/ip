package duke.tasklist.task;

/**
 * Event class, the class for the Event objects
 */
public class Event extends Task{
    /**
     * Constructor for Event class for creation from user input
     * @param description Description of Event
     * @param duration Timeline of Event
     * @param taskType Fixed taskType for Event objects
     */
    public Event(String description, String duration, String taskType) {
        super(description, duration, taskType);
        parseForDate();
    }
    /**
     * Constructor for Event class for creation from savedData
     * @param taskType Fixed taskType for Event objects
     * @param state isDone state of the Event object
     * @param description Description of Event
     * @param timeline Timeline of Event
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
