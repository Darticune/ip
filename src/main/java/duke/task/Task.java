package duke.task;

public class Task {
    protected String description;
    protected boolean isDone;
    public static final String TICK_SYMBOL = "\u2713";
    public static final String CROSS_SYMBOL = "\u2718";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
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
}
