import java.util.Scanner;

class book extends Item { //book subclass that extends to item super class

    //private variables unique to book item
    private String author;
    private String ISBN;
    private String genre;
    private String language;
    private int numberOfPages;

    //getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    //book constructor that supers item variables and creates variables unique to the class
    public book(String title, String author, String ISBN, String genre, String language, String year, int numberOfPages, boolean checkedOut) {
        super(title, year, checkedOut);
        this.author = author;
        this.ISBN = ISBN;
        this.genre = genre;
        this.language = language;
        this.numberOfPages = numberOfPages;
    }

    //default constructor
    public book() {
    }

    //main input function for user when adding book item
    public book input(Scanner scanner) {
        System.out.println("Please enter the book details as follows below: ");

        // input for title
        System.out.println("Enter the title for the book: ");
        setTitle(scanner.nextLine());

        // input for author
        System.out.println("Enter the author for the book: ");
        setAuthor(scanner.nextLine());
        while (getAuthor().isEmpty()) {
            System.out.println("Author cannot be empty. Please enter the author for the book: ");
            setAuthor(scanner.nextLine());
        }

        // input for year and ensures year is 4 digits long
        System.out.println("Enter the year for the book: ");
        setYear(scanner.nextLine());
        while (!getYear().matches("\\d{4}")) { //ensures year is 4 digits long
            System.out.println("Invalid year format. Please enter the year for the book (4 digits): ");
            setYear(scanner.nextLine());
        }

        // input for ISBN
        System.out.println("Enter the ISBN for the book: ");
        setISBN(scanner.nextLine());
        while (getISBN().isEmpty()) {
            System.out.println("ISBN cannot be empty. Please enter the ISBN for the book: ");
            setISBN(scanner.nextLine());
        }

        // input for genre
        System.out.println("Enter the genre for the book: ");
        setGenre(scanner.nextLine());
        while (getGenre().isEmpty()) {
            System.out.println("Genre cannot be empty. Please enter the genre for the book: ");
            setGenre(scanner.nextLine());
        }

        // input for language
        System.out.println("Enter the language the book is written in: ");
        setLanguage(scanner.nextLine());
        while (getLanguage().isEmpty()) {
            System.out.println("Language cannot be empty. Please enter the language for the book: ");
            setLanguage(scanner.nextLine());
        }

        // input for number of pages
        System.out.println("Enter the number of pages for the book: ");
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number of pages for the book: ");
            scanner.nextLine(); // consume the invalid input
        }
        setNumberOfPages(scanner.nextInt());
        scanner.nextLine(); // Consume the newline

        setCheckedOut(false); //flip boolean

        System.out.println("Book added!");
        return new book(getTitle(), getAuthor(), getISBN(), getGenre(), getLanguage(), getYear(), getNumberOfPages(), isCheckedOut());
    }

    //print function as specified in guidelines of coursework
    public void print() {
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + getAuthor());
        System.out.println("Year: " + getYear());
        System.out.println("ISBN: " + getISBN());
        System.out.println("Genre: " + getGenre());
        System.out.println("Language: " + getLanguage());
        System.out.println("Number of pages: " + getNumberOfPages());
    }

    //get details method as specified for formatted printing with all variables
    public String getBookDetails() {
        return String.format("Book - Title: %s, Author: %s, Year: %s, ISBN: %s, Genre: %s, Language: %s, Pages: %s",
                getTitle(), getAuthor(), getYear(), getISBN(), getGenre(), getLanguage(), getNumberOfPages());
    }
}