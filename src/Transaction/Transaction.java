package Transaction;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Random;
import Book.*;
import Borrower.*;

public class Transaction {
    int transactionID;
    private ArrayList<Book> BookList;

    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String TransactionStatus ;
    private double fine = 0; // Late return fees
    private double finePerDay = 10; // Late fee per day By default it's 10

    public Transaction(){
    }

    public Transaction( LocalDate borrowDate, LocalDate returnDate){
        //this.book = book;

        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    private String generateTransactionId(ArrayList<Transaction> Transactions) {
        Random random = new Random();
        String id;
        do {
            id = String.format("%06d", random.nextInt(1000000));
        } while (!Transactions.contains(id));
        return id;
    }

    public double remainingDays(LocalDate currentDate){ // to check how many remaining days until return date
        return ChronoUnit.DAYS.between(returnDate, currentDate);
    }
    public double calculateFine(){ // finePerDay can be determined by admin
        LocalDate currentDate = LocalDate.now();
        if(currentDate.isAfter(this.returnDate)){
            double days = remainingDays(currentDate);
            fine = finePerDay * days;
        }
        else
            fine = 0;
        return fine;
    }

    public double getFine() {
        calculateFine();
        return fine;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public ArrayList<Book> getBookList() {
        return BookList;
    }

    public String GetTransactionStatus() {
        return TransactionStatus;
    }


//    public void setBook(Book book) {
//        this.BookList = book;
//    }


    public void SetTransactionStatus (String TransactionStatus){
        this.TransactionStatus = TransactionStatus ;
        //Still Valid
        // Overdue
        // Returned
    }



    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getFinePerDay() {
        return finePerDay;
    }

    public void setFinePerDay(double finePerDay) { //
        this.finePerDay = finePerDay;
    }

    public String toString(Borrower borrower) {
        return "Transaction ID: " + transactionID /*+ " Book: " + book.getBookId()*/ + " Borrower: " + borrower.GetBorrowerName() + " Borrow date: " +
                borrowDate + " Return Date: " + returnDate + " Fine: " + fine;
    }

}
