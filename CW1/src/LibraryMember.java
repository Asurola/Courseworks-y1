import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

class LibraryMember { //class library member

    //private variables and arraylist initialisation
    private String memberId;
    private String name;
    private ArrayList<Item> checkedOutItems = new ArrayList<>();
    private Library library;

    //constructor initialisation
    public LibraryMember(String memberId, String name, Library library) {
        this.memberId = memberId;
        this.name = name;
        this.library = library;
    }

    //default constructor
    public LibraryMember (){
    }

    //getters and setters
    public void setLibrary (Library library){
        this.library = library;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Item> getCheckedOutItems() {
        return checkedOutItems;
    }

    //method to register a new member taking user input as name and generating a random ID for user.
    public void registerNewMember(Scanner scanner) {
        Random random = new Random();
        //takes user input for name
        System.out.println("Enter member name: ");
        setName(scanner.nextLine());

        //randomly generates an ID for user and uses setter to store and assign it.
        int randomID = random.nextInt(100000);
        setMemberId(String.format("%03d", randomID));
        System.out.println("Your member ID is: " + getMemberId());
        System.out.println("Member registered successfully!");
    }

    //add item method for arraylist
    public void addItem(Item item) {
        checkedOutItems.add(item);
    }

    //remove item method for arraylist
    public void removeItem (Item item){
        checkedOutItems.remove(item);
    }

    //check out item method
    public void checkOutItem(Item item, String memberId) {
        if (library != null && library.getMembers().containsKey(memberId)) { //checks for existence and correspondence with memberID
            if (item != null) { //checks existence of item.
                if (!item.isCheckedOut()) { //checks if item is checked out already
                    String memberName = library.getMemberNameById(memberId); // get the name of the member by ID
                    if (library.getMembers().get(memberId) != null) {
                        // check if the item is reserved
                        if (library.isReserved(item)) {
                            System.out.println("Error: This item is reserved by another member and cannot be checked out. Please use option 6 to see all reservations.");
                        } else {
                            // proceed with the check-out
                            library.getMembers().get(memberId).addItem(item);
                            item.setCheckedOut(true); //flips boolean
                            item.addCheckedOutBy(memberId); //adds memberID to arraylist
                            System.out.println(memberName + " checked out: " + item.getTitle());
                        }
                    } else { //error messages
                        System.out.println("Member does not exist. Please use option 12 to view all registered members, or use option 1 to create and register a new member.");
                    }
                } else {
                    System.out.println("Error: The item is already checked out. Please use option 8 to view checked out items");
                }
            } else {
                System.out.println("Error: The item was not found. Please use option 7 to view available items");
            }
        } else {
            System.out.println("Error: Only registered members can check out items or incorrect member ID. Please use option 12 to view all registered members, or use option 1 to create and register a new member.");
        }
    }

    public void returnItem(Item item, String memberId, Library library) { //return item method
        if (item != null) { //checks existence of item
            if (item.isCheckedOut() && item.getCheckedOutBy().contains(memberId)) { //checks if item is checked out with correspondence to ID
                String memberName = library.getMemberNameById(memberId); // get the name of the member by ID
                // does a check of correspondence with respective item type
                if (item instanceof book) {
                    library.getMembers().get(memberId).removeItem(item); //uses correspondence to remove accordingly
                    item.setCheckedOut(false); //flips boolean after it was flipped due to check out
                    item.removeCheckedOutBy(memberId); // removes from arraylist, so it won't be displayed in option 8
                    System.out.println(memberName + " returned: " + ((book) item).getBookDetails());
                } else if (item instanceof DVD) {
                    library.getMembers().get(memberId).removeItem(item);
                    item.setCheckedOut(false);
                    item.removeCheckedOutBy(memberId);
                    System.out.println(memberName + " returned: " + ((DVD) item).getDVDDetails());
                } else if (item instanceof magazine) {
                    library.getMembers().get(memberId).removeItem(item);
                    item.setCheckedOut(false);
                    item.removeCheckedOutBy(memberId);
                    System.out.println(memberName + " returned: " + ((magazine) item).getMagazineDetails());
                }
            } else if (!item.isCheckedOut()) { // if Item is checked out, prompts user to see further details
                System.out.println("Error: The item is not checked out. Please use option 7 to view available items");
            } else {
                System.out.println("Error: You cannot return this item as it is not checked out by you or incorrect member ID. Use either option 12, 1 or 8");
            }
        } else {
            System.out.println("Error: The item was not found. Please use option 8 to view checked out items");
        }
    }

    public void listCheckedOutItems() { //list checked out items using arraylist
        System.out.println("Checked Out Items: ");
        if (library != null && library.getMembers() != null) { //does a check with existence and ensures that members is not null
            boolean checkedOutItemsExist = false; //boolean value for variable manipulation
            for (Map.Entry<String, LibraryMember> entry : library.getMembers().entrySet()) { //for every instance of hashmap entry, get the set and key values to be printed with
                String memberId = entry.getKey();
                LibraryMember member = entry.getValue();
                for (Item item : member.getCheckedOutItems()) {
                    System.out.println(item.getTitle() + " (Checked out by: " + memberId + ")");
                    checkedOutItemsExist = true; //boolean flip
                }
            }
            if (!checkedOutItemsExist) { //error validation in specific case
                System.out.println("No checked out items. Check out items using option 2");
            }
        } else {
            System.out.println("No checked out items. Check out items using option 2");
        }
    }
}