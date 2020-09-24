package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {
    public static void main(String[] args) {
        Ui.printWelcomeMessage();

        Storage storage = new Storage();
        TaskList taskList = new TaskList(storage.createTaskListFromSavedData());
        new Parser(taskList, storage);
    }
}
