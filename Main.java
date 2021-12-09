package com.company;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static com.company.BookArrayLists.bookAuthor;
import static com.company.BookArrayLists.bookIssuedTo;


interface BookArrayLists {
    ArrayList<String> bookName = new ArrayList<>();
    ArrayList<String> bookAuthor = new ArrayList<>();
    ArrayList<String> bookIssued = new ArrayList<>();
    ArrayList<String> bookIssuedTo = new ArrayList<>();
    ArrayList<String> bookIssuedOn = new ArrayList<>();
    ArrayList<String> bookIssuedTemp = new ArrayList<>();
    ArrayList<String> bookAuthorTemp = new ArrayList<>();
}

class LibraryManagementSystemRefactor implements com.company.BookArrayLists {
    public void addSampleBooks() {
        bookName.add("Harry potter");
        bookAuthor.add("J.k Rowling");
        bookName.add("Letting Go");
        bookAuthor.add("Philip Roth");
        bookName.add("Fear of Flying");
        bookAuthor.add("Erica Jong");
        bookName.add("When Breath Becomes Air");
        bookAuthor.add("Paul Kalanithi");
        bookName.add("The Adventures of Tom Sawyer");
        bookAuthor.add("Mark Twain");
        bookName.add("Little Women");
        bookAuthor.add("Louisa May Alcott");
        bookName.add("The Phantom Tollbooth");
        bookAuthor.add("Norton Juster");
        bookName.add("The Heart of the Matter");
        bookAuthor.add("Graham Greene");
    }

    public void AddBook(String bookname, String bookauthor) {
        bookName.add(bookname);
        bookAuthor.add(bookauthor);
        System.out.println("Book added successfully");
    }

    public void IssueBook(String BookYouWant, String borrowerName, String bookIssuedon, String AuthorName) {
        bookIssued.add(BookYouWant);
        bookIssuedTo.add(borrowerName);
        bookIssuedOn.add(bookIssuedon);
        bookIssuedTemp.add(BookYouWant);
        bookAuthorTemp.add(AuthorName);
        bookName.remove(BookYouWant);
        bookAuthor.remove(AuthorName);
        System.out.println("Book Issued to "+borrowerName);
    }

    public void returnBorrowedBook(String borrowerName2, String returnBook) {
        for (int i = 0; i < bookIssued.size(); i++) {
            //            && bookIssuedTo.get(i).equals(borrowerName2) && bookIssuedTemp.get(i).equals(returnBook)
            if (bookIssued.get(i).equals(returnBook)) {
                String temp1 = bookIssuedTemp.get(i);
                String temp2 = bookAuthorTemp.get(i);
                bookName.add(temp1);
                bookAuthor.add(temp2);
                bookIssued.remove(returnBook);
                bookIssuedOn.remove(i);
                bookIssuedTo.remove(borrowerName2);
                bookIssuedTemp.remove(returnBook);
                bookAuthorTemp.remove(i);
                System.out.println("Book Returned Successfully");
                break;
            }
        }
    }

    public void ShowAllBooks() {
        for (int i = 0; i < bookName.size(); i++) {
            System.out.println("BookName: " + bookName.get(i) + "\t bookAuthor: " + bookAuthor.get(i));
        }
    }

    public void ShowIssuedBooks(String borrowerName3) {
        System.out.println("Books issued to "+borrowerName3 +": ");
        for (int i = 0; i < bookIssued.size(); i++)
            if (borrowerName3.equalsIgnoreCase(bookIssuedTo.get(i))) {
                System.out.println("BookName: " + bookIssued.get(i) + "\t Borrower: " + borrowerName3 + "\t BookIssueDate: " + bookIssuedOn.get(i));
            }
    }
}


public class test2 {
    public static void main(String[] args) {
        com.company.LibraryManagementSystemRefactor boss = new com.company.LibraryManagementSystemRefactor();
        boss.addSampleBooks();
        Start();
    }

    public static void AddBooksToLibrary(){
        com.company.LibraryManagementSystemRefactor Manager1 = new com.company.LibraryManagementSystemRefactor();
        Scanner scan = new Scanner(System.in);
        String boknam;
        String bokauthor;
        System.out.print("Enter bookNAme: ");
        scan.nextLine();
        boknam = scan.nextLine();
        System.out.print("Enter bookAuthor: ");
        bokauthor = scan.nextLine();
        if (Manager1.bookName.contains(boknam) || Manager1.bookIssued.contains(boknam))
            System.out.println("Sorry the book you want to add, Already exists in our library or maybe it's borrowed by someone");
        else
            Manager1.AddBook(boknam, bokauthor);
    }

    public static void ShowAvailableBooks(){
        com.company.LibraryManagementSystemRefactor Manager1 = new com.company.LibraryManagementSystemRefactor();
        if (Manager1.bookName.isEmpty())
            System.out.println("Sorry there is no books available Right Now");
        else
            Manager1.ShowAllBooks();
    }

    public static void BorrowBook(){
        com.company.LibraryManagementSystemRefactor Manager1 = new com.company.LibraryManagementSystemRefactor();
        LocalDateTime dt = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        Scanner scan = new Scanner(System.in);
        if (!Manager1.bookName.isEmpty()) {
            Manager1.ShowAllBooks();
            System.out.println("So which book you want?");
            System.out.print(">> ");
            String bookyOUwant = scan.nextLine();
            for (int i = 0; i < Manager1.bookName.size(); i++) {
                if (bookyOUwant.equalsIgnoreCase(Manager1.bookName.get(i))) {
                    System.out.println("Your name sir");
                    System.out.print(">> ");
                    String borrowerNamE = scan.nextLine();
                    String myDate1 = dt.format(df);
                    String Author = bookAuthor.get(i);
                    Manager1.IssueBook(bookyOUwant, borrowerNamE, myDate1, Author);
                    break;
                }
                if (i > Manager1.bookName.size())
                    System.out.println("Sorry sir, We currently don't have that book in our library");
            }
        } else
            System.out.println("Sorry there is no books available Right Now");
    }

    public static void ReturnBorrowedBooks(){
        com.company.LibraryManagementSystemRefactor Manager1 = new com.company.LibraryManagementSystemRefactor();
        Scanner scan = new Scanner(System.in);
        if (!Manager1.bookIssued.isEmpty()) {
            System.out.println("Your name sir");
            System.out.print(">> ");
            String returnerName = scan.nextLine();
            int i = 0, counter = 0, counter1 = 0;
            while (i < bookIssuedTo.size()) {
                if (returnerName.equalsIgnoreCase(bookIssuedTo.get(i))) {
                    counter1++;
                    System.out.println("Which book you want to return sir");
                    Manager1.ShowIssuedBooks(returnerName);
                    System.out.print(">> ");
                    String returnerBook = scan.nextLine();
                    for (int j = 0; j < Manager1.bookIssued.size(); j++)
                        if (returnerBook.equalsIgnoreCase(Manager1.bookIssued.get(j))) {
                            Manager1.returnBorrowedBook(returnerName, returnerBook);
                            counter+=1;
                            break;
                        }
                }
                i++;
                if (counter==1)
                    break;
            }
            if (counter1==0)
                System.out.println("There is no book lended with your name");

        } else
            System.out.println("Sorry there is no books borrowed from our library");
    }

    public static void Start() {
        Scanner scan = new Scanner(System.in);
        boolean flag = true;
        do {
            System.out.println("\t\t\t\t\tManager: HOW MAY I HELP YOU SIR");
            System.out.println("(Press an key!!!)");
            scan.nextLine();
            System.out.print("0. Leave\n1. AddBooks to library\n2. ShowAvailableBooks\n3. BorrowBook\n4. ReturnBorrowedBooks\n");
            System.out.print(">> ");
            int x = scan.nextInt();
            switch (x) {
                case 0 -> System.out.println("Thank you sir! PLease keep visiting");
                case 1 -> AddBooksToLibrary();
                case 2 -> ShowAvailableBooks();
                case 3 -> BorrowBook();
                case 4 -> ReturnBorrowedBooks();
                default -> System.out.println("Press keys between -1 & 5");
            }
            if (x==0)
                flag = false;
        } while (flag);
    }
}
