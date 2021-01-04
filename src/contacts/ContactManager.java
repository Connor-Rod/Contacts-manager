package contacts;

import util.FileReader;
import util.Input;

import java.io.IOException;
import java.nio.file.Path;

public class ContactManager {

    public static void init(FileReader file, Path path) throws IOException {
        Input input = new Input();
            System.out.println(ContactFormat.menuFormatter());
            String menuSelection = input.getString();
            switch (menuSelection) {
                case "1" -> System.out.println(ContactFormat.allContactFormat(file.read(path)));
                case "2" -> {
                    Contact contact = new Contact(input.nameValidation(), input.phoneValidation(file));
                    input.addContact(contact, file, path);
                }
                case "3" -> System.out.println(ContactFormat.allContactFormat(input.searchName(file.getFilePath(), file)));
                case "4" -> {
                    System.out.println("enter full name.");
                    String userChoice = input.getString();
                    file.delete(path, userChoice);
                }
                case "5" -> {
                    System.out.println("Thank you for using Contact Manager! See you again.");
                    System.exit(0);
                }
            }
         if(input.yesNo()) {
             init(file, path);
         }
    }

    public static void main(String[] args) throws IOException {
        FileReader testReader = new FileReader("contacts.txt", "data", "error.log");
        Path contactFile = testReader.getFilePath();
        init(testReader, contactFile);
    }
}