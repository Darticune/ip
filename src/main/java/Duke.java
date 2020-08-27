import java.util.Scanner;

public class Duke {
    public static int addToList (String[] recordedList, String command, int listCount) {
        recordedList[listCount] = command;
        listCount++;
        System.out.println("added: " + command);
        return listCount;
    }
    public static void printList (String[] recordedList, int listCount) {
        for (int i=0; i<listCount; i++) {
            System.out.print(i+1);
            System.out.println(". " + recordedList[i]);
        }
    }
    public static void bidGoodbye () {
        printHorizontal();
        System.out.println("Bye. Hope to see you again soon!");
        printHorizontal();
    }
    public static void processCommand () {
        String[] recordedList = new String[100];
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
                printHorizontal();
                listCount = addToList(recordedList, command, listCount);
                printHorizontal();
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
