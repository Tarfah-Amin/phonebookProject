

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Phonebook phonebook = new Phonebook();

        int choice = 0, searchChoice = 0;
        String contactName, eventTitle ;
        

        System.out.println("Welcome to the BST Phonebook!");

        
        while (choice != 8) {
            System.out.println("Please choose an option:");
            System.out.println("1. Add a contact");
            System.out.println("2. Search for a contact");
            System.out.println("3. Delete a contact");
            System.out.println("4. Schedule an event");
            System.out.println("5. Print event details");
            System.out.println("6. Print contacts by first name");
            System.out.println("7. Print all events alphabetically");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    // Add a contact
                    System.out.print("Enter the contact's name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the contact's phone number: ");
                    String phoneNumber = scanner.nextLine();
                    System.out.print("Enter the contact's email address: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter the contact's address: ");
                    String address = scanner.nextLine();
                    System.out.print("Enter the contact's birthday (DD/MM/YYYY): ");
                    String birthday = scanner.nextLine();
                    System.out.print("Enter any notes for the contact: ");
                    String notes = scanner.nextLine();

                    Contact contact = new Contact(name, phoneNumber, email, address, birthday, notes);
                    phonebook.addContact(contact);
                    System.out.println("Contact added successfully!");
                    break;

                case 2:
                    // Search for a contact
                    System.out.println("Enter search criteria:");
                    System.out.println("1. Name");
                    System.out.println("2. Phone Number");
                    System.out.println("3. Email Address");
                    System.out.println("4. Address");
                    System.out.println("5. Birthday");

                    System.out.print("Enter your choice: ");
                    searchChoice = scanner.nextInt();
                    scanner.nextLine();

                    // Perform the search based on the chosen criteria
                    LinkedList<Contact> searchResults =null;
                    Contact searchObj =null;
                    
                    switch (searchChoice) {
                        case 1:
                            System.out.println("Enter the contact's name: ");
                            contactName = scanner.nextLine();
                            searchObj = phonebook.searchName(contactName);
                            if(searchObj == null)
                                System.out.println("Can't find the contact name :(please enter it agin and make sure all case is matched");
                            else
                                System.out.println(searchObj.toString());
                            break;
                        case 2:
                            System.out.println("Enter the contact's phone number: ");
                            String contactPhone = scanner.nextLine();
                            searchObj = phonebook.SearchPhone(contactPhone);
                            System.out.println(searchObj.toString());
                            break;
                        case 3: 
                            System.out.println("Enter the contact's Email address: ");
                            String contactEmail = scanner.nextLine();
                            searchResults = phonebook.SearchEmail(contactEmail);
                            //System.out.println(searchResults.toString());
                            break;
                        case 4: 
                            System.out.println("Enter the contact's Address: ");
                            String contactAddress = scanner.nextLine();
                            searchResults = phonebook.searchAddress(contactAddress);
                            //System.out.println(searchResults.toString());
                            break;
                        case 5: 
                            System.out.println("Enter the contact's date of birth: ");
                            String contactDOB = scanner.nextLine();
                            searchResults = phonebook.SearchDOB(contactDOB);
                            //System.out.println(searchResults.toString());

                            break;
                        default:
                            System.out.println("Invalid search criteria.");
                            break;
                    }

                    if (searchObj == null && ( searchResults==null || searchResults.isEmpty())) {
                        System.out.println("Contact not found.");
                    } else if(searchResults !=null && !searchResults.isEmpty()) {
                        phonebook.printLinkedListofallcontacts(searchResults);
                    }
                    break;

                case 3://Run not work:not deleting the contact just the related event:( ------------------------------------------------------------------------------------------
                    System.out.print("Enter the contact's name to delete: ");
                    String contactDelete = scanner.nextLine();
                    phonebook.deleteContact(contactDelete);
                    break;
                    
                case 4:
                    System.out.println("Enter type");
                    System.out.println("1.event\n2.appointment");
                    System.out.print("Enter your choice:");
                    int typeChoice = scanner.nextInt();
                    scanner.nextLine();//to clean
                    System.out.println("");
                    
                    String type ="event";
                    boolean isEvent = true;
                    
                    if(typeChoice == 2){
                        type = "appointment";
                        isEvent =false;
                    }
                    
                    
                    System.out.print("Enter "+type+" title: ");
                    eventTitle = scanner.nextLine();
                       
                        
                    System.out.print("Enter "+type+" location: ");
                    String eventLocation = scanner.nextLine();

                    System.out.print("Enter "+type+" date in this form (DD/MM/YYYY): ");
                    LocalDate eventDate = LocalDate.parse(scanner.nextLine() , DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    
                    System.out.print("Enter "+type+" start time in this form (HH:mm): ");
                    LocalTime eventStart = LocalTime.parse(scanner.nextLine() , DateTimeFormatter.ofPattern("HH:mm"));

                    
                    System.out.print("Enter "+type+" end time in this form (HH:mm): ");
                    LocalTime eventEnd = LocalTime.parse(scanner.nextLine() , DateTimeFormatter.ofPattern("HH:mm"));


                    System.out.print("Enter contact name: ");
                    contactName = scanner.nextLine();
                    
                    Event e1 = new Event(eventTitle, eventLocation, eventDate, eventStart, eventEnd, contactName ,isEvent );
                    phonebook.scheduleEvent(e1,contactName);
                    break;

                case 5:
                    System.out.println("Enter search criteria: ");
                    System.out.println("1. Contact name");
                    System.out.println("2. Event tittle");
                    int criteria = scanner.nextInt();
                    scanner.nextLine();//to clean the scanner buffur
                    
                    switch(criteria){
                        case 1:
                            System.out.println("Enter the contact's name:");
                            contactName = scanner.nextLine();
                            if(phonebook.searchName(contactName) == null){
                                System.out.println("Contact not found! ");
                                break;}
                            System.out.println("Contact Found!");
                            //check if event found[ you use a wrong method]
                            phonebook.printLinkedListOfEvents(phonebook.searchName(contactName).getRelatedEvent()); 
                            break;

                        case 2:
                            System.out.println("Enter the Event's title: ");
                            eventTitle = scanner.nextLine();
                            if(phonebook.searchEventTitle(eventTitle) == null){//-----check before add take contact  
                                System.out.println("Event not found! ");
                                break;}  
                            System.out.println("Event Found!");
                            System.out.println(phonebook.searchEventTitle(eventTitle).toString()); 
                            break;

                    }
                break;
                    

                case 6:
                    // Print contacts by first name
                    System.out.print("Enter the first name: ");
                    String firstName = scanner.next();
                    LinkedList<Contact> contactsByFirstName =  phonebook.SearchFname(firstName);
                    phonebook.printLinkedListofallcontacts(contactsByFirstName);

                    if (contactsByFirstName == null && contactsByFirstName.isEmpty()) {
                        System.out.println("No contacts found with the given first name.");
                    } 
                    break;

                    

                case 7://Run not work:not printing all so there is a problem most prpably in add sorted?----------------------------------------------------------------------------------
                    phonebook.printLinkedListOfEvents(phonebook.EventList);
                    break;

                case 8:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Please enter a valid option (1-8).");
                    break;
            }
        }

        
        scanner.close();
    }
} 





    