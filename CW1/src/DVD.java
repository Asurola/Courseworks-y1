import java.util.Scanner;

class DVD extends Item { //dvd subclass that extends to item super class

    //dvd constructor that supers item variables and creates variables unique to the class
    public DVD(String title, String director, String year, int runtime, String genre, boolean bluRay, boolean checkedOut) {
        super(title, year, checkedOut);
        this.director = director;
        this.runtime = runtime;
        this.genre = genre;
        this.bluRay = bluRay;
    }

    //private variables unique to dvd item
    private String director;
    private int runtime;
    private String genre;
    private boolean bluRay;

    //default constructor
    public DVD(){
    }

    //getters and setters
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getRuntime() {
        return runtime;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isBluRay() {
        return bluRay;
    }

    public void setBluRay(boolean bluRay) {
        this.bluRay = bluRay;
    }

    //main input function for user when adding dvd item
    public DVD input(Scanner scanner) {
        System.out.println("Please enter the DVD details as follows below: ");

        // input validation for title
        System.out.println("Enter the title for the DVD: ");
        setTitle(scanner.nextLine());
        while (getTitle().isEmpty()) {
            System.out.println("Title cannot be empty. Please enter the title for the DVD: ");
            setTitle(scanner.nextLine());
        }

        // input validation for director
        System.out.println("Enter the director for the DVD: ");
        setDirector(scanner.nextLine());
        while (getDirector().isEmpty()) {
            System.out.println("Director cannot be empty. Please enter the director for the DVD: ");
            setDirector(scanner.nextLine());
        }

        // input validation for year
        System.out.println("Enter the year for the DVD: ");
        setYear(scanner.nextLine());
        while (!getYear().matches("\\d{4}")) { //ensures year is 4 digits
            System.out.println("Invalid year format. Please enter the year for the DVD (4 digits): ");
            setYear(scanner.nextLine());
        }

        // input validation for runtime
        System.out.println("Enter the runtime for the DVD in minutes: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number for the runtime: ");
            scanner.next(); // Consume the invalid input
        }
        setRuntime(scanner.nextInt());

        // reset the scanner buffer
        scanner.nextLine();

        // input validation for genre
        System.out.println("Enter the genre for the DVD: ");
        setGenre(scanner.nextLine());
        while (getGenre().isEmpty()) {
            System.out.println("Genre cannot be empty. Please enter the genre for the DVD: ");
            setGenre(scanner.nextLine());
        }

        setCheckedOut(false);

        // Validate Blu-Ray input
        boolean validInput = false;
        while (!validInput) {
            System.out.println("Is the DVD a Blu-Ray disc? (Answer with Yes or No): ");
            String input = scanner.nextLine().toLowerCase();
            if (input.equals("yes") || input.equals("no")) {
                setBluRay(input.equals("yes"));
                validInput = true;
            } else {
                System.out.println("Invalid input. Please answer with Yes or No.");
            }
        }

        System.out.println("DVD added!");

        return new DVD(getTitle(), getDirector(), getYear(), getRuntime(), getGenre(), isCheckedOut(), isBluRay());
    }


    //print function as specified in guidelines of coursework
    public void print() {
        System.out.println("Title: " + getTitle());
        System.out.println("Director: " + getDirector());
        System.out.println("Release year: " + getYear());
        System.out.println("Runtime: " + getRuntime() + " minutes");
        System.out.println("Genre: " + getGenre());
        System.out.println("Blu-Ray: " + (isBluRay() ? "Yes" : "No"));
    }

    //get details method as specified for formatted printing with all variables
    public String getDVDDetails() {
        return String.format("DVD - Title: %s, Director: %s, Release year: %s, Runtime: %d minutes, Genre: %s, Blu-Ray: %s",
                getTitle(), getDirector(), getYear(), getRuntime(), getGenre(), (isBluRay() ? "Yes" : "No"));
    }
}
