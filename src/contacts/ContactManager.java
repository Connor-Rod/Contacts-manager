package contacts;

import util.FileReader;
import util.Input;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ContactManager {

    public static void main(String[] args) throws IOException {
        FileReader testReader = new FileReader("contacts.txt", "data", "error.log");
        Path contactFile = testReader.getFilePath();
//        testReader.write(contactFile, Arrays.asList("\n" + "Sam Sampson" + "|" + "1231234"));
//        System.out.println(testReader.read(contactFile));
        Input input = new Input();
        do {
            System.out.println(ContactFormat.menuFormatter());
            String menuSelection = input.getString();
            switch (menuSelection) {
                case "1" -> System.out.println(ContactFormat.allContactFormat(testReader.read(contactFile)));
                case "2" -> {
                    Contact contact = new Contact(input.nameValidation(testReader), input.phoneValidation(testReader));
                    input.addContact(contact, testReader);
                    }
                case "3" -> System.out.println(ContactFormat.allContactFormat(input.searchName(testReader.getFilePath(), testReader)));
                case "4" -> {
                    System.out.println("enter full name.");
                    String userChoice = input.getString();
                    testReader.delete(contactFile, userChoice);
                }
                case "5" -> {
                    System.out.println("Thank you for using Contact Manager! See you again.");
                    System.exit(0);
                }
            }
        } while(input.yesNo());
//        input.phoneValidation(menuSelection);
    }
}