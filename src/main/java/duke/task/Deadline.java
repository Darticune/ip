package duke.task;

public class Deadline extends Todo{

    public Deadline(String details, String completeBy, String taskType) {
        super(details, completeBy, taskType);
    }

    public String getTimeline() {
        return (" (by: " + timeline + ")");
    }
}
