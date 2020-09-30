package duke.tasklist.task;

/**
 * Todo class, the class for the Todo objects
 */
public class Todo extends Task{
    /**
     * Constructor for Todo class for creation from user input
     * @param description Description of Todo
     * @param blank Whitespace as Todo classes has no timeline
     * @param taskType Fixed taskType for Todo objects
     */
    public Todo(String description, String blank, String taskType) {
        super(description, blank, taskType);
    }

    /**
     * Constructor for Todo class for creation from savedData
     * @param taskType Fixed taskType for Todo objects
     * @param state isDone state of the Todo object
     * @param description Description of Todo
     * @param timeline Whitespace as Todo classes have no timeline
     */
    public Todo(String taskType, String state, String description, String timeline) {
        super(taskType, state, description, timeline);
    }
}
