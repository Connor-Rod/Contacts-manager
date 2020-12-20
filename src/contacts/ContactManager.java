package contacts;

import util.FileReader;
import util.Input;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class ContactManager {

    private String userInput;

    public static void main(String[] args) throws IOException {
        FileReader testReader = new FileReader("contacts.txt", "data", "error.log");
        Path contactFile = testReader.getFilePath();
//        testReader.write(contactFile, Arrays.asList("\n" + "Sam Sampson" + "|" + "1231234"));
//        System.out.println(testReader.read(contactFile));
        Input input = new Input();
        ContactFormat menu1 = new ContactFormat();

        System.out.println(ContactFormat.menuFormatter());
        String menuSelection = input.getString();
        input.phoneValidation(menuSelection);
    }
}