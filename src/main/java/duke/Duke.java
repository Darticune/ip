package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

/**
 * Duke Class, the class that initialises and runs the functionalities for Duke
 */
public class Duke {
    /**
     * Prints the Welcome Message and creates the TaskList object using Storage.createTaskListFromSavedData()
     * Creates a Parser Object with the created TaskList Object
     */
    public static void main(String[] args) {
        Ui.printWelcomeMessage();
        TaskList taskList = new TaskList(Storage.createTaskListFromSavedData());
        new Parser(taskList);
    }
}
