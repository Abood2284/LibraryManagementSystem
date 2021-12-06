/*
        Create a library management system which is capable of issuing books to the students.
        Book should have info like:
        1. Book name
        2. Book Author
        3. Issued to
        4. Issued on
        User should be able to add books, return issued books, issue books
        Assume that all the users are registered with their names in the central database
         */
//    bookName -> Its stores all the books, used as storage
//    bookAuthor -> It's stores author name corresponding to bookName
//    bookIssued - > Stores which book are on issue
//    bookIssuedTo -> stores name of the borrower corresponding to bookIssued
//    bookIssuedOn -> stores date of the borrower corresponding to bookIssued
package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

//import static com.company.Books.bookIssued;
//import static com.company.Books.bookAuthor;
//import static com.company.Books.bookName; // I guess its future declaration, done by intellij idea I used bookName arraylist in a method, so it came here, I don't know why

class libraryExceptions extends Exception {
    public String bookAlreadyAvailable() {
        return "Sorry the book you want to add, Already exists in our library or maybe it's borrowed by someone";
    }

    public String emptyStorage() {
        return "Sorry there is no books available Right Now";
    }

    public String noMatch() {
        return "Sorry you are not the person who borrowed this person, or this book doesn't belong to our library";
    }
}

/**
 * This is a library management [small project] class
 *
 * @author Abdul raheem
 * @version 0.1
 * @see <a href="https://github.com/Abood2284/LibraryManagementSystem" target="_blank">Github Source Code</a>
 * @since 2021
 */
@SuppressWarnings("JavaDoc")
class library_2 {
    /*Methods Implemented in this class
     * AddBook  -> ( To add book in your library )
     * ShowBook -> ( To display available books in library )
     * ShowIssuedBooks -> ( To Display books that have been issued )
     * IssueBook  -> ( To issue a book from our library )
     * returnBorrowedBook -> ( To return the bornm,./rowed book to our library )*/
    public ArrayList<String> bookName = new ArrayList<>();
    public ArrayList<String> bookAuthor = new ArrayList<>();
    public ArrayList<String> bookIssued = new ArrayList<>(); // books which are issued
    public ArrayList<String> bookIssuedTo = new ArrayList<>();  // to whom the books the are issued
    public ArrayList<String> bookIssuedOn = new ArrayList<>();
    public ArrayList<String> bookIssuedTemp = new ArrayList<>();
    public ArrayList<String> bookIssuedAuthorTemp = new ArrayList<>();

    /**
     * @param bookname   name of the book to be added in the arrayList
     * @param bookauthor name of the author with the same book you added
     */
    public void AddBook(String bookname, String bookauthor) {
        bookName.add(bookname);
        bookAuthor.add(bookauthor);
        System.out.println("Book added successfully");
    }

    /**
     * Displays all the books available with corresponds to the author name
     *
     * @throws com.company.libraryExceptions if bookName array<List> is empty
     */
    public void ShowBook() {
        try {
            if (bookName.isEmpty()) {
                throw new com.company.libraryExceptions();
            } else {
                for (int i = 0; i < bookName.size(); i++) {
                    System.out.println("BookName: " + bookName.get(i) + "\t bookAuthor: " + bookAuthor.get(i));
                }
            }
        } catch (com.company.libraryExceptions e) {
            System.out.println(e.emptyStorage());
        }
    }

    /**
     * Display those books that are issue to the user [ Note:- these books are not available in the main array list ]
     */
    public void ShowIssuedBooks() {
        System.out.println("Books issued to you:");
        for (int i = 0; i < bookIssued.size(); i++) {
            System.out.println("BookName: " + bookIssued.get(i) + "\t Borrower: " + bookIssuedTo.get(i) + "\t BookIssueDate: " + bookIssuedOn.get(i));
        }
    }

    /**
     * @param BookYouWant  Name of the book you want to Borrow
     * @param borrowerName Name of the user Currently Running the program
     * @param bookIssuedon You Don't need to pass this. It is automatically passed by the method it is called
     */
    public void IssueBook(String BookYouWant, String borrowerName, String bookIssuedon) {
        if (bookName.isEmpty()) {
            System.out.println("Sorry there are no book available in our library, Currently");
        }
        for (int i = 0; i < bookName.size(); i++) {
            if (bookName.get(i).equals(BookYouWant)) {
                System.out.println("Book found...\t we are issuing the book");
                bookIssued.add(BookYouWant);
                bookIssuedTo.add(borrowerName);
                bookIssuedOn.add(bookIssuedon);
                //                Storing the original information of the book in temp array
                bookIssuedTemp.add(BookYouWant);
                String temp = bookAuthor.get(i);
                bookIssuedAuthorTemp.add(temp);

                bookName.remove(i);
                bookAuthor.remove(i);
                break;
            }

        }
    }

    /**
     * @param borrowerName2 -> Name of the user who borrowed the book/who is running the program
     * @param returnBook    -> Name of the book you borrowed
     * @throws com.company.libraryExceptions -> If Book you want to return is not issued from our library or the borrower himself didn't came to return the book [ same name you gave when borrowing the book ]
     */
    public void returnBorrowedBook(String borrowerName2, String returnBook) {
        try {
            for (int i = 0; i < bookIssued.size(); i++) {
                if (bookIssued.get(i).equals(returnBook) && bookIssuedTo.get(i).equals(borrowerName2) && bookIssuedTemp.get(i).equals(returnBook)) {
                    String temp1 = bookIssuedTemp.get(i);
                    String temp2 = bookIssuedAuthorTemp.get(i);
                    bookName.add(temp1);
                    bookAuthor.add(temp2);
                    bookIssued.remove(returnBook);
                    bookIssuedOn.remove(i);
                    bookIssuedTo.remove(borrowerName2);
                    bookIssuedTemp.remove(i);
                    bookIssuedAuthorTemp.remove(i);
                    System.out.println("Book Returned Successfully");

                } else {
                    throw new com.company.libraryExceptions();
                }

            }
        } catch (com.company.libraryExceptions e) {
            System.out.println(e.noMatch());
        }
    }
}

/**
 * This is a file class
 *
 * @author Abdul raheem
 * {@link com.company.library_2}
 */

public class _104_Exercise7 {
    public static void statement() {
        System.out.println("""
            0. Leave
            1. AddBooks to library
            2. ShowAvailableBooks
            3. BorrowBook
            4. ReturnBorrowedBook""");
    }

    /**
     * This is where everything happens just call this method in main
     * Inside there is a while-loop which will run till user inputs the leave key
     * You can see a switch case to call the appropriate functions
     */
    public static void Start() {
        com.company.library_2 Manager1 = new com.company.library_2();
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        System.out.println("Manager: How may i help you with sir");
        System.out.println("(Press an key!!!)");
        while (flag) {
            scan.nextLine();
            statement();
            System.out.print(">> ");
            int x = scan.nextInt();
            switch (x) {
                case 0 -> {
                    System.out.println("Thank you sir! PLease keep visiting");
                    flag = false;
                }
                case 1 -> {
                    String boknam;
                    String bokauthor;
                    System.out.print("Enter bookNAme: ");
                    scan.nextLine();
                    boknam = scan.nextLine();
                    System.out.print("Enter bookAuthor: ");
                    bokauthor = scan.nextLine();
                    try {
                        if (Manager1.bookName.contains(boknam) || Manager1.bookIssued.contains(boknam)) {
                            throw new com.company.libraryExceptions();
                        } else {
                            Manager1.AddBook(boknam, bokauthor);
                        }
                    } catch (com.company.libraryExceptions e) {
                        System.out.println(e.bookAlreadyAvailable());
                    }
                }
                case 2 -> {
                    try {
                        if (Manager1.bookName.isEmpty()) {
                            throw new com.company.libraryExceptions();
                        } else {
                            Manager1.ShowBook();
                        }
                    } catch (com.company.libraryExceptions e) {
                        System.out.println(e.emptyStorage());
                    }
                }
                case 3 -> {
                    try {
                        if (Manager1.bookName.isEmpty()) {
                            throw new com.company.libraryExceptions();
                        } else {
                            for (int i = 0; i < Manager1.bookName.size(); i++) {
                                System.out.println("BookName: " + Manager1.bookName.get(i) + "\t bookAuthor: " + Manager1.bookAuthor.get(i));
                            }
                            System.out.println("So which book you want?");
                            System.out.print(">> ");
                            scan.nextLine();
                            String bookyOUwant = scan.nextLine();
                            for (int i = 0; i < Manager1.bookName.size(); i++) {
                                if (bookyOUwant.equalsIgnoreCase(Manager1.bookName.get(i))) {
                                    System.out.println("Your name sir");
                                    System.out.print(">> ");
                                    String borrowerNamE = scan.nextLine();
                                    String myDate1 = dt.format(df);  // to get the current date
                                    // will pass all the details to IssueBook method
                                    Manager1.IssueBook(bookyOUwant, borrowerNamE, myDate1);
                                } else {
                                    System.out.println("Sorry sir, We currently don't have that book in our library");
                                }
                            }

                        }
                    } catch (com.company.libraryExceptions e) {
                        System.out.println(e.emptyStorage());
                    }
                }
                case 4 -> {
                    if (Manager1.bookIssued.isEmpty()) {
                        System.out.println("Sorry there is no books borrowed from our library");
                    } else {
                        System.out.println("Your name sir");
                        System.out.print(">> ");
                        scan.nextLine();
                        String returnerName = scan.nextLine();
                        for (int i = 0; i < Manager1.bookIssuedTo.size(); i++) {
                            if (returnerName.equalsIgnoreCase(Manager1.bookIssuedTo.get(i))) {
                                System.out.println("Which book you want to return sir");
                                Manager1.ShowIssuedBooks();
                                System.out.print(">> ");
                                while (true) {
                                    String returnerBook = scan.nextLine();
                                    for (int j = 0; j < Manager1.bookIssued.size(); j++) {
                                        if (returnerBook.equalsIgnoreCase(Manager1.bookIssued.get(j))) {
                                            Manager1.returnBorrowedBook(returnerName, returnerBook);
                                            break;
                                        } else {
                                            System.out.println("Sorry sir there is no book of that name you lended. PLEASE WRITE THE NAME OF THE BOOK CAREFULLY");
                                        }
                                        System.out.print(">> ");
                                    }
                                }

                            } else {
                                System.out.println("Sorry sir there is no books lent with your name");
                            }
                        }
                    }
                }
                default -> System.out.println("Press keys between -1 & 5");
            }
            System.out.println("Press any key!!!");
        }
    }

    /**
     * Just run the start(); method and BOOM!! everything starts working
     */
    public static void main(String[] args) {
        Start();

    }
}
