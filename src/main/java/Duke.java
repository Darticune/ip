import java.util.Scanner;


public class Duke {
    public static void printHorizontal () {
        System.out.println("____________________________________________________________");
    }
    public static void printHeader() {
        printHorizontal();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontal();
    }
    public static String extractKeyword (String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings[0];
    }
    public static String extractDetails (String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings[1];
    }
    public static boolean isBye (String keyword) {
        return keyword.equals("bye");
    }
    public static int findCommandLength (String command) {
        String[] parsedStrings = command.split(" ", 2);
        return parsedStrings.length;
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
        }
        else {
            for (int i = 0; i < listCount; i++) {
                System.out.print(i + 1);
                System.out.print(". ");
                printTask(recordedList, i);
            }
        }
    }
    public static int addTodoToList (Todo[] recordedList, String details, int listCount) {
        recordedList[listCount] = new Todo(details, " ", "T");
        System.out.println("Got it. I've added this task:");
        printTask(recordedList, listCount);
        listCount++;
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
        return listCount;
    }
    public static int addDeadlineToList (Todo[] recordedList, String details, int listCount) {
        String[] parsedStrings = details.split(" /by ", 2);
        recordedList[listCount] = new Deadline(parsedStrings[0], parsedStrings[1], "D");
        System.out.println("Got it. I've added this task:");
        printTask(recordedList, listCount);
        listCount++;
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
        return listCount;
    }
    public static int addEventToList (Todo[] recordedList, String details, int listCount) {
        String[] parsedStrings = details.split(" /at ", 2);
        recordedList[listCount] = new Event(parsedStrings[0], parsedStrings[1], "E");
        System.out.println("Got it. I've added this task:");
        printTask(recordedList, listCount);
        listCount++;
        System.out.print("Now you have " + listCount + " task");
        if(listCount > 1) {
            System.out.print("s");
        }
        System.out.println(" in the list.");
        return listCount;
    }
    public static void completeTask (Todo[] recordedList, int index) {
        recordedList[index].setAsDone();
        System.out.println("Nice! I've marked this task as done:");
        printTask(recordedList, (index));

    }
    public static void bidGoodbye () {
        printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontal();
    }

    public static void main(String[] args) {
        Todo[] recordedList = new Todo[100];
        int listCount = 0;

        printHeader();

        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        String keyword = extractKeyword(command.trim());
        String details;
        if(findCommandLength(command) > 1) {
            details = extractDetails(command.trim());
        }
        else {
            details = " ";
        }

        while (!isBye(keyword)) {
            switch(keyword) {
            case "todo":
                printHorizontal();
                listCount = addTodoToList(recordedList, details, listCount);
                printHorizontal();
                break;
            case "deadline":
                printHorizontal();
                listCount = addDeadlineToList(recordedList, details, listCount);
                printHorizontal();
                break;
            case "event":
                printHorizontal();
                listCount = addEventToList(recordedList, details, listCount);
                printHorizontal();
                break;
            case "list":
                printHorizontal();
                printList(recordedList, listCount);
                printHorizontal();
                break;
            case "done":
                int index = Integer.parseInt(details);
                if (index > listCount) {
                    printHorizontal();
                    System.out.println("Sorry, there is no such item.");
                    printHorizontal();
                }
                else {
                    printHorizontal();
                    completeTask(recordedList, (index -1));
                    printHorizontal();
                }
                break;
            default:
                printHorizontal();
                System.out.println("Sorry, this command does not exist!");
                printHorizontal();
            }
            command = input.nextLine();
            keyword = extractKeyword(command.trim());
            if(findCommandLength(command) > 1) {
                details = extractDetails(command.trim());
            }
            else {
                details = " ";
            }
        }
        bidGoodbye();
    }
}   
