package duke.tasklist;

import duke.ui.Ui;
import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Todo;
import duke.tasklist.task.Task;

import java.util.ArrayList;

public class TaskList {

    public static final String TASK_TYPE_TODO = "T";
    public static final String TASK_TYPE_DEADLINE = "D";
    public static final String TASK_TYPE_EVENT = "E";
    public static final String NO_TIMELINE = " ";
    protected ArrayList<Task> recordedList;

    public TaskList (ArrayList<Task> initialisedList) {
        recordedList = initialisedList;
    }

    //Getters
    public ArrayList<Task> getRecordedList () {
        return recordedList;
    }

    //Editing the list
    public void endAddTask() {
        System.out.println(Ui.ADDED_TASK);
        int listCount = recordedList.size();
        printTask(recordedList.get(listCount-1));
        printTaskTally();
    }
    public void addTodoToList(String details) {
        recordedList.add(new Todo(details, NO_TIMELINE, TASK_TYPE_TODO));
        endAddTask();
    }
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
    public int getTaskIndex(String commandDetails) {
        return Integer.parseInt(commandDetails)-1;
    }
    public void setTaskAsDone(int index) {
        recordedList.get(index).setAsDone();
        System.out.println(Ui.COMPLETED_TASK);
        printTask(recordedList.get(index));
    }
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
    public void removeTaskFromList(int index) {
        Task targetedTask = recordedList.remove(index);
        System.out.println(Ui.DELETED_TASK);
        printTask(targetedTask);
        printTaskTally();
    }
    public void deleteTask(String commandDetails) {
        try {
            int index = getTaskIndex(commandDetails);
            removeTaskFromList(index);
        } catch (NumberFormatException e) {
            System.out.println(Ui.INVALID_INDEX);
        } catch (NullPointerException e) {
            System.out.println(Ui.INDEX_BEYOND_LIST);
            System.out.println(Ui.INVALID_INDEX);
        }
    }

    //Finding Keyword
    public void findKeyword(String details) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : recordedList) {
            if((task.getDescription()).contains(details)) {
                matchingTasks.add(task);
            }
        }
        printMatchingTasks(matchingTasks);
    }

    //Printing the list
    public void printMatchingTasks (ArrayList<Task> matchingTasks) {
        if (matchingTasks.size() != 0) {
            System.out.println(Ui.FIND_TASKS_SUCCESS);
            printList(matchingTasks);
        } else {
            System.out.println(Ui.FIND_TASKS_FAILURE);
        }
    }
    public void printTask(Task element) {
        System.out.print("[" + element.getTaskType() + "]");
        System.out.print("[" + element.getStatusIcon() + "] " + element.getDescription());
        System.out.println(element.getTimeline());
    }
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
    public void printTaskTally() {
        int listCount = recordedList.size();
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
    }
}
