import java.util.Scanner;

public class Duke {
    public static int addToList (Task[] recordedList, String command, int listCount) {
        recordedList[listCount] = new Task(command);
        listCount++;
        System.out.println("added: " + command);
        return listCount;
    }
    public static void printList (Task[] recordedList, int listCount) {
        for (int i=0; i<listCount; i++) {
            System.out.print(i+1);
            System.out.print(". ");
            printTask(recordedList, i);
        }
    }
    public static void printTask (Task[] recordedList, int index) {
        System.out.println("[" +recordedList[index].getStatusIcon() + "] " + recordedList[index].description);
    }
    public static void bidGoodbye () {
        printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontal();
    }
    public static void processCommand () {
        Task[] recordedList = new Task[100];
        int listCount = 0;
        Scanner input = new Scanner(System.in);
        String command;
        command = input.nextLine();

        while (true) {
            if (command.equals("bye")) {
                bidGoodbye();
                break;
            }
            switch(command) {
            case "list":
                printHorizontal();
                printList(recordedList, listCount);
                printHorizontal();
                break;
            default:
                //Complete tasks
                if((command.length() == 6) && ((command.startsWith("done")))) {
                    int index = Integer.parseInt(command.substring(5));
                    recordedList[index-1].setAsDone();
                    printHorizontal();
                    System.out.println("Nice! I've marked this task as done:");
                    printTask(recordedList, (index-1));
                    printHorizontal();
                }
                //Add tasks
                else {
                    printHorizontal();
                    listCount = addToList(recordedList, command, listCount);
                    printHorizontal();
                }
            }
            command = input.nextLine();
        }

    }
    public static void printHorizontal () {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {

        printHorizontal();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontal();

        processCommand();
    }
}   
