import java.util.Scanner;

class magazine extends Item { //magazine subclass that extends to item super class

    //magazine constructor that supers item variables and creates variables unique to the class
    public magazine(String title, String year, String publisher, int issueNumber, String topic, String targetAudience, boolean checkedOut) {
        super(title, year, checkedOut);
        this.publisher = publisher;
        this.issueNumber = issueNumber;
        this.topic = topic;
        this.targetAudience = targetAudience;
    }

    //private variables unique to magazine item
    private String publisher;
    private int issueNumber;
    private String topic;
    private String targetAudience;

    //default constructor
    public magazine() {

    }

    //getters and setters
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(String targetAudience) {
        this.targetAudience = targetAudience;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    //main input function for user when adding magazine item
    public magazine input(Scanner scanner) {
        System.out.println("Please enter the magazine details as follows below: ");

        // input validation for title
        System.out.println("Enter the title for the magazine: ");
        setTitle(scanner.nextLine());
        while (getTitle().isEmpty()) {
            System.out.println("Title cannot be empty. Please enter the title for the magazine: ");
            setTitle(scanner.nextLine());
        }

        // input validation for publisher
        System.out.println("Enter the publisher for the magazine: ");
        setPublisher(scanner.nextLine());
        while (getPublisher().isEmpty()) {
            System.out.println("Publisher cannot be empty. Please enter the publisher for the magazine: ");
            setPublisher(scanner.nextLine());
        }

        // input validation for target audience
        System.out.println("Enter the target audience for the magazine: ");
        setTargetAudience(scanner.nextLine());
        while (getTargetAudience().isEmpty()) {
            System.out.println("Target audience cannot be empty. Please enter the target audience for the magazine: ");
            setTargetAudience(scanner.nextLine());
        }

        // input validation for year
        System.out.println("Enter the year for the magazine: ");
        setYear(scanner.nextLine());
        while (!getYear().matches("\\d{4}")) { //ensures year is 4 digits long
            System.out.println("Invalid year format. Please enter the year for the magazine (4 digits): ");
            setYear(scanner.nextLine());
        }

        // input validation for issue number
        System.out.println("Enter the issue number for the magazine: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number for the issue number: ");
            scanner.next(); // consume the invalid input
        }
        setIssueNumber(scanner.nextInt());

        // reset the scanner buffer
        scanner.nextLine();

        // input validation for topic
        System.out.println("Enter the topic for the magazine: ");
        setTopic(scanner.nextLine());
        while (getTopic().isEmpty()) {
            System.out.println("Topic cannot be empty. Please enter the topic for the magazine: ");
            setTopic(scanner.nextLine());
        }

        setCheckedOut(false); //flip boolean input

        System.out.println("Magazine added!");

        return new magazine(getTitle(), getPublisher(), getTargetAudience(), getIssueNumber(), getYear(), getTopic(), isCheckedOut());
    }


    //print function as specified in guidelines of coursework
    public void print() {
        System.out.println("Title: " + getTitle());
        System.out.println("Publisher: " + getPublisher());
        System.out.println("Target audience: " + getTargetAudience());
        System.out.println("Year: " + getYear());
        System.out.println("Issue Number: #" + getIssueNumber());
        System.out.println("Topic: " + getTopic());
    }

    //get details method as specified for formatted printing with all variables
    public String getMagazineDetails() {
        return String.format("Magazine - Title: %s, Publisher: %s, Target audience: %s, Year: %s, Issue Number: #%d, Topic: %s",
                getTitle(), getPublisher(), getTargetAudience(), getYear(), getIssueNumber(), getTopic());
    }
}
