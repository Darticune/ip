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
    public static final String FIND = "find";

    protected String command, commandType, commandDetails = "";
    protected boolean isValid;
    protected boolean isBye;

    /**
     * Constructor for Command class
     * Takes in user input via getCommand() and checks if the input is valid
     * <p>
     * If valid, it is parsed into the fields:
     * commandType - The type of command it is
     * commandDetails - The details of the command
     * isValid boolean is set to true.
     * <p>
     * If invalid, isValid is set to false.
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
        } else {
            String[] parsedStrings = command.split(" ");
            if ((parsedStrings.length != 1) && isRecognisedCommand(commandType)) {
                handleUnnecessaryCommandDetails(commandType);
                setValidity(false);
            }
        }
    }

    //Getters
    /**
     * Returns isBye (boolean) state
     * @return isBye field
     */
    public boolean getIsBye() {
        return isBye;
    }
    /**
     * Returns isValid (boolean) state
     * @return isValid field
     */
    public boolean getIsValid() {
        return isValid;
    }
    /**
     * Returns the commandType of the command
     * @return commandType field
     */
    public String getCommandType() {
        return commandType;
    }
    /**
     * Returns the commandDetails of the command
     * @return commandDetails field
     */
    public String getCommandDetails() {
        return commandDetails;
    }
    
    //Construct command
    private void setValidity (boolean validity) {
        isValid = validity;
    }
    private void setIsBye(String keyword) {
        isBye =  keyword.equals(BYE);
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
    private boolean isCompleteTask(String commandType) {
        return commandType.equals(DONE);
    }
    private boolean isDeleteTask(String commandType) {
        return commandType.equals(DELETE);
    }
    private boolean isFindKeyword(String commandType) {
        return commandType.equals(FIND);
    }
    private boolean isList(String commandType) {
        return commandType.equals(LIST);
    }
    private boolean isBye(String commandType) {
        return commandType.equals(BYE);
    }
    private boolean requireCommandDetails(String commandType) {
        boolean requireCommandDetails1 = (isAddTask(commandType)) || isCompleteTask(commandType);
        boolean requireCommandDetails2 = (isFindKeyword(commandType) || isDeleteTask(commandType));
        return (requireCommandDetails1 || requireCommandDetails2);
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
    private void handleUnnecessaryCommandDetails(String commandType) {
        Ui.printDivider();
        System.out.println(Ui.DETAILS_ERROR + commandType + Ui.NON_EMPTY_FIELD);
        Ui.printDivider();
    }
    private boolean isRecognisedCommand (String commandType) {
        boolean isRecognisedCommand1 = (isAddTask(commandType)) || isCompleteTask(commandType);
        boolean isRecognisedCommand2 = (isFindKeyword(commandType) || isDeleteTask(commandType));
        boolean isRecognisedCommand3 = (isList(commandType) || isBye(commandType));
        return (isRecognisedCommand1 || isRecognisedCommand2 || isRecognisedCommand3);
    }
}
