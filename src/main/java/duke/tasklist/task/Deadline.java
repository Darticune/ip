package duke.tasklist.task;

/**
 * Deadline class, the class for the Deadline objects
 */
public class Deadline extends Task{
    /**
     * Constructor for Deadline class for creation from user input
     * @param description Description of Deadline
     * @param completeBy Timeline of Deadline
     * @param taskType Fixed taskType for Deadline objects
     */
    public Deadline(String description, String completeBy, String taskType) {
        super(description, completeBy, taskType);
        parseForDate();
    }
    /**
     * Constructor for Deadline class for creation from savedData
     * @param taskType Fixed taskType for Deadline objects
     * @param state isDone state of the Deadline object
     * @param description Description of Deadline
     * @param timeline Timeline of Deadline
     */
    public Deadline(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }

    /**
     * Returns formatted String ready to be printed to console
     * @return String with the formatted output for Deadline timelines
     */
    @Override
    public String getTimeline() {
        return (" (by: " + timeline + ")");
    }
    /**
     * Returns formatted String ready to be saved into savedData file
     * @return String with the formatted Deadline details
     */
    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + SEPARATOR + timeline;
    }
}
