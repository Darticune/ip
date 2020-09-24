package duke.storage;

import duke.ui.Ui;

import duke.tasklist.task.Deadline;
import duke.tasklist.task.Event;
import duke.tasklist.task.Todo;
import duke.tasklist.task.Task;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    //Initialisation from Saved Data
    public Task convertToTask(String record) {
        String[] parsedFields = record.split(" \\| ");
        switch(parsedFields[0]) {
        case "T":
            return new Todo (parsedFields[0], parsedFields[1], parsedFields[2], " ");
        case "D":
            return new Deadline(parsedFields[0], parsedFields[1], parsedFields[2], parsedFields[3]);
        case "E":
            return new Event(parsedFields[0], parsedFields[1], parsedFields[2], parsedFields[3]);
        default:
            Ui.printDivider();
            System.out.println(Ui.INVALID_ENTRY + record);
            Ui.printDivider();
        }
        return null;
    }
    public ArrayList<String> readDataAsStrings() throws FileNotFoundException {
        File dukeFile = new File ("data/Duke.txt");
        ArrayList<String> fileContents = new ArrayList<>();
        Scanner s = new Scanner(dukeFile);
        while (s.hasNext()) {
            fileContents.add(s.nextLine());
        }
        return fileContents;
    }
    public ArrayList<Task> convertDataToTaskList(ArrayList<String> fileContent) {
        ArrayList<Task> recordedList = new ArrayList<>();
        for (String record : fileContent) {
            recordedList.add(convertToTask(record));
        }
        return recordedList;
    }
    public ArrayList<Task> createTaskListFromSavedData() {
        ArrayList<Task> recordedList;
        try {
            ArrayList<String> fileContent = readDataAsStrings();
            recordedList = convertDataToTaskList(fileContent);
        } catch (FileNotFoundException e){
            recordedList = new ArrayList<>();
        }
        return recordedList;
    }

    //Writing to data file
    public boolean directoryExists() {
        File dir = new File("data");
        return (dir.exists() && dir.isDirectory());
    }
    public void createDirectory() {
        File dir = new File ("data");
        dir.mkdir();
    }
    public boolean savedDataFileExists() {
        File savedData = new File("data/Duke.txt");
        return savedData.exists();
    }
    public void createSavedDataFile() {
        File dukeFile = new File ("data/Duke.txt");
        try {
            dukeFile.createNewFile();
        } catch (IOException e) {
            System.out.println(Ui.TEXT_FILE_CREATION_FAILED);
        }
    }
    public void emptyFileContents() throws IOException {
        FileWriter fw = new FileWriter("data/Duke.txt");
        fw.write("");
        fw.close();
    }
    public void writeTask(Task task) throws IOException {
        FileWriter fw = new FileWriter("data/Duke.txt", true);
        fw.write(task.toSaveFormat() + System.lineSeparator());
        fw.close();
    }
    public void writeList(ArrayList<Task> recordedList) {
        try {
            emptyFileContents();
            for (Task task : recordedList) {
                writeTask(task);
            }
        } catch (IOException e) {
            System.out.println(Ui.SAVE_FAILED);
        }
    }
    public void updateSavedData(ArrayList<Task> recordedList) {
        if(!directoryExists()) {
            createDirectory();
        }
        if (!savedDataFileExists()) {
            createSavedDataFile();
        }
        writeList(recordedList);
    }
}
