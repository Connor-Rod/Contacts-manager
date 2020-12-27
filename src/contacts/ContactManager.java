package contacts;

import util.FileReader;
import util.Input;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

public class ContactManager {

    public static void main(String[] args) throws IOException {
        FileReader testReader = new FileReader("contacts.txt", "data", "error.log");
        Path contactFile = testReader.getFilePath();
//        testReader.write(contactFile, Arrays.asList("\n" + "Sam Sampson" + "|" + "1231234"));
//        System.out.println(testReader.read(contactFile));
        Input input = new Input();

        System.out.println(ContactFormat.menuFormatter());
        String menuSelection = input.getString();
        switch (menuSelection) {
            case "1" -> System.out.println(ContactFormat.allContactFormat(testReader.read(contactFile)));
            case "2" -> {
                Contact contact = new Contact(input.nameValidation(), input.phoneValidation(testReader));
                testReader.writeContact(testReader.getFilePath(), Arrays.asList(contact.getName() + " | " + contact.getPhone()));
            }
            case "3" -> System.out.println(ContactFormat.allContactFormat(input.searchName(testReader.getFilePath(), testReader)));
            case "4" -> {
                System.out.println("enter full name.");
                String userChoice = input.getString();
                testReader.delete(contactFile, userChoice);
            }

        }
//        input.phoneValidation(menuSelection);
    }
}