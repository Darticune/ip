package duke.command;

import duke.ui.Ui;

import java.util.Scanner;

/**
 * Command class, the class that takes in a command from the user input, checks if it is valid, and parses it
 */
public class Command {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String BYE = "bye";

    public String command, commandType, commandDetails = "";
    public boolean isValid;
    public boolean isBye;

    /**
     * Constructor for Command class
     * Takes in user input via getCommand() and checks if the input is valid
     * <p>
     * If valid, it is parsed into the fields:
     * commandType - The type of command it is
     * commandDetails - The details of the command
     * and the isValid boolean is set to true. Otherwise, isValid is set to false.
     * <p>
     * It also sets the boolean isBye with setIsBye so that the Parser class can extract this value to break the loop
     * if so.
     */
    public Command () {
        command = getCommand();
        commandType = extractCommandType(command.trim());

        setValidity(true);
        setIsBye(commandType);

        if (requireCommandDetails(commandType)) {
            try {
                commandDetails = extractCommandDetails(command.trim());
            } catch (IndexOutOfBoundsException e) {
                handleEmptyCommandDetails(commandType);
                setValidity(false);
            }
        }
    }

    //Construct command
    private void setValidity (boolean validity) {
        isValid = validity;
    }
    private void setIsBye(String keyword) {
        isBye =  keyword.equals("bye");
    }
    private String getCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    private String extractCommandType(String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings[0];
    }
    private boolean isAddTask(String commandType) {
        switch (commandType) {
        case "todo":
        case "deadline":
        case "event":
            return true;
        default:
            return false;
        }
    }
    private static boolean isCompleteTask(String commandType) {
        return commandType.equals("done");
    }
    private boolean isDeleteTask(String commandType) {
        return commandType.equals("delete");
    }
    private boolean requireCommandDetails(String commandType) {
        return ((isAddTask(commandType)) || isCompleteTask(commandType) || isDeleteTask(commandType));
    }
    private String extractCommandDetails(String command) {
            String[] parsedStrings = command.split(" ", 2);
            return parsedStrings[1];
    }
    private void handleEmptyCommandDetails(String commandType) {
        Ui.printDivider();
        System.out.println(Ui.DETAILS_ERROR + commandType + Ui.EMPTY_FIELD);
        Ui.printDivider();
    }
}
