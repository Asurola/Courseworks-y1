import java.util.*;


// class initialisation for item hierarchy
class Item{
    private String title; // private variables
    private String year;
    private boolean checkedOut;
    //hashset stores unique elements without associated data unlike hashmaps with key-value links. P.S wanted to use a hashset for the inventory system too but the guidelines said it needed to be an arraylist so I left it as is. I wonder if I could have just used a hashset
    //hashset used for checkedOutBy because only one value needed for unique-ness with ID
    private Set<String> checkedOutBy;

    // getters and setters for variables
    public Set<String> getCheckedOutBy() {
        return checkedOutBy;
    }

    public String getTitle() { //getters and setters
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean isCheckedOut() {
        return checkedOut;
    }

    public void setCheckedOut(boolean checkedOut) {
        this.checkedOut = checkedOut;
    }

    // constructor creation and HashSet initialisation
    public Item(String title, String year, boolean checkedOut) { //superclass construction
        this.title = title;
        this.year = year;
        this.checkedOut = checkedOut;
        this.checkedOutBy = new HashSet<>();
    }

    public Item() { //blank/default constructor with HashSet initialisation and assignment with this because code breaks otherwise with a nullpointerexception. Initialisation is here to stop that.
        this.checkedOutBy = new HashSet<>();
    }

    public void addCheckedOutBy(String memberId) {  //method for adding memberID to hashset
        checkedOutBy.add(memberId);
    }

    public void removeCheckedOutBy(String memberId) { //method for removing memberID to hashset
        checkedOutBy.remove(memberId);
    }

}
class Library {//library class with private variables, constructor creation, setters and getters
    private ArrayList<Item> inventory; //inventory arraylist initialisation, I wonder if a using a hashset would have been better?
    private HashMap<String, LibraryMember> members; // Hashmap initialisation for members and their details

    private HashMap<String, Set<String>> reservations; // Hashmap for binding the string (key) and set of strings (value(memberIDs))


    public Library() { // library constructor with arraylist and hashmap initialisation
        this.inventory = new ArrayList<>();
        this.members = new HashMap<>();
        this.reservations = new HashMap<>();
    }

    //getters and setters for arraylist and hashmap
    public HashMap<String, LibraryMember> getMembers() {
        return members;
    }

    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public void addItem(Item item) { //had to capitalise the item class because it felt weird putting item twice :(
        inventory.add(item);
    } //add item method for arraylist

    public void addMember(LibraryMember member) {
        members.put(member.getMemberId(), member);
    } // add member to HashMap

    public String getMemberNameById(String memberId) { // method to get member name based on ID from hashmap made above
        LibraryMember member = members.get(memberId); //gets member ID from hashmap
        return (member != null) ? member.getName() : "Unknown"; //ternary (conditional) operation to return the member's name if present, if not, uses the unknown/null as a placeholder
    }

    public void removeItem(String title) { //removeItem method, for every item, checks correspondence with title and if equal (ignores case), assigns to variable
        Item itemToRemove = null;

        for (Item item : inventory) {
            if (item.getTitle().equalsIgnoreCase(title)) {
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) { //if logic in case item not found
            inventory.remove(itemToRemove); //removes from arraylist if equals
            System.out.println("Item removed: " + title);
        } else {
            System.out.println("Error: Item with title '" + title + "' not found in the library. Please use option 7 to view all available items or use option 10 to add an item."); //returns msg if not
        }
    }

    public void listAvailableItems() { //method to list all available items based on status of boolean checkout. Lists in detail.
        System.out.println("\nAvailable items: ");
        for (Item item : inventory) {
            if (!item.isCheckedOut()) {
                if (item instanceof book) {
                    System.out.println(((book) item).getBookDetails());
                } else if (item instanceof DVD) {
                    System.out.println(((DVD) item).getDVDDetails());
                } else if (item instanceof magazine) {
                    System.out.println(((magazine) item).getMagazineDetails());
                }
            }
        }
    }

    public boolean isReserved(Item item) { //is reserved boolean method for logic for reservation feature
        Set<String> reservationSet = reservations.get(item.getTitle()); //retrieves set for given title from map
        return reservationSet != null && !reservationSet.isEmpty(); //ensures reservation is not empty and there is a reservation
    }

    public void reserveItem(Item item, String memberId) { //ADDITIONAL feature, reserveItem method and if logic for validation
        if (item != null && memberId != null && members.containsKey(memberId)) { //checks if item and memberId are valid
            if (item.isCheckedOut()) {
                String checkedOutBy = item.getCheckedOutBy().iterator().next(); // get the first member ID in the set
                if (checkedOutBy.equals(memberId)) {
                    // check if the item is already reserved by the member
                    if (isReserved(item)) {
                        System.out.println("Error: You have already reserved this item. Use option 6 to view all reservations");
                    } else {
                        // Add the reservation
                        //checks for existence of an entry from given title, if so returns existing set<String> of reservations, if not,
                        // it computes a new value using lambda expression (k) which creates a new HashSet <String> when a new entry is needed
                        reservations.computeIfAbsent(item.getTitle(), k -> new HashSet<>()).add(memberId);
                        System.out.println("Item reserved successfully!");
                    }
                } else {
                    System.out.println("Error: This item is currently checked out by another member. Use option 8 to view all checked out items."); //if logic for error validation
                }
            } else {
                // item is not checked out, proceed with reservation
                // check if the item is already reserved by the member
                if (isReserved(item)) {
                    System.out.println("Error: You have already reserved this item.");
                } else {
                    // add the reservation
                    reservations.computeIfAbsent(item.getTitle(), k -> new HashSet<>()).add(memberId);
                    System.out.println("Item reserved successfully!");
                }
            }
        } else {
            System.out.println("Error: Invalid item or member ID.");  //error handling for invalid entry
        }
    }

    public void cancelReservation (Item item, String memberID){ //cancel reservation method for reservation feature
        if (item != null){
            String title = item.getTitle();
            Set<String> reservationSet = reservations.get(title); //retrieves set for given title from map
            if (reservationSet != null) {
                reservationSet.remove(memberID); // if exists reservation, removes memberID from reservation set. Therefore, cancelling reservation.
                System.out.println("Reservation canceled for item '" + title + "' by member: " + memberID);
            } else {
                System.out.println("Error: No reservation found for item '" + title + "' by member " + memberID + ". Please Use option 6 to list all reservations.");
            }
        } else {
            System.out.println("Error: The item was not found. Please use option 7 to view available items");
        }
    }

    public void listReservations (){ // method to list reservations
        System.out.println("\nReservations: ");
        if (!reservations.isEmpty()){ //checks for existence of reservations
            for (Map.Entry<String, Set<String>> entry : reservations.entrySet()){ //iterates over entries in reservation set
                String title = entry.getKey();
                Set<String> memberIds = entry.getValue(); //gets set of memberIDs with reservations
                System.out.println("Item " + title + " reserved by members: " + memberIds);
            }
        } else {
            System.out.println("No reservations. Please use option 4 to place a reservation.");
        }
    }

    public void searchItemsByTitle(String title) { //method to search for items by title
        boolean itemFound = false; //boolean logic variable to return error if item not found
        for (Item item : inventory) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) { //should swap to equalsIgnoreCase, is easier
                itemFound = true; //sets to true to avoid displaying the final message even if an item is found
                //if logic to make use of each details function for the respective item by checking item type first.
                if (item instanceof book) {
                    if (item.isCheckedOut()) {
                        System.out.println((((book) item).getBookDetails()) + ", Checked out by: " + item.getCheckedOutBy());
                    } else {
                        System.out.println((((book) item).getBookDetails()));
                    }
                } else if (item instanceof DVD) {
                    if (item.isCheckedOut()) {
                        System.out.println((((DVD) item).getDVDDetails()) + ", Checked out by: " + item.getCheckedOutBy());
                    } else {
                        System.out.println((((DVD) item).getDVDDetails()));
                    }
                } else if (item instanceof magazine) {
                    if (item.isCheckedOut()) {
                        System.out.println((((magazine) item).getMagazineDetails()) + ", Checked out by: " + item.getCheckedOutBy());
                    } else {
                        System.out.println((((magazine) item).getMagazineDetails()));
                    }
                }
            }
        }
        if (!itemFound) { //if logic and error if item not found
            System.out.println("No item found");
        }
    }


    public Item searchItemsForCheckOut(String title) { //search items for checkout method
        for (Item item : inventory) {
            if (item.getTitle().toLowerCase().contains(title.toLowerCase())) { //should swap to equalsIgnoreCase. easier and it just checks for every instance of item in inventory and returns it
                return item;
            }
        }
        return null; //else error handling
    }

    public void printLibraryMembers() { //print method for all existing library methods
        System.out.println("\nLibrary Members:");
        if (members.isEmpty()) { //validation to print message if no members exist such as when the code is first run
            System.out.println("No members in the library.");
        } else {
            for (Map.Entry<String, LibraryMember> entry : members.entrySet()) { //scans map for memberIDs and registered string names
                String memberId = entry.getKey(); //assignment of memberID and member with entry map
                LibraryMember member = entry.getValue();
                System.out.println("Member ID: " + memberId);
                System.out.println("Member Name: " + member.getName());
                System.out.println("---------------------");
            }
        }
    }

    public void emptyLibrary() { //method to print message to prompt user to add an item when inventory is empty. i.e. when starting program or after removing all existing items
        if (getInventory().isEmpty()) {
            System.out.println("The library is currently empty. Consider adding items to the inventory using option 10.");
        }
    }
}



