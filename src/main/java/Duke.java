import java.util.Scanner;


public class Duke {
    public static final int MAX_TASK_COUNT = 100;
    public static final String DETAILS_ERROR = "ERROR: The description of ";
    public static final String EMPTY_FIELD = " cannot be empty!";
    public static final String COMPLETED_TASK = "Nice! I've marked this task as done:";
    public static final String INDEX_BEYOND_LIST = "Sorry, there is no such item.";
    public static final String INVALID_INDEX = "Please enter a valid task number.";
    public static final String INVALID_COMMAND = "Sorry, this command does not exist!";

    public static void printDivider() {
        System.out.println("____________________________________________________________");
    }
    public static void printHeader() {
        printDivider();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
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
    public static String extractCommandDetails(String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings[1];
    }
    public static void handleEmptyCommandDetails (String commandType) {
        printDivider();
        System.out.println(DETAILS_ERROR + commandType + EMPTY_FIELD);
        printDivider();
    }
    public static boolean isBye (String keyword) {
        return keyword.equals("bye");
    }
    public static void printTask (Todo[] recordedList, int index) {
        Todo element = recordedList[index];
        System.out.print("[" + element.taskType + "]");
        System.out.print("[" + element.getStatusIcon() + "] " + element.description);
        System.out.println(element.getTimeline());
    }
    public static void printList (Todo[] recordedList, int listCount) {
        if (listCount == 0) {
            System.out.println("Sorry, you have no tasks in the list");
        } else {
            for (int i = 0; i < listCount; i++) {
                System.out.print(i + 1);
                System.out.print(". ");
                printTask(recordedList, i);
            }
        }
    }
    public static void endAddTask (Todo[] recordedList, int listCount) {
        System.out.println("Got it. I've added this task:");
        printTask(recordedList, listCount);
        listCount++;
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }
    public static void addTodoToList (Todo[] recordedList, String details, int listCount) {
        recordedList[listCount] = new Todo(details, " ", "T");
        endAddTask(recordedList, listCount);
    }
    public static void addDeadlineToList (Todo[] recordedList, String details, int listCount) {
        String[] parsedStrings = details.split(" /by ", 2);
        recordedList[listCount] = new Deadline(parsedStrings[0], parsedStrings[1], "D");
        endAddTask(recordedList, listCount);
    }
    public static void addEventToList (Todo[] recordedList, String details, int listCount) {
        String[] parsedStrings = details.split(" /at ", 2);
        recordedList[listCount] = new Event(parsedStrings[0], parsedStrings[1], "E");
        endAddTask(recordedList, listCount);
    }
    public static int getTaskIndex (String commandDetails) {
        return Integer.parseInt(commandDetails);
    }
    public static void completeTask (Todo[] recordedList, String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails);
            recordedList[index-1].setAsDone();
            System.out.println(COMPLETED_TASK);
            printTask(recordedList, (index-1));
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INDEX);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(INDEX_BEYOND_LIST);
        }

    }
    public static void bidGoodbye () {
        printDivider();
        System.out.println("Bye. Hope to see you again soon!");
        printDivider();
    }

    public static void main(String[] args) {
        Todo[] recordedList = new Todo[MAX_TASK_COUNT];
        int listCount = 0;

        printHeader();

        String command;
        String commandType;
        String commandDetails = "";

        do {
            command = getCommand();
            commandType = extractCommandType(command.trim());
            if((isAddTask(commandType)) || isCompleteTask(commandType)) {
                try {
                    commandDetails = extractCommandDetails(command.trim());
                } catch (IndexOutOfBoundsException e) {
                    handleEmptyCommandDetails(commandType);
                    continue;
                }
            }

            printDivider();
            switch(commandType) {
            case "todo":
                addTodoToList(recordedList, commandDetails, listCount);
                listCount++;
                break;
            case "deadline":
                addDeadlineToList(recordedList, commandDetails, listCount);
                listCount++;
                break;
            case "event":
                addEventToList(recordedList, commandDetails, listCount);
                listCount++;
                break;
            case "list":
                printList(recordedList, listCount);
                break;
            case "done":
                completeTask(recordedList, commandDetails);
                break;
            default:
                System.out.println(INVALID_COMMAND);
            }
            printDivider();
        } while (!isBye(commandType));

        bidGoodbye();
    }
}
