import java.util.Scanner;

public class Duke {
    public static void processCommand () {
        Scanner input = new Scanner(System.in);
        String command;
        command = input.nextLine();

        switch(command) {
        case "bye":
            printHorizontal();
            System.out.println("Bye. Hope to see you again soon!");
            printHorizontal();
            break;
        default:
            printHorizontal();
            System.out.println(command);
            printHorizontal();
            processCommand();
        }

    }
    public static void printHorizontal () {
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        printHorizontal();

        printHorizontal();
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        printHorizontal();

        processCommand();
    }
}   
