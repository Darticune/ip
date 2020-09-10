package duke.task;

public class Todo extends Task{
    protected String taskType;
    protected String timeline;

    public Todo (String description, String blank, String taskType) {
        super(description);
        timeline = blank;
        this.taskType = taskType;
    }
    public String getTimeline() {
        return timeline;
    }
    public String getTaskType() {
        return taskType;
    }
}
