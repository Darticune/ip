package duke.parser;

import duke.ui.Ui;
import duke.command.Command;
import duke.storage.Storage;
import duke.tasklist.TaskList;

public class Parser {
    boolean isBye;

    public Parser (TaskList taskList, Storage storage) {
        do {
            Command command = new Command();
            setIsBye(command);
            if (!command.isValid) {
                continue;
            }

            Ui.printDivider();
            processCommand(taskList, command);
            storage.updateSavedData(taskList.recordedList);
            Ui.printDivider();
        } while (!isBye);
    }

    public void setIsBye(Command command) {
        isBye = command.isBye;
    }
    public void processCommand(TaskList taskList, Command command) {
        switch(command.commandType) {
        case Command.TODO:
            taskList.addTodoToList(command.commandDetails);
            break;
        case Command.DEADLINE:
            taskList.addDeadlineToList(command.commandDetails);
            break;
        case Command.EVENT:
            taskList.addEventToList(command.commandDetails);
            break;
        case Command.LIST:
            taskList.printList();
            break;
        case Command.DONE:
            taskList.completeTask(command.commandDetails);
            break;
        case Command.DELETE:
            taskList.deleteTask(command.commandDetails);
            break;
        case Command.BYE:
            Ui.bidGoodbye();
            break;
        default:
            System.out.println(Ui.INVALID_COMMAND);
        }
    }
}
