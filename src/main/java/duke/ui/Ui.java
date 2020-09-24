package duke.ui;

public class Ui {
    public static final String DIVIDER = "____________________________________________________________";
    public static final String HELLO = "Hello! I'm Duke";
    public static final String INVALID_COMMAND = "Sorry, this command does not exist!";
    public static final String PROMPT_START = "What can I do for you?";
    public static final String DETAILS_ERROR = "ERROR: The description of ";
    public static final String EMPTY_FIELD = " cannot be empty!";
    public static final String GOODBYE = "Bye. Hope to see you again soon!";
    public static final String INVALID_ENTRY = "Invalid entry in saved file: ";
    public static final String TEXT_FILE_CREATION_FAILED = "Unable to create text file";
    public static final String SAVE_FAILED = "Attempt to save data failed!";
    public static final String ADDED_TASK = "Got it. I've added this task:";
    public static final String COMPLETED_TASK = "Nice! I've marked this task as done:";
    public static final String CORRECT_DEADLINE_FORMAT = "Correct format: deadline [DEADLINE_NAME] /by [DUE_BY]";
    public static final String CORRECT_EVENT_FORMAT = "Correct format: event [EVENT_NAME] /at [DURATION]";
    public static final String DELETED_TASK = "Noted. I've removed this task:";
    public static final String DEADLINE_FORMAT_INCORRECT = "Command format for Deadline is incorrect.";
    public static final String EMPTY_LIST = "Sorry, you have no tasks in the list";
    public static final String EVENT_FORMAT_INCORRECT = "Command format for Event is incorrect.";
    public static final String INDEX_BEYOND_LIST = "Sorry, there is no such item.";
    public static final String INVALID_INDEX = "Please enter a valid task number.";

    public static void printDivider() {
        System.out.println(DIVIDER);
    }
    public static void printWelcomeMessage() {
        printDivider();
        System.out.println(HELLO);
        System.out.println(PROMPT_START);
        printDivider();
    }
    public static void bidGoodbye() {
        System.out.println(GOODBYE);
    }
}
