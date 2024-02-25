import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<Contact> contacts;
    private static Scanner scanner;
    public static int id = 0;
    public static void main(String[] args) {
        /**
         * Simulate the phone messages and contacts application
         *
         * Welcome user
         * Show options: 1 Manage Contacts  2.Messages   3.Quit
         * Incase of selecting options 1 ---> show these options
         *    1. show all contacts
         *    2. Add a new contact
         *    3. Search for a contact
         *    4. Delete a contact
         *    5. Go back to previous menu
         * Incase of selecting 2 ---> show these options
         *    1.See the list of all messages
         *    2 Send a new message
         *    3. Go back to the previous menu
         * Incase of selecting options 3 ---> quit the application
         */

        contacts = new ArrayList<>();
        System.out.println("Hello our beloved user");
        showInitialOptions();
    }
    public static void showInitialOptions(){
        System.out.println("What do you want to do: " +
                "\n\t1. Manage Contacts" +
                "\n\t2. Messages" +
                "\n\t3. Quit");
        scanner = new Scanner(System.in);
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                manageContacts();
                break;
            case 2:
                manageMessages();
                break;
            default:
                break;
        }
    }

    private static void manageMessages() {
        System.out.println("What do you want to do" +
                "\n\t1. Show all messages" +
                "\n\t2. Send a message" +
                "\n\t3. Go Back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllNessages();
                break;
            case 2:
                sendNewMessage();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void sendNewMessage() {
        System.out.println("Who do you want to chat with?");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name of the contact");
            sendNewMessage();
        }
        else{
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("What do you want to say?");
                String text = scanner.next();
                if(text.equals("")){
                    System.out.println("Please enter some message");
                    sendNewMessage();
                }
                else{
                    id++;
                    Message newMessage = new Message(id,text,name);
                    for (Contact c: contacts){
                        if (c.getName().equals(name)){
                            ArrayList<Message> newMessages = c.getMessages();
                            newMessages.add(newMessage);
                            c.setMessages(newMessages);
                        }
                    }
                }
            }
            else {
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void showAllNessages() {
        ArrayList<Message> allMessages = new ArrayList<>();
        for (Contact c: contacts){
            allMessages.addAll(c.getMessages());
        }
        if (allMessages.size()>0){
            for (Message m: allMessages){
                m.getDetails();
                System.out.println("***************");
            }
        }
        else{
            System.out.println("You don't have any message");
        }
        showInitialOptions();
    }

    public static void manageContacts(){
        System.out.println("What do you want to do:" +
                "\n\t1. Show all contacts" +
                "\n\t2. Add a new contact" +
                "\n\t3. Search for a contact" +
                "\n\t4. Delete a contact" +
                "\n\t5. Go Back");
        int choice = scanner.nextInt();
        switch (choice){
            case 1:
                showAllContacts();
                break;
            case 2:
                addNewContact();
                break;
            case 3:
                searchForContact();
                break;
            case 4:
                deleteContact();
                break;
            default:
                showInitialOptions();
                break;
        }
    }

    private static void deleteContact() {
        System.out.println("Please enter the contact name");
        String name = scanner.next();
        if (name.equals("")){
            System.out.println("Please enter the name");
            deleteContact();
        }
        else{
            boolean doesExist = false;
            for(Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    contacts.remove(c);
                    System.out.println("Contact deleted successfully");
                }
            }
            if (!doesExist){
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();
    }

    private static void searchForContact() {
        System.out.println("Please enter the contact name:");
        String name = scanner.next();
        if(name.equals("")){
            System.out.println("Please enter the name:");
            searchForContact();
        }
        else{
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                    c.getDetails();
                }
            }
            if (!doesExist){
                System.out.println("There is no such contact");
            }
        }
        showInitialOptions();

    }

    private static void addNewContact() {
        System.out.println("Adding a new contact...." +
                "\nPlease enter the contact name:");
        String name = scanner.next();
        System.out.println("Please enter the contact's number:");
        String number = scanner.next();
        System.out.println("Please enter the contacts's email:");
        String email = scanner.next();

        if (name.equals("") || number.equals("") || email.equals("")){
            System.out.println("Please fill in all the details!");
            addNewContact();
        }
        else{
            boolean doesExist = false;
            for (Contact c: contacts){
                if (c.getName().equals(name)){
                    doesExist = true;
                }
            }
            if (doesExist){
                System.out.println("We have contact named " + name + " saved on this device");
                addNewContact();
            }
            else{
                Contact contact = new Contact(name,number,email);
                contacts.add(contact);
                System.out.println("Contact " + name + " added successfully");
            }

        }
        showInitialOptions();
    }

    private static void showAllContacts() {
        if (contacts.size()>0){
            for (Contact c: contacts){
                c.getDetails();
                System.out.println("*********************");
            }
            showInitialOptions();
        }
        else{
            System.out.println("You don't have any contact saved yet");
            showInitialOptions();
        }
    }
}