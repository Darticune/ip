package duke.command;

import duke.ui.Ui;

import java.util.Scanner;

public class Command {
    public static final String TODO = "todo";
    public static final String DEADLINE = "deadline";
    public static final String EVENT = "event";
    public static final String LIST = "list";
    public static final String DONE = "done";
    public static final String DELETE = "delete";
    public static final String BYE = "bye";
    public static final String FIND = "find";

    public String command, commandType, commandDetails = "";
    public boolean isValid;
    public boolean isBye;

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
    public void setValidity (boolean validity) {
        isValid = validity;
    }
    public void setIsBye(String keyword) {
        isBye =  keyword.equals(BYE);
    }
    public String getCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public String extractCommandType(String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings[0];
    }
    public boolean isAddTask(String commandType) {
        switch (commandType) {
        case "todo":
        case "deadline":
        case "event":
            return true;
        default:
            return false;
        }
    }
    public static boolean isCompleteTask(String commandType) {
        return commandType.equals(DONE);
    }
    public boolean isDeleteTask(String commandType) {
        return commandType.equals(DELETE);
    }
    public boolean isFindKeyword(String commandType) {
        return commandType.equals(FIND);
    }
    public boolean requireCommandDetails(String commandType) {
        boolean requireCommandDetails1 = (isAddTask(commandType)) || isCompleteTask(commandType);
        boolean requireCommandDetails2 = (isFindKeyword(commandType) || isDeleteTask(commandType));
        return (requireCommandDetails1 || requireCommandDetails2);
    }
    public String extractCommandDetails(String command) {
            String[] parsedStrings = command.split(" ", 2);
            return parsedStrings[1];
    }
    public void handleEmptyCommandDetails(String commandType) {
        Ui.printDivider();
        System.out.println(Ui.DETAILS_ERROR + commandType + Ui.EMPTY_FIELD);
        Ui.printDivider();
    }
}
