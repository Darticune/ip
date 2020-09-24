package duke.tasklist.task;

/**
 * Todo class, the class for the Todo objects
 */
public class Todo extends Task{
    /**
     * Constructor for Todo class for creation from user input
     * @param description String that contains description of Todo
     * @param blank String with whitespace as Todo classes has no timeline
     * @param taskType String that contains fixed taskType for Todo objects
     */
    public Todo(String description, String blank, String taskType) {
        super(description, blank, taskType);
    }

    /**
     * Constructor for Todo class for creation from savedData
     * @param taskType String that contains fixed taskType for Todo objects
     * @param state String that contains the isDone state of the Todo object
     * @param description String that contains description of Todo
     * @param timeline String that contains whitespace as Todo classes have no timeline
     */
    public Todo(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }
}
