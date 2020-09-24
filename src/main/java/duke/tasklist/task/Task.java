package duke.tasklist.task;

/**
 * Task class, the abstract class for the Task objects
 */
public abstract class Task {
    public static final String TICK_SYMBOL = "\u2713";
    public static final String CROSS_SYMBOL = "\u2718";
    public final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String timeline;

    /**
     * Constructor for Task class for creation from user input
     * @param description String that contains description of Task
     * @param timeline String that contains timeline of Task
     * @param taskType String that contains fixed taskType for Task objects
     */
    public Task(String description, String timeline, String taskType) {
        this.description = description;
        this.isDone = false;
        this.timeline = timeline;
        this.taskType = taskType;
    }

    /**
     * Constructor for Task class for creation from savedData
     * @param taskType String that contains fixed taskType for Task objects
     * @param state String that contains the isDone state of the Task object
     * @param description String that contains description of Task
     * @param timeline String that contains timeline of Task
     */
    public Task(String taskType, String state, String description, String timeline) {
        this.description = description;
        if(state.equals("1")) {
            this.setAsDone();
        } else {
            this.isDone = false;
        }
        this.taskType = taskType;
        this.timeline = timeline;
    }

    /**
     * Getter for description of Task object
     * @return String containing description of Task object
     */
    public String getDescription() {
        return description;
    }
    /**
     * Getter for Status Icon representing isDone
     * @return String containing either TICK_SYMBOL if Task is Done and CROSS_SYMBOL if Task is not Done
     */
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
    }
    /**
     * Getter for timeline of Task object
     * @return String containing timeline of Task object
     */
    public String getTimeline() {
        return timeline;
    }
    /**
     * Getter for taskType of Task object
     * @return
     */
    public String getTaskType() {
        return taskType;
    }
    /**
     * Setter for isDone to true from the default false once the done command is issued for the Task object
     */
    public void setAsDone() {
        this.isDone = true;
    }
    /**
     * Returns formatted String ready to be saved into savedData file
     * @return String with the formatted Task details
     */
    public String toSaveFormat() {
        String payload = taskType + SEPARATOR;
        if (isDone) {
            payload += "1";
        } else {
            payload += "0";
        }
        payload += SEPARATOR + description;
        return payload;
    }
}
