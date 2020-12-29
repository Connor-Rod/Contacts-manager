package contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ContactFormat {

    private static final ArrayList<String> menuOptions = new ArrayList<>(
            Arrays.asList(
                    "View contacts.",
                    "Add a new contact.",
                    "Search a contact by name.",
                    "Delete an existing contact.",
                    "Exit."
            )
    );


    public static StringBuilder menuFormatter() {
        StringBuilder menu = new StringBuilder();
        for (var i =0; i<menuOptions.size(); i++) {
            menu.append(i+1).append(". ").append(menuOptions.get(i)).append("\n");
        }
        menu.append("Enter an option (1, 2, 3, 4 or 5):");
        return menu;
    }

    public static StringBuilder allContactFormat(List<Contact> contactList){
        StringBuilder contacts = new StringBuilder();
        contacts.append("Name  |  Phone Number|").append("\n");
        for (Contact contact : contactList) {
            contacts.append(contact.getName()).append(" ").append(contact.getPhone()).append("\n");
        }
        return contacts;
    }

}


