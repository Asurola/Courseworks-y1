import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManagementSystem { //main method class

    public static void main(String[] args) { //object initialisation with main classes
        Library library = new Library();
        LibraryMember lm = new LibraryMember();
        lm.setLibrary(library); //shorter assignment of object to class setter.
        Scanner scanner = new Scanner(System.in);

        library.emptyLibrary(); //initiates with empty library method to prompt user to add an item.

        int choice; //variable for loop

        do { //menu comment string
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Register as a New Library Member");
            System.out.println("2. Check Out an Item");
            System.out.println("3. Return an Item");
            System.out.println("4. Reserve a title");
            System.out.println("5. Cancel a reservation");
            System.out.println("6. List reservations");
            System.out.println("7. List Available Items");
            System.out.println("8. List Checked-Out Items");
            System.out.println("9. Search for Items by Title");
            System.out.println("10. Add an item to stock");
            System.out.println("11. Remove an item from stock: ");
            System.out.println("12. Print all the existing members");
            System.out.println("13. Exit");

            //while loop for taking input for menu.
            while (true) {
                System.out.print("Enter your choice (1-13): ");

                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else { //validation
                    System.out.println("Invalid input. Please enter a number between 1 and 13.");
                    scanner.nextLine();  // consume the invalid input
                }
            }
            //Switch statement for menu options
            switch (choice) {
                case 1:
                    // register as a new library member
                    lm.registerNewMember(scanner);
                    library.addMember(new LibraryMember(lm.getMemberId(), lm.getName(), library));
                    break;
                case 2:
                    // check Out an Item
                    System.out.println("\nEnter the title you want to check out: ");
                    String titleForCheckout = scanner.nextLine();
                    System.out.println("Please enter your memberID: ");
                    String memberIDforCheckOut = scanner.nextLine();
                    lm.checkOutItem(library.searchItemsForCheckOut(titleForCheckout), memberIDforCheckOut);
                    break;
                case 3:
                    // return an item
                    System.out.println("\nEnter the title you want to return: ");
                    String titleForReturn = scanner.nextLine();
                    System.out.println("Please enter your memberID: ");
                    String memberIDforReturn = scanner.nextLine();
                    lm.returnItem(library.searchItemsForCheckOut(titleForReturn), memberIDforReturn, library);
                    break;
                case 4:
                    // reserve an item
                    System.out.println("\nEnter the title you want to reserve: ");
                    String titleForReservation = scanner.nextLine();
                    System.out.println("Please enter you're memberID: ");
                    String memberIDforReservation = scanner.nextLine();
                    library.reserveItem(library.searchItemsForCheckOut(titleForReservation), memberIDforReservation);
                    break;
                case 5:
                    // cancel a reservation
                    System.out.println("\nEnter the title you want to cancel the reservation for: ");
                    String titleForCancel = scanner.nextLine();
                    System.out.println("Please enter you're memberID: ");
                    String memberIDforCancel = scanner.nextLine();
                    library.cancelReservation(library.searchItemsForCheckOut(titleForCancel), memberIDforCancel);
                    break;
                case 6:
                    //view all reservations
                    library.listReservations();
                    break;
                case 7:
                    // list available items
                    library.listAvailableItems();
                    library.emptyLibrary(); //does an if logic check and prints if empty.
                    break;
                case 8:
                    // list checked-out items
                    lm.listCheckedOutItems();
                    break;
                case 9:
                    // search for items by title
                    System.out.println("\nPlease enter the title of the item you want to search for: ");
                    String title = scanner.nextLine();
                    library.searchItemsByTitle(title);
                    break;
                case 10:
                    // add item function with do loop
                    int type;
                    do {
                        System.out.println("""
                                \nChoose what type of item you would like to add: 
                                1. Book
                                2. DVD
                                3. Magazine
                                4. Exit
                                """);
                        while (true) {
                            System.out.println("Enter your choice (1-4): ");
                            if (scanner.hasNextInt()) {
                                type = scanner.nextInt();
                                scanner.nextLine(); // consume the newline
                                break;
                            } else {
                                System.out.println("Invalid input. Please enter a number between 1 and 4.");
                                scanner.nextLine(); // consume the invalid input
                            }
                        }
                        switch (type) { //switch case for add book based on item.
                            case 1:
                                book addBook = new book();
                                addBook.input(scanner);
                                library.addItem(addBook);
                                break;
                            case 2:
                                DVD addDVD = new DVD();
                                addDVD.input(scanner);
                                library.addItem(addDVD);
                                break;
                            case 3:
                                magazine addMagazine = new magazine();
                                addMagazine.input(scanner);
                                library.addItem(addMagazine);
                                break;
                            case 4:
                                break;
                        }
                        break;
                    } while (type != 4); //ensures the loop is maintained until 4
                    break;
                case 11:
                    // remove an item
                    System.out.println("\nEnter the title of the item u want to remove: ");
                    String titleInput = scanner.nextLine();
                    library.removeItem(titleInput);
                    break;
                case 12:
                    //prints all library members
                    library.printLibraryMembers();
                    break;
                case 13:
                    //Exit method clause
                    System.out.println("Exiting the Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 10.");
                    break;
            }
        } while (choice != 13); //ensures the loop is maintained until 13 is inputted
    }
}




