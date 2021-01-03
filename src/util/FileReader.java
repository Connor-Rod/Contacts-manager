package util;

import contacts.Contact;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReader {


    private String fileName;
    private String directoryName;
    private String logFileName;
    private Path filePath;
    private Path directoryPath;
    private List<String> fileLines;
    private Path logFilePath;
    private final List<Contact> contactList = new ArrayList<>();

    //constructor
    public FileReader(String fileName, String directoryName, String logFileName) throws IOException {
        this.fileName = fileName;
        this.directoryName = directoryName;
        this.logFileName = logFileName;
        this.filePath = Paths.get(directoryName, fileName);
        this.directoryPath = Paths.get(directoryName);
        this.logFilePath = Paths.get(directoryName, logFileName);
        create(this.logFilePath);
        create(this.filePath);
    }

    //method to create file
    public void create(Path path) throws IOException {
        if (Files.notExists(path)) {
            try {
            Files.createFile(path);
            } catch (IOException e) {
                // if logfile exists,write to it and throw exception, else just throw exception
                if (Files.exists(this.logFilePath)) {Files.write(path, Arrays.asList(e.getMessage()), StandardOpenOption.APPEND);}
                throw new IOException("failed to create file");
            }
        }
    }

    //method to read file
    public List<String> read(Path filePath) throws IOException {
        return Files.readAllLines(filePath);
//        for (String contact: contacts) {
//            contactList.add(new Contact(contact.substring(0, contact.indexOf("|")).trim(),contact.substring(contact.indexOf("|")).trim()));
//        }
//        return contactList;
    }

    public void writeContact(Path filePath, List<String> contact) throws IOException {
        Files.write(filePath, contact, StandardOpenOption.APPEND);
    }

    public void writeError(Path logFilePath, List<String> list) throws IOException {
        Files.write(logFilePath, list, StandardOpenOption.APPEND);
    }

    public void delete(Path filePath, String name) throws IOException {
       List<String> deleteList = this.read(filePath);
        System.out.println(deleteList);
        List<String> newList = new ArrayList<>();
       for (String contact: deleteList) {
           String trim = contact.substring(0, contact.indexOf("|")).trim();
           if (trim.equalsIgnoreCase(name)) {
               continue;
           }
           newList.add(String.format("%-15s | %-20s |", trim, contact.substring(contact.indexOf("|"))));
       }
       if (newList.size() > 0) {
            Files.write(filePath, Arrays.asList(newList.get(0)),StandardOpenOption.TRUNCATE_EXISTING);
            for(var i = 1; i < newList.size(); i++) {
               Files.write(filePath, Arrays.asList(newList.get(i)), StandardOpenOption.APPEND );
            }
       }
       else Files.write(filePath, Arrays.asList(""),StandardOpenOption.TRUNCATE_EXISTING);
    }

    //getters and setters
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public void setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(Path filePath) {
        this.filePath = filePath;
    }

    public List<String> getFileLines() {
        return fileLines;
    }

    public void setFileLines(List<String> fileLines) {
        this.fileLines = fileLines;
    }

    public Path getLogFilePath() {
        return logFilePath;
    }

    public void setLogFilePath(Path logFilePath) {
        this.logFilePath = logFilePath;
    }

    public Path getDirectoryPath() {
        return directoryPath;
    }

    public void setDirectoryPath(Path directoryPath) {
        this.directoryPath = directoryPath;
    }
}
