package Transaction;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import Borrower.*;
import Book.*;
public class TransactionManager {
    private static Set<Integer> transactionIDs = new HashSet<>();
    private Map<Integer, Transaction> transactionMap = new HashMap<>();
    FileWriter fw;


    public void addTransaction(Book book, Borrower borrower, LocalDate borrowDate, LocalDate returnDate, int transactionID) throws IllegalArgumentException{
        if(transactionMap.containsKey(transactionID)){
            throw new IllegalArgumentException("Transaction ID must be unique");
        }
        Transaction t = new Transaction(borrowDate, returnDate);
        transactionMap.put(transactionID, t);
        transactionIDs.add(transactionID);
        try {
            fw = new FileWriter("Save.txt");
            fw.append("Transaction ID: " + transactionID + " Book: " + book.getBookId() + " Borrower: " +
                    borrower.GetBorrowerName() + " Borrow date: " + borrowDate + " Return Date: " + returnDate);
        } catch (IOException ex) {
            Logger.getLogger(TransactionManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void addTransaction(Transaction t) throws IllegalArgumentException{
        if(transactionMap.containsKey(t.transactionID)){
            throw new IllegalArgumentException("Transaction ID must be unique");
        }
        transactionMap.put(t.transactionID, t);
        transactionIDs.add(t.transactionID);
    }

    public Transaction getTransaction(int transactionID){
        return transactionMap.get(transactionID);
    }

//    public double calculateFine(int transactionID, LocalDate currentDate){
//        Transaction t = transactionMap.get(transactionID);
//        if (t != null){
//            return t.calculateFine(currentDate);
//        }
//        else
//            throw new IllegalArgumentException("Transaction ID not found: " + transactionID);
//    }

    public void updateReturnDate(int transactionID, LocalDate newDate){
        Transaction t = transactionMap.get(transactionID);
        if(t != null){
            t.setReturnDate(newDate);
        }
        else
            throw new IllegalArgumentException("Transaction ID not found: " + transactionID);
    }

    public void updateFinePerDay(int transactionID, double newFine){ // admin can update fine per day for specified transaction
        Transaction t = transactionMap.get(transactionID);
        if(t != null){
            t.setFinePerDay(newFine);
        }
        else
            throw new IllegalArgumentException("Transaction ID not found: " + transactionID);
    }

    public void removeTransaction(int transactionID){
        Transaction t = transactionMap.remove(transactionID);
        if(t != null){
            transactionIDs.remove(transactionID);
        }
        else
            throw new IllegalArgumentException("Transaction ID not found: " + transactionID);
    }
}
