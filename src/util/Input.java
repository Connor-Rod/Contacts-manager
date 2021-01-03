package util;

import contacts.Contact;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class Input {

    final Scanner sc = new Scanner(System.in);
    private String userInput;

    public Input() throws IOException {
    }

    public String getString() {
        return sc.nextLine();
    }

    public boolean yesNo(String prompt) {
        System.out.printf("%s", prompt);
        String userInput = getString().toLowerCase();
        return (userInput.equals("y") | userInput.equals("yes"));
    }

    public boolean yesNo() {
        return yesNo("\nWould you like to continue?");
    }

    public String phoneValidation(FileReader reader) throws IOException {
        System.out.println("enter a phone number");
        userInput = sc.nextLine().trim();
        if ((userInput.length() == 8 | userInput.length() == 12) | (userInput.length() == 7 | userInput.length() == 10))  {
           try {
               if (userInput.contains("-")) {
                  userInput = userInput.replaceAll("-", "");
               }
               Long.parseLong(userInput);
               if (getUserInput().length() == 7) {
                   return userInput.substring(0,3) + "-" + userInput.substring(3);
               }
               else {
                   return userInput.substring(0,3) + "-" + userInput.substring(3,6) + "-" + userInput.substring(6);
               }
           } catch (Exception e) {
               for (StackTraceElement errorLine : e.getStackTrace() ) {
                   reader.writeError(reader.getLogFilePath(), (Collections.singletonList(errorLine.toString())));
                return phoneValidation(reader);
               }
            }
        }
        System.out.println("not a phone #");
        return phoneValidation(reader);
    }
    
    public String nameValidation (FileReader reader) throws IOException {
        System.out.println("enter a first and last name.");
        userInput = sc.nextLine().trim();
        if (userInput.contains("\s")) {
            List<String> contacts = reader.read(reader.getFilePath());
            List<String> contactNames = new ArrayList<>();
            return userInput;
        } else {
            return nameValidation(reader);
        }
    }

    public List<String> searchName (Path file, FileReader reader) throws IOException {
        System.out.println("search a name.");
        List<String> searched = new ArrayList<>();
        String userSearch = sc.nextLine().trim();
       List<String> contactList = reader.read(file);
       for(String contact: contactList) {
           String trim = contact.substring(0, contact.indexOf("|")).trim();
           String first = trim.substring(0, contact.indexOf(" ")).trim();
           String last = trim.substring(contact.indexOf(" ")).trim();
           if (trim.equalsIgnoreCase(userSearch)) {
               searched.add(contact);
           } else if ((first.equalsIgnoreCase(userSearch))) {
               searched.add(contact);
           } else if (last.equalsIgnoreCase(userSearch)) {
               searched.add(contact);
           }
       }
       return searched;
    }

    public void addContact(Contact contact, FileReader testReader) throws IOException {
        String contactFormatting = String.format("%-15s | %20s |", contact.getName(), contact.getPhone());
        testReader.writeContact(testReader.getFilePath(), Arrays.asList(contactFormatting));
        System.out.printf("Contact: %s successfully added with phone number: %s.", contact.getName(), contact.getPhone());
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
