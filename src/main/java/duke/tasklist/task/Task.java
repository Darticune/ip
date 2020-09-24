package duke.tasklist.task;

public abstract class Task {
    public static final String TICK_SYMBOL = "\u2713";
    public static final String CROSS_SYMBOL = "\u2718";
    public final String SEPARATOR = " | ";

    protected String description;
    protected boolean isDone;
    protected String taskType;
    protected String timeline;

    public Task(String description, String timeline, String taskType) {
        this.description = description;
        this.isDone = false;
        this.timeline = timeline;
        this.taskType = taskType;
    }

    //Construct from saved data
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

    public String getDescription() {
        return description;
    }
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : CROSS_SYMBOL);
    }
    public void setAsDone() {
        this.isDone = true;
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
