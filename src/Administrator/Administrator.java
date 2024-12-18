package Administrator;
import Book.*;
import java.io.Serializable;
import java.util.ArrayList;
import Account.*;
import Customer.Customer;

public class Administrator extends Account implements Serializable {
   public Administrator(){
       super();
       Admin=true;
    }
    public Customer Add_User(){
       Customer NewCustomer=new Customer();
       return NewCustomer;
    }
    public enum Update_User_Option{
       Username,Name,E_mail,Phone_Number,Address
    }
    public Customer Update_User(String Username,ArrayList<Account> Customers)throws CustomerNotFound{
       if(Usernames.contains(Username)) {
           for (Account customer : Customers) {
               if (customer instanceof Customer && customer.getUsername().equals(Username)) {
                   Customer UpdateCustomer = (Customer) customer;
                   UpdateCustomer.LogIn(UpdateCustomer.getUsername(), UpdateCustomer.getPassword(),false);
                   return UpdateCustomer;
               }
           }
       }
           throw new CustomerNotFound();
    }
    public void Remove_User(String Username,ArrayList<Account> Customers)throws CustomerNotFound{
       if(Usernames.contains(Username)) {
           for (Account customer : Customers) {
               if (customer instanceof Customer && customer.getUsername().equals(Username)) {
                   customer.LogIn(customer.getUsername(),customer.getPassword(),false);
                   customer.DeleteAccount(customer.getUsername(),customer.getPassword());
                   Customers.remove(customer);
                   break;
               }
           }
       }
       else{
           throw new CustomerNotFound();
       }
    }
    public Book Add_Book( String title, String author, int publicationYear, boolean isAvailable, double price,String Category,int Stock){
       return new Book( title, author, publicationYear, isAvailable,  price, Category,Stock);
    }
    public enum Update_Book_Option{
       BookId,BookTitle,BookAuthor,PublicationYear,KindOfBook,Availability,Price
    }
    public void Update_Book(String bookId,ArrayList<Book> Books,String Update,Update_Book_Option Choice){
        boolean Exists=false;
        for(Book book:Books){
            if( book.getBookId().equals(bookId)){
                Exists=true;
                switch(Choice){
                    case BookTitle:book.setTitle(Update);
                        break;
                    case BookAuthor:book.setAuthor(Update);
                        break;
                    case PublicationYear:book.setPublicationYear(Integer.parseInt(Update));
                        break;
                    case KindOfBook:book.setCategory(Update);
                        break;
                    case Availability:book.setAvailable(Boolean.parseBoolean(Update));
                        break;
                    case Price:book.setPrice(Float.parseFloat(Update));
                    break;
                }
            }
        }
        if(!Exists){
            System.out.println("Book Not Found");
        }
    }
    public void Remove_Book(String BookId,ArrayList<Book> Books){
        boolean Exists=false;
        for(Book book:Books){
            if(book.getBookId().equals(BookId)){
                Exists=true;
                Books.remove(book);
                System.out.println("Book Removed Successfully");
            }
        }
        if(!Exists){
            System.out.println("Book Not Found");
        }
    }
    public static void Display_InventoryStatus(ArrayList<Book> Books){
       int Available_Books=0;
       int UnAvailable_Books=0;
       StringBuilder AvailableBooks=new StringBuilder();
        StringBuilder UnAvailableBooks=new StringBuilder();
        for(Book book:Books){
            if(book.isAvailable()){
                Available_Books++;
                AvailableBooks.append(book.getTitle()).append('\n');
            }
            else{
                UnAvailable_Books++;
                UnAvailableBooks.append(book.getTitle()).append('\n');
            }
        }
        System.out.println("Number Of Available Books: "+ Available_Books);
        System.out.println("Number Of UnAvailable Books: "+ UnAvailable_Books);
        if(Available_Books>0){
            System.out.println("Available Books: ");
            System.out.println(AvailableBooks.toString());
        }
        else{
            System.out.println("No Available Books");
        }
        if(UnAvailable_Books>0){
            System.out.println("UnAvailable Books: ");
            System.out.println(UnAvailableBooks.toString());
        }
        else{
            System.out.println("No UnAvailable Books");
       }
    }
    public void UpdateStock(String BookID,int NewStock,ArrayList<Book> Books){
       for(Book book:Books){
           if(BookID.equals(book.getBookId())){
               book.setStock(NewStock);
           }
       }
    }
}
