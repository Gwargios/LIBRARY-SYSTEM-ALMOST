package Borrower;


import java.io.Serializable;
import java.util.ArrayList;
import Transaction.*;
import Book.*;
import Notification.*;

public class Borrower implements Serializable {
    private String BorrowerID;
    private String BorrowerName;
    private int Num_Of_borrowed_Books;
    private static int Max_borrowed_Books = 3;
    private ArrayList<Transaction> HistoryOfTransaction;
    private ArrayList<Notification> BorrowerNotifications;

    // the constructor will deal with the array of users
    public Borrower(String BorrowerName, String BorrowerID) {
        this.BorrowerName = BorrowerName;
        this.BorrowerID = BorrowerID;
        Num_Of_borrowed_Books = 0;
    }

    public String GetBorrowerName() {
        return BorrowerName;
    }

    public String GetBorrowerID() {
        return BorrowerID;
    }

    public int Num_Of_borrowed_Books() {
        return Num_Of_borrowed_Books;
    }

    public int GetMaxBorrowedBooks() {
        return Max_borrowed_Books;
    }

    public void Add_Borrowed_Book() {

    }

    // To see The Transaction History
    public void Display_Borrower_History_Transaction() {
        System.out.println(BorrowerName + "'s Transaction");
        System.out.println("---------------------------------------------------");
        if (HistoryOfTransaction.isEmpty()) {
            System.out.println("You haven't made any borrowing transactions yet");
        } else {
            HistoryOfTransaction.trimToSize();
            for (int i = 0; i < HistoryOfTransaction.size(); i++) {
                Transaction TempTransaction = HistoryOfTransaction.get(i);
                System.out.println("Transaction " + (i + 1));
                System.out.println("Transaction ID: " + TempTransaction.getTransactionID());
                TempTransaction.getBookList().trimToSize();
                for (int j = 0; j < TempTransaction.getBookList().size(); j++) {
                    System.out.println("Book " + (j + 1));
                    Book TempBook = TempTransaction.getBookList().get(j);
                    System.out.println("Book Title: " + TempBook.getTitle());
                }
                System.out.println("Borrow Date: " + TempTransaction.getBorrowDate());
                System.out.println("Return Date: " + TempTransaction.getReturnDate());
                System.out.println("Transaction Status" + TempTransaction.GetTransactionStatus());
                if (TempTransaction.GetTransactionStatus().equals("Overdue")) {
                    System.out.println("Fine: " + TempTransaction.getFine());
                }

            }

        }

    }

    // To display a list of Available books when Borrower starts to make a transaction
    public void ShowBooksForBorrowTransaction(ArrayList<Book> Books) {
        Books.trimToSize();
        for (int i = 0; i < Books.size(); i++) {
            Book TempObj = Books.get(i);
            if (TempObj.isAvailable()) {
                System.out.println("Book " + (i + 1) + ":");
                System.out.println("Title: " + TempObj.getTitle());
                System.out.println("Author: " + TempObj.getAuthor());
                System.out.println("ID: " + TempObj.getBookId());
                System.out.println("--------------------------------------------");
            }
        }


//        public void Add_New_Transaction () {
//            System.out.println("New Transaction:");
//            System.out.println("---------------------------------------------------");
//            if (Num_Of_borrowed_Books >= Max_borrowed_Books) {
//
//                System.out.println("Sorry...You have reached the maximum number of borrowed books!\n");
//                System.out.println("---------------------------------------------------");
//                System.out.println("1. Back to the Main Menu");
//
//                //calling the function that contains the user dashboard
//            } else {
//
//            }
//
//
//        }


    }
}
