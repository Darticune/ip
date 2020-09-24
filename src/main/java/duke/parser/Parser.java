package duke.parser;

import duke.ui.Ui;
import duke.command.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class Parser {
    protected boolean isBye;

    public Parser (TaskList taskList, Storage storage) {
        do {
            Command command = new Command();
            setIsBye(command);
            if (!command.getIsValid()) {
                continue;
            }

            Ui.printDivider();
            processCommand(taskList, command);
            storage.updateSavedData(taskList.getRecordedList());
            Ui.printDivider();
        } while (!isBye);
    }

    public void setIsBye(Command command) {
        isBye = command.getIsBye();
    }
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
