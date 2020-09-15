package duke.task;

public class Deadline extends Todo{

    public Deadline(String details, String completeBy, String taskType) {
        super(details, completeBy, taskType);
    }

    public Deadline (String taskType, String state, String description, String timeline) {
        super(taskType, state, description,timeline);
    }

    public String getTimeline() {
        return (" (by: " + timeline + ")");
    }

    @Override
    public String toSaveFormat() {
        return super.toSaveFormat() + SEPARATOR + timeline;
    }
}
