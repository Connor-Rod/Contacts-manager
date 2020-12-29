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

//    public static String getString(String prompt) {
//        System.out.println(prompt);
//        return getString();
//    }

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
            List<Contact> contacts = reader.read(reader.getFilePath());
            List<String> contactNames = new ArrayList<>();
//            for (Contact contact : contacts) {
//                    contactNames.add(contact.getName());
//            }
//            if (contactNames.contains(userInput)) {
//                if (!yesNo("There's already a contact named: "+ userInput + " Do you want to overwrite it? (Yes/No)")) {
//                    nameValidation(reader);
//                } else {
//                    reader.delete(reader.getFilePath(), contactNames.get(contactNames.indexOf(userInput)));
////                        reader.writeContact(reader.getLogFilePath(), Arrays.asList((userInput) + " | " +  phoneValidation(reader)));
//                }
//            }
            return userInput;
        } else {
            return nameValidation(reader);
        }
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
