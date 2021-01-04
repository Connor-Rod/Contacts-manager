package util;

import contacts.Contact;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Input {

    final Scanner sc = new Scanner(System.in);
    private String userInput;

    public Input() {
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
    
    public String nameValidation () {
        System.out.println("enter a first and last name.");
        userInput = sc.nextLine().trim();
        if (userInput.contains("\s")) {
            return userInput;
        } else {
            return nameValidation();
        }
    }

    public List<String> searchName (Path file, FileReader reader) {
        try {
            System.out.println("search a name.");
            List<String> searched = new ArrayList<>();
            String userSearch = sc.nextLine().trim();
            List<String> contactList = reader.read(file);
            for (String contact : contactList) {
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
        } catch (Exception e) {
            System.out.println("There was a problem with searching that name.");
            return searchName(file, reader);
        }
    }

    public List<String> searchName (Path file, FileReader reader, String name) throws IOException {
        List<String> searched = new ArrayList<>();
        List<String> contactList = reader.read(file);
        for(String contact: contactList) {
            String trim = contact.substring(0, contact.indexOf("|")).trim();
            if (trim.equalsIgnoreCase(name)) {
                searched.add(trim);
            }
        }
        return searched;
    }

    public void addContact(Contact contact, FileReader testReader, Path file) throws IOException {
        String contactFormatting = String.format("%-15s | %20s |", contact.getName(), contact.getPhone());
        if(searchName(file, testReader, contact.getName()).contains(contact.getName())) {
            System.out.println("There is already a contact by the name of " + contact.getName());
            System.out.println("Would you like to override this contact?");
            String userOverride = this.sc.nextLine();
            if (userOverride.equalsIgnoreCase("y") || userOverride.equalsIgnoreCase("yes")) {
                testReader.delete(testReader.getFilePath(), searchName(file, testReader, contact.getName()).get(0));
                testReader.writeContact(testReader.getFilePath(), Arrays.asList(contactFormatting));
            } else {
                addContact(contact, testReader, file);
            }
        } else {
            testReader.writeContact(testReader.getFilePath(), Arrays.asList(contactFormatting));
        }
        System.out.printf("Contact: %s successfully added with phone number: %s.", contact.getName(), contact.getPhone());
    }

    public String getUserInput() {
        return userInput;
    }

}
