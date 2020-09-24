package duke.tasklist.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task{
    public Deadline(String description, String completeBy, String taskType) {
        super(description, completeBy, taskType);
        parseForDate();
    }

    //Construct from saved data
    public Deadline(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }

    @Override
    public String getTimeline() {
        return (" (by: " + timeline + ")");
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + SEPARATOR + timeline;
    }
}
