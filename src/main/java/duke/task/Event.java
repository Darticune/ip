package duke.task;

public class Event extends Todo{

    public Event(String details, String duration, String taskType) {
        super(details, duration, taskType);
    }

    public Event (String taskType, String state, String description, String timeline) {
        super(taskType, state, description,timeline);
    }

    public String getTimeline() {
        return (" (at: " + timeline + ")");
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + SEPARATOR + timeline;
    }
}
