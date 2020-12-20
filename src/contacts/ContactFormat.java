package contacts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ContactFormat {

    private static ArrayList<String> menuOptions = new ArrayList<>(
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

    public static StringBuilder allContactFormat(List<String> contactList){
        StringBuilder contacts = new StringBuilder();
        contacts.append("Name  |  Phone Number|").append("\n");
        for (var i =0; i<contactList.size(); i++) {
            contacts.append(contactList.get(i)).append("\n");
        }
        return contacts;
    }

    public ArrayList<String> getMenuOptions() {
        return menuOptions;
    }

    public void setMenuOptions(ArrayList<String> menuOptions) {
        this.menuOptions = menuOptions;
    }
}



