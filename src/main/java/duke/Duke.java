package duke;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.util.Scanner;
import java.util.ArrayList;


public class Duke {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String HELLO = "Hello! I'm duke.Duke";
    public static final String INVALID_COMMAND = "Sorry, this command does not exist!";
    public static final String PROMPT_START = "What can I do for you?";
    public static final String EMPTY_LIST = "Sorry, you have no tasks in the list";
    public static final String DETAILS_ERROR = "ERROR: The description of ";
    public static final String EMPTY_FIELD = " cannot be empty!";
    public static final String DEADLINE_FORMAT_INCORRECT = "Command format for Deadline is incorrect.";
    public static final String EVENT_FORMAT_INCORRECT = "Command format for Event is incorrect.";
    public static final String CORRECT_DEADLINE_FORMAT = "Correct format: deadline [DEADLINE_NAME] /by [DUE_BY]";
    public static final String CORRECT_EVENT_FORMAT = "Correct format: event [EVENT_NAME] /at [DURATION]";
    public static final String ADDED_TASK = "Got it. I've added this task:";
    public static final String COMPLETED_TASK = "Nice! I've marked this task as done:";
    public static final String DELETED_TASK = "Noted. I've removed this task:";
    public static final String INDEX_BEYOND_LIST = "Sorry, there is no such item.";
    public static final String INVALID_INDEX = "Please enter a valid task number.";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";

    public static void printDivider() {
        System.out.println(DIVIDER);
    }
    public static void printWelcomeMessage() {
        printDivider();
        System.out.println(HELLO);
        System.out.println(PROMPT_START);
        printDivider();
    }
    public static String getCommand() {
        Scanner input = new Scanner(System.in);
        return input.nextLine();
    }
    public static String extractCommandType(String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings[0];
    }
    public static boolean isAddTask (String commandType) {
        switch (commandType) {
        case "todo":
        case "deadline":
        case "event":
            return true;
        default:
            return false;
        }
    }
    public static boolean isCompleteTask (String commandType) {
        return commandType.equals("done");
    }
    public static boolean isDeleteTask (String commandType) {
        return commandType.equals("delete");
    }
    public static boolean isBye (String keyword) {
        return keyword.equals("bye");
    }
    public static boolean requireCommandDetails (String commandType) {
        return ((isAddTask(commandType)) || isCompleteTask(commandType) || isDeleteTask(commandType));
    }
    public static String extractCommandDetails(String command) {
            String[] parsedStrings = command.split(" ", 2);
            return parsedStrings[1];
        }
    public static void handleEmptyCommandDetails (String commandType) {
        printDivider();
        System.out.println(DETAILS_ERROR + commandType + EMPTY_FIELD);
        printDivider();
    }
    public static void printTask (ArrayList<Todo> recordedList, int index) {
        Todo element = recordedList.get(index);
        System.out.print("[" + element.getTaskType() + "]");
        System.out.print("[" + element.getStatusIcon() + "] " + element.getDescription());
        System.out.println(element.getTimeline());
    }
    public static void printTask (Todo element) {
        System.out.print("[" + element.getTaskType() + "]");
        System.out.print("[" + element.getStatusIcon() + "] " + element.getDescription());
        System.out.println(element.getTimeline());
    }
    public static void printList (ArrayList<Todo> recordedList) {
        int listCount = recordedList.size();
        if (listCount == 0) {
            System.out.println(EMPTY_LIST);
        } else {
            for (int i = 0; i < listCount; i++) {
                System.out.print(i + 1);
                System.out.print(". ");
                printTask(recordedList, i);
            }
        }
    }
    public static void printTaskTally (ArrayList<Todo> recordedList) {
        int listCount = recordedList.size();
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }
    public static void endAddTask (ArrayList<Todo> recordedList) {
        System.out.println(ADDED_TASK);
        int listCount = recordedList.size();
        printTask(recordedList, listCount-1);
        printTaskTally(recordedList);
    }
    public static void addTodoToList (ArrayList<Todo> recordedList, String details) {
        recordedList.add(new Todo(details, " ", "T"));
        endAddTask(recordedList);
    }
    public static void addDeadlineToList (ArrayList<Todo> recordedList, String details) {
        String[] parsedStrings = details.split(" /by ", 2);
        try {
            recordedList.add(new Deadline(parsedStrings[0], parsedStrings[1], "D"));
            endAddTask(recordedList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(DEADLINE_FORMAT_INCORRECT);
            System.out.println(CORRECT_DEADLINE_FORMAT);
        }
    }
    public static void addEventToList (ArrayList<Todo> recordedList, String details) {
        String[] parsedStrings = details.split(" /at ", 2);
        try {
            recordedList.add(new Event(parsedStrings[0], parsedStrings[1], "E"));
            endAddTask(recordedList);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(EVENT_FORMAT_INCORRECT);
            System.out.println(CORRECT_EVENT_FORMAT);
        }
    }
    public static int getTaskIndex (String commandDetails) {
        return Integer.parseInt(commandDetails)-1;
    }
    public static void setTaskAsDone (ArrayList<Todo> recordedList, int index) {
        recordedList.get(index).setAsDone();
        System.out.println(COMPLETED_TASK);
        printTask(recordedList, (index));
    }
    public static void completeTask (ArrayList<Todo> recordedList, String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails);
            setTaskAsDone(recordedList, index);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INDEX);
        } catch (NullPointerException e) {
            System.out.println(INDEX_BEYOND_LIST);
            System.out.println(INVALID_INDEX);
        }
    }
    public static void removeTaskFromList (ArrayList<Todo> recordedList, int index) {
        Todo targetedTask = recordedList.remove(index);
        System.out.println(DELETED_TASK);
        printTask(targetedTask);
        printTaskTally(recordedList);
    }
    public static void deleteTask (ArrayList<Todo> recordedList, String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails);
            removeTaskFromList(recordedList, index);
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INDEX);
        } catch (NullPointerException e) {
            System.out.println(INDEX_BEYOND_LIST);
            System.out.println(INVALID_INDEX);
        }
    }
    public static void bidGoodbye () {
        System.out.println(GOODBYE);
    }
    public static void processCommand(ArrayList<Todo> recordedList, String commandType, String commandDetails) {
        switch(commandType) {
        case "todo":
            addTodoToList(recordedList, commandDetails);
            break;
        case "deadline":
            addDeadlineToList(recordedList, commandDetails);
            break;
        case "event":
            addEventToList(recordedList, commandDetails);
            break;
        case "list":
            printList(recordedList);
            break;
        case "done":
            completeTask(recordedList, commandDetails);
            break;
        case "delete":
            deleteTask(recordedList, commandDetails);
            break;
        case "bye":
            bidGoodbye();
            break;
        default:
            System.out.println(INVALID_COMMAND);
        }
    }
    public static void serveUser() {
        ArrayList<Todo> recordedList = new ArrayList<>();
        String command, commandType, commandDetails = "";

        do {
            command = getCommand();
            commandType = extractCommandType(command.trim());

            if (requireCommandDetails(commandType)) {
                try {
                    commandDetails = extractCommandDetails(command.trim());
                } catch (IndexOutOfBoundsException e) {
                    handleEmptyCommandDetails(commandType);
                    continue;
                }
            }

            printDivider();
            processCommand(recordedList, commandType, commandDetails);
            printDivider();

        } while (!isBye(commandType));
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        serveUser();
    }
}
