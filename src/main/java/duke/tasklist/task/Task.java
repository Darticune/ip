package duke.tasklist.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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

    //Date Conversion
    private String toDateFormat (String date) {
        DateTimeFormatter inFormatter = DateTimeFormatter.ofPattern("d/M/yyyy");
        DateTimeFormatter outFormatter = DateTimeFormatter.ofPattern("d MMM yyyy");
        LocalDate parsedDate = LocalDate.parse(date, inFormatter);
        return outFormatter.format(parsedDate);
    }
    /**
     * Parses the timeline field for a date that will be converted from d/MM/yyyy to d MMM yyyy
     */
    public void parseForDate() {
        String[] parsedStrings = timeline.split(" ");
        String lastParsedString = parsedStrings[parsedStrings.length -1];
                timeline = "";
        for (String parsedString : parsedStrings) {
            try {
                timeline += toDateFormat(parsedString);
            } catch (DateTimeParseException | IndexOutOfBoundsException e) {
                timeline += parsedString;
            } finally {
                if (!parsedString.equals(lastParsedString)) {
                    timeline += " ";
                }
            }
        }
    }

    //Getters
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
     * @return String containing taskType of Task object
     */
    public String getTaskType() {
        return taskType;
    }

    //Setters
    /**
     * Setter for isDone to true from the default false once the done command is issued for the Task object
     */
    public void setAsDone() {
        this.isDone = true;
    }

    //Convert to save format
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
