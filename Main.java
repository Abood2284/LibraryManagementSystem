package com.company;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import static com.company.Books.bookIssued;
import static com.company.Books.bookAuthor;
import static com.company.Books.bookName; // I guess its future declaration, done by intellij idea I used bookName arraylist in a method, so it came here, I don't know why

class libraryExceptions extends Exception{
    public String bookAlreadyAvailable() {
        return "Sorry the book you want to add, Already exists in our library or maybe it's borrowed by someone";
    }
    public String emptyStorage(){
        return "Sorry there is no books available";
    }
    public String noMatch(){return "Sorry you are not the person who borrowed this person, or this book doesn't belong to our library";}
}
interface Books{
    ArrayList<String> bookName = new ArrayList<>();
    ArrayList<String> bookAuthor = new ArrayList<>();
    ArrayList<String> bookIssued = new ArrayList<>();
    ArrayList<String> bookIssuedTo = new ArrayList<>();
    ArrayList<String> bookIssuedOn = new ArrayList<>();
    ArrayList<String> bookIssuedTemp = new ArrayList<>();
    ArrayList<String> bookIssuedAuthorTemp = new ArrayList<>();

}

/**
 * This is a library management [small project] class
 * @author Abdul raheem
 * @since 2021
 * @version 0.1
 * @see <a href="https://github.com/Abood2284/LibraryManagementSystem" target="_blank">Github Source Code</a>
 */
class library_2 implements com.company.Books {
    /**
     *
     * @param bookname name of the book to be added in the arrayList
     * @param bookauthor name of the author with the same book you added
     */
    public void AddBook(String bookname, String bookauthor){
        bookName.add(bookname);
        bookAuthor.add(bookauthor);
        System.out.println("Book added successfully");
    }

    /**
     * Displays all the books available with corresponds to the author name
     * @throws com.company.libraryExceptions if bookName array<List> is empty
     */
    public void ShowBook(){
        try {
            if (bookName.isEmpty()){
                throw new com.company.libraryExceptions();
            }else {
                for (int i =0; i<bookName.size(); i++){
                    System.out.println("BookName: "+ bookName.get(i) +"\t bookAuthor: "+ bookAuthor.get(i));
                }
            }
        }catch(com.company.libraryExceptions e){
            System.out.println(e.emptyStorage());
        }
    }

    /**
     * Display those books that are issue to the user [ Note:- these books are not available in the main array list ]
     */
    public void ShowIssuedBooks() {
        System.out.println("Books issued");
        for (int i =0; i<bookIssued.size(); i++){
            System.out.println("BookName: "+bookIssued.get(i)+"\t Borrower: "+bookIssuedTo.get(i)+"\t BookIssueDate: "+bookIssuedOn.get(i));
        }
    }

    /**
     *
     * @param BookYouWant Name of the book you want to Borrow
     * @param borrowerName Name of the user Currently Running the program
     * @param bookIssuedon You Don't need to pass this. It is automatically passed by the method it is called
     */
    public void IssueBook(String BookYouWant, String borrowerName, String bookIssuedon){
        for (int i=0; i<bookName.size(); i++){
            if (bookName.get(i).equals(BookYouWant)){
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
            if(i>= bookName.size()){
                if (!bookName.get(i).equals(BookYouWant))
                    System.out.println("Sorry book not available in our library");
            }
        }
    }

    /**
     *
     * @param borrowerName2 -> Name of the user who borrowed the book/who is running the program
     * @param returnBook -> Name of the book you borrowed
     * @throws com.company.libraryExceptions -> If Book you want to return is not issued from our library or the borrower himself didn't came to return the book [ same name you gave when borrowing the book ]
     */
    public void returnBorrowedBook(String borrowerName2, String returnBook){
        try {
            for (int i=0; i<bookIssued.size(); i++){
                if (bookIssued.get(i).equals(returnBook) && bookIssuedTo.get(i).equals(borrowerName2) && bookIssuedTemp.get(i).equals(returnBook)){
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

                }else {
                    throw new com.company.libraryExceptions();
                }

            }
        }catch(com.company.libraryExceptions e){
            System.out.println(e.noMatch());
        }
    }
}

public class _104_Exercise7 {
    public static void statement(){
        System.out.println("""
            0. Leave
            1. AddBooks to library
            2. ShowAvailableBooks
            3. BorrowBook
            4. ReturnBorrowedBook""");
    }

    /**
     * This is where everything happens just call this method in main
     */
    public static void Start(){
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        com.company.library_2 Manager1 = new com.company.library_2();
        boolean flag = true;
        Scanner scan = new Scanner(System.in);
        System.out.println("Manager: How may i help you with sir");
        System.out.println("(Press an key!!!)");
        while(flag){
            scan.nextLine();
            statement();
            System.out.print(">> ");
            int x = scan.nextInt();
            switch(x){
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
                        if (bookName.contains(boknam) || bookIssued.contains(boknam)){
                            throw new com.company.libraryExceptions();
                        }else{
                            Manager1.AddBook(boknam, bokauthor);
                        }
                    }catch(com.company.libraryExceptions e){
                        System.out.println(e.bookAlreadyAvailable());
                    }
                }
                case 2 -> {
                    try {
                        if (bookName.isEmpty()){
                            throw new com.company.libraryExceptions();
                        }else {
                            Manager1.ShowBook();
                        }
                    }catch(com.company.libraryExceptions e){
                        System.out.println(e.emptyStorage());
                    }
                }
                case 3 -> {
                    try {
                        if (bookName.isEmpty()){
                            throw new com.company.libraryExceptions();
                        }else {
                            for (int i =0; i<bookName.size(); i++){
                                System.out.println("BookName: "+ bookName.get(i) +"\t bookAuthor: "+ bookAuthor.get(i));
                            }
                            System.out.println("So which book you want?");
                            System.out.print(">> ");
                            scan.nextLine();
                            String bookyOUwant = scan.nextLine();
                            System.out.println("Your name sir");
                            System.out.print(">> ");
                            String borrowerNamE = scan.nextLine();
                            String myDate1 = dt.format(df);  // to get the current date
                            // will pass all the details to IssueBook method
                            Manager1.IssueBook(bookyOUwant, borrowerNamE, myDate1);
                        }
                    }catch(com.company.libraryExceptions e){
                        System.out.println(e.emptyStorage());
                    }
                }
                case 4 -> {
                    System.out.println("Your name sir");
                    System.out.print(">> ");
                    scan.nextLine();
                    String returnerName = scan.nextLine();
                    System.out.println("Which book you want to return sir");
                    Manager1.ShowIssuedBooks();
                    System.out.print(">> ");
                    String returnerBook = scan.nextLine();
                    Manager1.returnBorrowedBook(returnerName, returnerBook);
                }
                default -> System.out.println("Press keys between -1 & 5");
            }
            System.out.println("Press any key!!!");
        }
    }
    public static void main(String[] args) {
        Start();

    }
}
