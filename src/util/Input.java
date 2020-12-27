package util;

import contacts.Contact;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Input {

    final Scanner sc = new Scanner(System.in);
    private String userInput;

    public Input() throws IOException {
    }

    public String getString() {
        return sc.nextLine();
    }

//    public static String getString(String prompt) {
//        System.out.println(prompt);
//        return getString();
//    }

//    public boolean yesNo(String prompt) {
//        System.out.printf("%s", prompt);
//        String userInput = getString().toLowerCase();
//        return (userInput.equals("y") | userInput.equals("yes"));
//    }

    public String phoneValidation(FileReader reader) throws IOException {
        System.out.println("enter a phone number");
        userInput = sc.nextLine().trim();
        if (userInput.length() == 7 | userInput.length() == 10) {
           try {
               Long.parseLong(userInput);
               System.out.println(userInput);
               return userInput;
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
    
    public String nameValidation () {
        System.out.println("enter a first and last name.");
        userInput = sc.nextLine().trim();
        if (userInput.contains("\s")) {
            return userInput;
        }
        return nameValidation();
    }

    public List<Contact> searchName (Path file, FileReader reader) throws IOException {
        System.out.println("search a name.");
        List<Contact> searched = new ArrayList<>();
        String userSearch = sc.nextLine().trim();
       List<Contact> contactList = reader.read(file);
       for(Contact contact: contactList) {
           if ((contact.getName().equalsIgnoreCase(userSearch))) {
               searched.add(contact);
           } else if ((contact.getName().substring(0, contact.getName().indexOf(" ")).equalsIgnoreCase(userSearch))) {
               searched.add(contact);
           } else if ((contact.getName().substring(contact.getName().indexOf(" ")).trim().equalsIgnoreCase(userSearch))) {
               searched.add(contact);
           }
       }
       return searched;
    }

    public boolean moveOn(String prompt) {
        System.out.printf("%s", prompt);

        return (userInput.equals("y") | userInput.equals("yes"));
    }

    public Scanner getSc() {
        return sc;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
}
