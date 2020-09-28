package duke.tasklist;

import duke.ui.Ui;
import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Todo;
import duke.tasklist.task.Task;

import java.util.ArrayList;

/**
 * TaskList class, the class that holds the lists of tasks and the methods that operate on it
 */
public class TaskList {

    public static final String TASK_TYPE_TODO = "T";
    public static final String TASK_TYPE_DEADLINE = "D";
    public static final String TASK_TYPE_EVENT = "E";
    public static final String NO_TIMELINE = " ";
    private ArrayList<Task> recordedList;
  
    /**
     * Constructor for TaskList class, initialises recordedList with the ArrayList<Task> that is passed to it
     * @param initialisedList ArrayList<Task> that recordedList is initialised with
     */
    public TaskList (ArrayList<Task> initialisedList) {
        recordedList = initialisedList;
    }

    //Getters
    /**
     * Getter for the recordedList stored in TaskList Objects
     * @return ArrayList<Task> stored in TaskList object
     */
    public ArrayList<Task> getRecordedList () {
        return recordedList;
    }

    //Editing the list
    private void endAddTask() {
        System.out.println(Ui.ADDED_TASK);
        int listCount = recordedList.size();
        printTask(recordedList.get(listCount-1));
        printTaskTally();
    }
    /**
     * Adds a new Todo object to recordedList with the String details that is passed to it
     * @param details String that contains description for the new Todo object
     */
    public void addTodoToList(String details) {
        recordedList.add(new Todo(details, NO_TIMELINE, TASK_TYPE_TODO));
        endAddTask();
    }
    /**
     * Adds a new Deadline object to recordedList with the String details that is passed to it
     * @param details String that contains description for the new Deadline object
     */
    public void addDeadlineToList(String details) {
        String[] parsedStrings = details.split(" /by ", 2);
        try {
            recordedList.add(new Deadline(parsedStrings[0], parsedStrings[1], TASK_TYPE_DEADLINE));
            endAddTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Ui.DEADLINE_FORMAT_INCORRECT);
            System.out.println(Ui.CORRECT_DEADLINE_FORMAT);
        }
    }
    /**
     * Adds a new Event object to recordedList with the String details that is passed to it
     * @param details String that contains description for the new Event object
     */
    public void addEventToList(String details) {
        String[] parsedStrings = details.split(" /at ", 2);
        try {
            recordedList.add(new Event(parsedStrings[0], parsedStrings[1], TASK_TYPE_EVENT));
            endAddTask();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(Ui.EVENT_FORMAT_INCORRECT);
            System.out.println(Ui.CORRECT_EVENT_FORMAT);
        }
    }
    private int getTaskIndex(String commandDetails) {
        return Integer.parseInt(commandDetails)-1;
    }
    private void setTaskAsDone(int index) {
        recordedList.get(index).setAsDone();
        System.out.println(Ui.COMPLETED_TASK);
        printTask(recordedList.get(index));
    }
    /**
     * Completes the task that is specified whose index is specified in the String commandDetails
     * @param commandDetails String that contains the index of the subclass object of Task to be completed
     */
    public void completeTask(String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails);
            setTaskAsDone(index);
        } catch (NumberFormatException e) {
            System.out.println(Ui.INVALID_INDEX);
        } catch (IndexOutOfBoundsException e) {
            System.out.println(Ui.INDEX_BEYOND_LIST);
            System.out.println(Ui.INVALID_INDEX);
        }
    }
    private void removeTaskFromList(int index) {
        Task targetedTask = recordedList.remove(index);
        System.out.println(Ui.DELETED_TASK);
        printTask(targetedTask);
        printTaskTally();
    }
    /**
     * Removes the task that is specified whose index is specified in the String commandDetails
     * @param commandDetails String that contains the index of the subclass object of Task to be deleted
     */
    public void deleteTask(String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails);
            removeTaskFromList(index);
        } catch (NumberFormatException e) {
            System.out.println(Ui.INVALID_INDEX);
        } catch (NullPointerException | IndexOutOfBoundsException e) {
            System.out.println(Ui.INDEX_BEYOND_LIST);
            System.out.println(Ui.INVALID_INDEX);
        }
}
    private boolean containsKeyword(String description, String keyword) {
        String descriptionLowerCase = description.toLowerCase();
        String keywordLowerCase = keyword.toLowerCase();
        return descriptionLowerCase.contains(keywordLowerCase);
    }
    /**
     * Parses recordedList for subclass objects of Task for those with description contain the String details, adds
     * them to a new ArrayList<Task> matchingTasks and passes the whole list to printMatchingTasks() to print to console
     * @param keyword String containing the keyword(s) to be searched for
     */
    public void findKeyword(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : recordedList) {
            if(containsKeyword(task.getDescription(), keyword)) {
                matchingTasks.add(task);
            }
        }
        printMatchingTasks(matchingTasks);
    }

    //Printing the list
    private void printTaskTally() {
        int listCount = recordedList.size();
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }
    private void printMatchingTasks (ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() != 0) {
            System.out.println(Ui.FIND_TASKS_SUCCESS);
            printList(matchingTasks);
        } else {
            System.out.println(Ui.FIND_TASKS_FAILURE);
        }
    }
    private void printTask(Task element) {
        System.out.print("[" + element.getTaskType() + "]");
        System.out.print("[" + element.getStatusIcon() + "] " + element.getDescription());
        System.out.println(element.getTimeline());
    }
    /**
     * Prints the ArrayList<Task> that is passed to it
     */
    public void printList(ArrayList<Task> listToPrint) {
        int listCount = listToPrint.size();
        if (listCount == 0) {
            System.out.println(Ui.EMPTY_LIST);
        } else {
            for (int i = 0; i < listCount; i++) {
                System.out.print(i + 1);
                System.out.print(". ");
                printTask(listToPrint.get(i));
            }
        }
    }
}
