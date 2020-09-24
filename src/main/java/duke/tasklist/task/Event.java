package duke.tasklist.task;

public class Event extends Task{
    public Event(String description, String duration, String taskType) {
        super(description, duration, taskType);
    }

    //Construct from saved data
    public Event(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }

    @Override
    public String getTimeline() {
        return (" (at: " + timeline + ")");
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + SEPARATOR + timeline;
    }
}
