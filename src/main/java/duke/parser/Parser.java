package duke.parser;

import duke.ui.Ui;
import duke.command.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Parser class, the class that is used to continuously take in commands from the user and acts on them until the bye
 * command is given.
 */
public class Parser {
    protected boolean isBye;
    /**
     * Constructor for Parser, which:
     * Creates Command objects with user input and passes them to processCommand() until the command "bye" is given
     * Updates savedData file by calling storage.updateSavedData() after each command
     * @param taskList Created Tasklist Object, used to store and access the task list
     */
    public Parser (TaskList taskList) {
        do {
            Command command = new Command();
            setIsBye(command);
            if (!command.getIsValid()) {
                isBye = false;
                continue;
            }

            Ui.printDivider();
            processCommand(taskList, command);
            Storage.updateSavedData(taskList.getRecordedList());
            Ui.printDivider();
        } while (!isBye);
    }
    private void setIsBye(Command command) {
        isBye = command.getIsBye();
    }

    /**
     * Checks the command type and calls the corresponding action to be carried out on the task list
     * @param taskList Created Tasklist Object, used to store and access the task list
     * @param command Command object to be checked and acted on
     */
    public void processCommand(TaskList taskList, Command command) {
        switch(command.getCommandType()) {
        case Command.TODO:
            taskList.addTodoToList(command.getCommandDetails());
            break;
        case Command.DEADLINE:
            taskList.addDeadlineToList(command.getCommandDetails());
            break;
        case Command.EVENT:
            taskList.addEventToList(command.getCommandDetails());
            break;
        case Command.LIST:
            taskList.printList(taskList.getRecordedList());
            break;
        case Command.DONE:
            taskList.completeTask(command.getCommandDetails());
            break;
        case Command.DELETE:
            taskList.deleteTask(command.getCommandDetails());
            break;
        case Command.FIND:
            taskList.findKeyword(command.getCommandDetails());
            break;
        case Command.BYE:
            Ui.bidGoodbye();
            break;
        default:
            System.out.println(Ui.INVALID_COMMAND);
        }
    }
}
