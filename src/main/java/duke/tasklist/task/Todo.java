package duke.tasklist.task;

public class Todo extends Task{

    public Todo(String description, String blank, String taskType) {
        super(description, blank, taskType);
    }

    //Construct from saved data
    public Todo(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }

}
