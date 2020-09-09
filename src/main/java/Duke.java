import java.util.Scanner;


public class Duke {
    public static final int MAX_TASK_COUNT = 100;
    public static final String DIVIDER = "____________________________________________________________";
    public static final String DETAILS_ERROR = "ERROR: The description of ";
    public static final String EMPTY_FIELD = " cannot be empty!";
    public static final String DEADLINE_FORMAT_INCORRECT = "Command format for Deadline is incorrect.";
    public static final String EVENT_FORMAT_INCORRECT = "Command format for Event is incorrect.";
    public static final String CORRECT_DEADLINE_FORMAT = "Correct format: deadline [DEADLINE_NAME] /by [DUE_BY]";
    public static final String CORRECT_EVENT_FORMAT = "Correct format: event [EVENT_NAME] /at [DURATION]";
    public static final String COMPLETED_TASK = "Nice! I've marked this task as done:";
    public static final String INDEX_BEYOND_LIST = "Sorry, there is no such item.";
    public static final String INVALID_INDEX = "Please enter a valid task number.";
    public static final String INVALID_COMMAND = "Sorry, this command does not exist!";
    public static final String HELLO = "Hello! I'm Duke";
    public static final String PROMPT_START = "What can I do for you?";
    public static final String EMPTY_LIST = "Sorry, you have no tasks in the list";
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
    public static boolean isBye (String keyword) {
        return keyword.equals("bye");
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
    public static void printTask (Todo[] recordedList, int index) {
        Todo element = recordedList[index];
        System.out.print("[" + element.taskType + "]");
        System.out.print("[" + element.getStatusIcon() + "] " + element.description);
        System.out.println(element.getTimeline());
    }
    public static void printList (Todo[] recordedList, int listCount) {
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
    public static boolean addDeadlineToList (Todo[] recordedList, String details, int listCount) {
        String[] parsedStrings = details.split(" /by ", 2);
        boolean addedSuccessfully = false;
        try {
            recordedList[listCount] = new Deadline(parsedStrings[0], parsedStrings[1], "D");
            endAddTask(recordedList, listCount);
            addedSuccessfully = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(DEADLINE_FORMAT_INCORRECT);
            System.out.println(CORRECT_DEADLINE_FORMAT);
        }
        return addedSuccessfully;
    }
    public static boolean addEventToList (Todo[] recordedList, String details, int listCount) {
        String[] parsedStrings = details.split(" /at ", 2);
        boolean addedSuccessfully = false;
        try {
            recordedList[listCount] = new Event(parsedStrings[0], parsedStrings[1], "E");
            endAddTask(recordedList, listCount);
            addedSuccessfully = true;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(EVENT_FORMAT_INCORRECT);
            System.out.println(CORRECT_EVENT_FORMAT);
        }
        return addedSuccessfully;
    }
    public static int getTaskIndex (String commandDetails) {
        return Integer.parseInt(commandDetails);
    }
    public static void completeTask (Todo[] recordedList, String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails)-1;
            recordedList[index].setAsDone();
            System.out.println(COMPLETED_TASK);
            printTask(recordedList, (index));
        } catch (NumberFormatException e) {
            System.out.println(INVALID_INDEX);
        } catch (NullPointerException e) {
            System.out.println(INDEX_BEYOND_LIST);
            System.out.println(INVALID_INDEX);
        }

    }
    public static boolean processCommand(Todo[] recordedList, String commandType, String commandDetails,int listCount) {
        boolean addedSuccessfully;
        switch(commandType) {
        case "todo":
            addTodoToList(recordedList, commandDetails, listCount);
            return true;
        case "deadline":
            addedSuccessfully = addDeadlineToList(recordedList, commandDetails, listCount);
            if (addedSuccessfully) {
                return true;
            }
            break;
        case "event":
            addedSuccessfully = addEventToList(recordedList, commandDetails, listCount);
            if (addedSuccessfully) {
                return true;
            }
            break;
        case "list":
            printList(recordedList, listCount);
            break;
        case "done":
            completeTask(recordedList, commandDetails);
            break;
        case "bye":
            bidGoodbye();
            break;
        default:
            System.out.println(INVALID_COMMAND);
        }
        return false;
    }
    public static void serveUser() {
        Todo[] recordedList = new Todo[MAX_TASK_COUNT];
        int listCount = 0;

        String command;
        String commandType;
        String commandDetails = "";
        boolean addedTask;

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
            addedTask = processCommand(recordedList, commandType, commandDetails, listCount);
            printDivider();

            if (addedTask) {
                listCount++;
            }
        } while (!isBye(commandType));
    }
    public static void bidGoodbye () {
        System.out.println(GOODBYE);
    }

    public static void main(String[] args) {
        printWelcomeMessage();
        serveUser();
    }
}
