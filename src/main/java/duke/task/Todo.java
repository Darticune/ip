package duke.task;

public class Todo extends Task{
    protected String taskType;
    protected String timeline;
    public final String SEPARATOR = " | ";

    public Todo(String description, String blank, String taskType) {
        super(description);
        timeline = blank;
        this.taskType = taskType;
    }
    public Todo(String taskType, String state, String description, String timeline) {
        super(description);
        if(state.equals("1")) {
            this.setAsDone();
        }
        this.taskType = taskType;
        this.timeline = timeline;
    }
    public String getTimeline() {
        return timeline;
    }
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
    public String getTaskType() {
        return taskType;
    }
}
