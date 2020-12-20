package util;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Input {

    final Scanner sc = new Scanner(System.in);
    private String userInput;

    public Input() throws IOException {
    }

    public String getString() {
        return sc.nextLine();
    }

    public FileReader testReader = new FileReader("contacts.txt", "data", "error.log");


//    public static String getString(String prompt) {
//        System.out.println(prompt);
//        return getString();
//    }

//    public boolean yesNo(String prompt) {
//        System.out.printf("%s", prompt);
//        String userInput = getString().toLowerCase();
//        return (userInput.equals("y") | userInput.equals("yes"));
//    }

    public void phoneValidation(String phoneNumber) throws IOException {
        if (phoneNumber.length() == 7 | phoneNumber.length() == 10) {
           try {
               Integer.parseInt(phoneNumber);
               System.out.println(phoneNumber);
               return;
           } catch (Exception e) {
               e.printStackTrace();
               System.out.println();
               for (StackTraceElement errorLine : e.getStackTrace() ) {
                   testReader.writeError(testReader.getLogFilePath(), (Collections.singletonList(errorLine.toString())));
               }
                return;
            }
        }
        System.out.println("Wrong phone #");
    }

    public boolean nameValidation(String prompt) {
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
