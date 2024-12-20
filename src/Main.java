
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import Account.*;
import Administrator.*;
import Book.*;
import Cart.*;
import Customer.*;
import Transaction.*;
import Borrower.*;
import Order.Order;

public class Main  {
    public static Scanner Input(){
        Scanner Input=new Scanner(System.in);
        return Input;
    }
    public static  void ReadingData(ArrayList<Account> Accounts,ArrayList<Book>Books){
        File file=new File("Details.ser");
        System.out.println(file.getAbsolutePath());
        try{
            ObjectInputStream obj=new ObjectInputStream(new FileInputStream(file));
            while(true){
                Object object=obj.readObject();
                if(object instanceof Account){
                    Accounts.add((Account)object);
                }
                if(object instanceof Book){
                    Books.add((Book) object);
                }
            }
        }  catch (EOFException eof) {
            System.out.println("End of file reached.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    public static void WritingData(ArrayList<Account> Accounts,ArrayList<Book>Books){
        File file=new File("Details.ser");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Account user : Accounts) {
                outputStream.writeObject(user);
            }
            for (Book book : Books) {
                outputStream.writeObject(book);
            }
            System.out.println("Accounts saved successfully!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error during writing: " + e.getMessage());
        }
    }
    public static void main(String[] args) {
        ArrayList<Account> Users= new ArrayList<>();
        ArrayList<Customer>Customers=new ArrayList<>();
        ArrayList<Book>Books=new ArrayList<>();
        ReadingData(Users,Books);
        Administrator Admin1=new Administrator();
        Customer Cust1=new Customer();
        Book book1=new Book("Lion King","ahmed",2024,true,24F,"sci fi",5);
        Book book100=new Book("My Life","ahmed",2024,true,24F,"sci fi",5);
        Admin1.SignUp("Haitch","7112005aA@","Ahmed","ahmedhesham@gmail.com","01210650900","masr el gedida");
        Cust1.SignUp("Haitch2","7112005aA@","Ahmed","ahmedhesham@gmail.com","01210650900","masr el gedida");
        Users.add(Admin1);
        Users.add(Cust1);
        Books.add(book1);
        Books.add(book100);
        int Choice;
        do {
            System.out.println("1-LOG IN" + '\t' + "2-SIGN UP" + '\t' + "3-EXIT");
            Choice = Input().nextInt();
            if (Choice == 1) {
                boolean LoggedIn;
                do {
                    LoggedIn = false;
                    try {
                        String Username;
                        String Password;
                        System.out.print("ENTER USERNAME: ");
                        Username = Input().nextLine();
                        System.out.print("ENTER PASSWORD: ");
                        Password = Input().nextLine();
                        boolean Y=false;
                        int Count=1;
                        for (Account user : Users) {
                            Y=(Count++==Users.size());
                            if (user.LogIn(Username, Password,Y)) {
                                System.out.println("LOGGED IN SUCCESSFULLY");
                                if (user instanceof Administrator) {
                                    Administrator Admin = (Administrator) user;
                                    int Choice4;
                                    do {
                                        System.out.println("Dashboard");
                                        System.out.println("1-ADD USER" + '\t' + "2-UPDATE USER" + '\t' + "3-REMOVE USER" + '\n'
                                                + "4-ADD BOOK" + '\t' + "5-UPDATE BOOK" + '\t' + "6-REMOVE BOOK");
                                        int Choice2 = Input().nextInt();
                                        switch (Choice2) {

                                            case 1: {
                                                int Choice3;
                                                boolean Success;
                                                do {
                                                    Success = false;
                                                    try {
                                                        Customer NewCustomer = Admin.Add_User();
                                                        System.out.print("ENTER USER DETAILS" + '\n' + "USERNAME: ");
                                                        String NewUsername = Input().nextLine();
                                                        System.out.print("PASSWORD: ");
                                                        String NewPassword = Input().nextLine();
                                                        System.out.print("NAME: ");
                                                        String NewName = Input().nextLine();
                                                        System.out.print("E-MAIL: ");
                                                        String NewEmail = Input().nextLine();
                                                        System.out.print("PHONE NUMBER: ");
                                                        String NewPhoneNumber = Input().nextLine();
                                                        System.out.print("ADDRESS: ");
                                                        String NewAddress = Input().nextLine();
                                                        NewCustomer.SignUp(NewUsername, NewPassword, NewName, NewEmail, NewPhoneNumber, NewAddress);
                                                        Users.add(NewCustomer);
                                                        System.out.println("USER ADDED SUCCESSFULLY");
                                                        Success = true;
                                                        break;
                                                    } catch (UniqueUsernameException | PasswordException |
                                                             InvalidEmail |
                                                             InvalidPhoneNumber e) {
                                                        System.out.println(e.getMessage());
                                                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                        Choice3 = Input().nextInt();

                                                    }
                                                } while (Choice3 == 1 && !Success);
                                                break;
                                            }
                                            case 2: {
                                                try {
                                                    System.out.print("ENTER USERNAME OF USER YOU WANT TO UPDATE: ");
                                                    String UpdateUsername = Input().nextLine();
                                                    Customer UpdateCustomer = Admin.Update_User(UpdateUsername, Users);
                                                    UpdateCustomer.ViewDetails();
                                                    UpdateCustomer.LogOut();
                                                    System.out.println("WHAT WOULD YOU LIKE TO UPDATE?");
                                                    int Choice7;
                                                    do {
                                                        System.out.println("1-USERNAME" + '\t' + "2-NAME" + '\t' + "3-E-MAIL" + '\t' + "4-PHONE NUMBER");
                                                        int Choice3 = Input().nextInt();
                                                        switch (Choice3) {
                                                            case 1: {
                                                                int Choice5;
                                                                boolean Success;
                                                                do {
                                                                    Success = false;
                                                                    try {
                                                                        System.out.print("ENTER NEW USERNAME: ");
                                                                        String NewUsername = Input().nextLine();
                                                                        UpdateCustomer.setUsername(NewUsername);
                                                                        System.out.println("USERNAME UPDATED SUCCESSFULLY");
                                                                        Success = true;
                                                                        break;
                                                                    } catch (UniqueUsernameException |
                                                                             SameUsernameException e) {
                                                                        System.out.println(e.getMessage());
                                                                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                                        Choice5 = Input().nextInt();
                                                                    }

                                                                } while (Choice5 == 1 && !Success);
                                                                break;
                                                            }
                                                            case 2: {
                                                                System.out.print("ENTER NEW NAME: ");
                                                                String NewName = Input().nextLine();
                                                                UpdateCustomer.setName(NewName);
                                                                System.out.println("NAME UPDATED SUCCESSFULLY");
                                                                break;
                                                            }
                                                            case 3: {
                                                                int Choice5;
                                                                boolean Success;
                                                                do {
                                                                    Success = false;
                                                                    try {
                                                                        System.out.print("ENTER NEW E-MAIL: ");
                                                                        String NewEmail = Input().nextLine();
                                                                        UpdateCustomer.setE_mail(NewEmail);
                                                                        System.out.println("E-MAIL CHANGED SUCCESSFULLY");
                                                                        Success = true;
                                                                        break;
                                                                    } catch (InvalidEmail e) {
                                                                        System.out.println(e.getMessage());
                                                                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                                        Choice5 = Input().nextInt();
                                                                    }

                                                                } while (Choice5 == 1 && !Success);
                                                                break;
                                                            }
                                                            case 4: {
                                                                int Choice6;
                                                                boolean Success;
                                                                do {
                                                                    Success = false;
                                                                    try {
                                                                        System.out.print("ENTER NEW PHONE NUMBER: ");
                                                                        String NewPhoneNumber = Input().nextLine();
                                                                        UpdateCustomer.setPhone_Number(NewPhoneNumber);
                                                                        System.out.println("PHONE NUMBER CHANGED SUCCESSFULLY");
                                                                        Success = true;
                                                                        break;
                                                                    } catch (InvalidPhoneNumber e) {
                                                                        System.out.println(e.getMessage());
                                                                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                                        Choice6 = Input().nextInt();
                                                                    }
                                                                } while (Choice6 == 1 && !Success);
                                                                break;
                                                            }
                                                        }
                                                        System.out.println("WOULD YOU LIKE TO UPDATE ANYTHING ELSE?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                        Choice7 = Input().nextInt();
                                                    } while (Choice7 == 1);
                                                } catch (CustomerNotFound e) {
                                                    System.out.println(e.getMessage());
                                                }
                                                break;
                                            }
                                            case 3: {
                                                int Choice7;
                                                boolean Success;
                                                do {
                                                    Success = false;
                                                    try {
                                                        System.out.print("ENTER USERNAME OF USER YOU WOULD LIKE TO REMOVE: ");
                                                        String RemoveUsername = Input().nextLine();
                                                        Admin.Remove_User(RemoveUsername, Users);
                                                        Success = true;
                                                        break;
                                                    } catch (CustomerNotFound e) {
                                                        System.out.println(e.getMessage());
                                                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                        Choice7 = Input().nextInt();
                                                    }
                                                } while (Choice7 == 1 && !Success);
                                                break;
                                            }
                                        }
                                        System.out.println("ANOTHER OPERATION?" + '\n' + "1-YES" + '\t' + "2-NO");
                                        Choice4 = Input().nextInt();
                                    } while (Choice4 == 1);
                                } else if (user instanceof Customer) {
                                    Customer customer = (Customer) user;
                                    int CustInput;
                                    do {
                                        System.out.println("1. Search\n" +
                                                "2. Add purchase\n" +
                                                "3. Borrow a Book ( Add Transaction )\n" +
                                                "4. Return Borrowed Books\n" +
                                                "5. Show History of Transaction\n" +
                                                //"6. Available Discount\n" +
                                                "7. Available Notification\n" +
                                                "8. View Order History\n"+
                                                "9. Rate a Book\n" +
                                                "10. Rate Us\n" +
                                                "11. Display All Available Books:\n" +    
                                                "12.Log Out");
                                        CustInput = Input().nextInt();
                                        switch (CustInput) {
                                            case 1: {
                                                System.out.println("1. by Author\n" +
                                                        "\t2. by title");
                                                CustInput = Input().nextInt();
                                                switch (CustInput) {
                                                    case 1: {
                                                        do {
                                                            do {
                                                                try {
                                                                    System.out.print("ENTER AUTHOR NAME: ");
                                                                    String AuthorName = Input().nextLine();
                                                                    customer.searchBooksByAuthor(Books, AuthorName);
                                                                } catch (SearchNotFound e) {
                                                                    System.out.println(e.getMessage());
                                                                    System.out.println("SEARCH AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                                    CustInput = Input().nextInt();
                                                                }
                                                            }while(CustInput==1);
                                                            System.out.println("DO ANOTHER SEARCH?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                            CustInput = Input().nextInt();
                                                        } while (CustInput == 1);
                                                        break;
                                                    }
                                                    case 2: {
                                                        do {
                                                            do {
                                                                try {
                                                                    System.out.print("ENTER TITLE NAME: ");
                                                                    String TitleName = Input().nextLine();
                                                                    customer.searchBooksByAuthor(Books, TitleName);
                                                                } catch (SearchNotFound e) {
                                                                    System.out.println(e.getMessage());
                                                                    System.out.println("SEARCH AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                                    CustInput = Input().nextInt();
                                                                }
                                                            }while(CustInput==1);
                                                            System.out.println("DO ANOTHER SEARCH?" + '\n' + "1-YES" + '\t' + "2-NO");
                                                            CustInput = Input().nextInt();
                                                        } while (CustInput == 1);
                                                        break;
                                                    }


                                                }
                                            }
                                            case 2: {

                                                customer.displayBooksForPurchase(Books);
                                                int PurchaseChoice;
                                                do {
                                                    System.out.print("Add A Book To Cart? (1-YES, 2-NO): ");
                                                    PurchaseChoice = Input().nextInt();
                                                    if (PurchaseChoice == 1) {
                                                        System.out.print("Enter the title of the book you want to add: ");
                                                        String title = Input().nextLine();
                                                        Book book = null;
                                                        for (Book b : Books) {
                                                            if (b.getTitle().equals(title)) {
                                                                book = b;
                                                                break;
                                                            }
                                                        }
                                                        if (book != null) {
                                                            customer.addToCart(book);
                                                            customer.viewCart();
                                                        } else {
                                                            System.out.println("Book not found.");
                                                        }
                                                    }
                                                } while (PurchaseChoice == 1);
                                                customer.viewCart();
                                                int cartChoice;
                                                do {
                                                    System.out.println("What Would You Like To Do Next? ");
                                                    System.out.println("1. Add Another Book to Cart");
                                                    System.out.println("2. Change The Quantity Of Any Book In The Cart");
                                                    System.out.println("3. Clear Cart From All Books");
                                                    System.out.println("4. Remove A Book Entirely From Cart");
                                                    System.out.println("5. Already Done Shopping");
                                                    System.out.println("6. View Cart");
                                                    cartChoice = Input().nextInt();
                                                    switch (cartChoice) {
                                                        case 1:
                                                            System.out.print("Enter the title of the book you want to add: ");
                                                            String title = Input().nextLine();
                                                            Book book = null;
                                                            for (Book b : Books) {
                                                                if (b.getTitle().equals(title)) {
                                                                    book = b;
                                                                    break;
                                                                }
                                                            }
                                                            if (book != null) {
                                                                customer.addToCart(book);
                                                                customer.viewCart();
                                                            } else {
                                                                System.out.println("Book not found."); //change it to throw an exception
                                                            }
                                                            break;
                                                        case 2:
                                                            System.out.print("Enter the title of the book you want to change quantity: ");
                                                            String title2 = Input().nextLine();
                                                            Book book2 = null;
                                                            for (Book b : Books) {
                                                                if (b.getTitle().equals(title2)) {
                                                                    book2 = b;
                                                                    break;
                                                                }
                                                            }

                                                            if (book2 != null) {
                                                                System.out.print("Enter the new quantity: ");
                                                                int quantity = Input().nextInt();
                                                                for(CartItem item:customer.getCart().getItems()){
                                                                    if(item.getBook().getTitle().equals(title2)){
                                                                        item.setQuantity(quantity);
                                                                        break;
                                                                    }
                                                                }
                                                                customer.viewCart();
                                                            } else {
                                                                System.out.println("Book not found."); //replace it with exception
                                                            }
                                                            break;
                                                        case 3:
                                                            customer.getCart().clearCart();
                                                            customer.viewCart();
                                                            break;
                                                        case 4:
                                                            System.out.print("Enter the title of the book you want to remove: ");
                                                            String title3 = Input().nextLine();
                                                            Book book3 = null;
                                                            for (Book b : Books) {
                                                                if (b.getTitle().equals(title3)) {
                                                                    book3 = b;
                                                                    break;
                                                                }
                                                            }
                                                            if (book3 != null) {
                                                                customer.removeFromCart(book3);
                                                                customer.viewCart();
                                                            } else {
                                                                System.out.println("Book not found.");
                                                            }
                                                            break;
                                                        case 5:
                                                            customer.placeOrder();
                                                            customer.getOrderHistory().getLast().displayOrderDetails();
                                                            int orderChoice;
                                                            do {
                                                                System.out.println("What Would You Like To Do Next?");
                                                                System.out.println("1. Change Order Details");
                                                                System.out.println("2. Cancel Order");
                                                                System.out.println("3. Finish and Choose Payment Method");
                                                                orderChoice = Input().nextInt();
                                                                switch (orderChoice) {
                                                                    case 1:
                                                                        System.out.println("1. Change Quantity for a Specific Order Item");
                                                                        System.out.println("2. Remove an Order Item");
                                                                        int orderDetailChoice = Input().nextInt();
                                                                        switch (orderDetailChoice) {
                                                                            case 1:
                                                                                System.out.print("Enter the title of the book you want to change quantity: ");
                                                                                String title4 = Input().nextLine();
                                                                                Book book4 = null;
                                                                                for (Book b : Books) {
                                                                                    if (b.getTitle().equals(title4)) {
                                                                                        book4 = b;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                if (book4 != null) {
                                                                                    System.out.print("Enter the new quantity: ");
                                                                                    int quantity2 = Input().nextInt();
                                                                                    for(CartItem item:customer.getCart().getItems()){
                                                                                        if(item.getBook().getTitle().equals(title4)){
                                                                                            item.setQuantity(quantity2);
                                                                                            break;
                                                                                        }
                                                                                    }
                                                                                    customer.getOrderHistory().getLast().displayOrderDetails();
                                                                                } else {
                                                                                    System.out.println("Book not found.");
                                                                                }
                                                                                break;
                                                                            case 2:
                                                                                System.out.print("Enter the title of the book you want to remove: ");
                                                                                String title5 = Input().nextLine();
                                                                                Book book5 = null;
                                                                                for (Book b : Books) {
                                                                                    if (b.getTitle().equals(title5)) {
                                                                                        book5 = b;
                                                                                        break;
                                                                                    }
                                                                                }
                                                                                if (book5 != null) {
                                                                                    customer.getOrderHistory().getLast().removeItem(book5);
                                                                                    customer.getOrderHistory().getLast().displayOrderDetails();
                                                                                } else {
                                                                                    System.out.println("Book not found.");
                                                                                }
                                                                                break;
                                                                        }
                                                                        break;
                                                                    case 2:{
                                                                        customer.getOrderHistory().remove(customer.getOrderHistory().getLast());
                                                                        System.out.println("Order Cancelled Successfully");
                                                                    }
                                                                        break;
                                                                    case 3:
                                                                        int paymentMethod;
                                                                        do {
                                                                            System.out.println("Payment Method: ");
                                                                            System.out.println("1. Cash");
                                                                            System.out.println("2. Credit Card");
                                                                             paymentMethod = Input().nextInt();
                                                                            switch (paymentMethod) {
                                                                                case 1:
                                                                                    System.out.println("Please Prepare " + customer.getOrderHistory().getLast().getTotalAmount() + " For delivery Person");
                                                                                    break;
                                                                                case 2:
                                                                                    System.out.print("Enter Cardholder Name: ");
                                                                                    String cardholderName = Input().nextLine();
                                                                                    System.out.print("Enter Card Number: ");
                                                                                    String cardNumber = Input().next();
                                                                                    try {
                                                                                        customer.Payment(cardholderName, cardNumber);
                                                                                    } catch (PaymentFailedException e) {
                                                                                        System.out.println(e.getMessage());
                                                                                        paymentMethod = 0;
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }while(paymentMethod==0);
                                                                        break;
                                                                }
                                                            } while (orderChoice != 3);
                                                            break;
                                                        case 6:{
                                                            customer.viewCart();
                                                        }
                                                    }
                                                } while (cartChoice != 5);
                                                break;
                                            }
                                            case 3: {
                                                //********borrow Transaction ******* Sara Tarek ************

//                public void Add_New_Transaction () {
                                                Transaction Temp_Transaction = new Transaction();
                                                System.out.println("New Transaction:");
                                                System.out.println("---------------------------------------------------");
                                                int trans_choice=0;
                                                do {
                                                    if (customer.GetBorrower_info().Num_Of_borrowed_Books() < customer.GetBorrower_info().GetMaxBorrowedBooks()) {
                                                        // list to show the available book
                                                        customer.GetBorrower_info().ShowBooksForBorrowTransaction(Books);
                                                        Book Temp_book = null;
                                                        System.out.println("Enter the Book Id: ");
                                                        String Temp_Book_Id = Input().next();
                                                        boolean book_Exists = false;
                                                        for (int i = 0; i < Books.size(); i++) {
                                                            Temp_book = Books.get(i);
                                                            if (Temp_Book_Id.equals(Temp_book.getBookId()) && Temp_book.isAvailable()) {
                                                                book_Exists = true;
                                                            }
                                                            if (book_Exists)
                                                                break;

                                                        }
                                                        if (!(book_Exists)) {
                                                            System.out.println("Book Is not found!");
                                                        } else {

                                                            Temp_Transaction.getBookList().add(Temp_book);
                                                            System.out.println("The Book has been added successfully. ");
                                                            System.out.println("1. Add another book\n2. Complete Transaction\n3. Cancel Transaction");
                                                             trans_choice = Input().nextInt();
                                                            switch (trans_choice) {
                                                                case 1: {
                                                                    // call the method of adding a new book
                                                                    break;
                                                                }
                                                                case 2: {
                                                                    Temp_Transaction.getBookList().toString().trim();
                                                                    customer.GetBorrower_info();
                                                                    // trim the arraylist of books within Temp_Transaction
                                                                    //add Temp_Transaction to the current customer arraylist of Transaction
                                                                    //add Temp_Transaction to the arraylist of System Transactions
                                                                    System.out.println("Transaction has been added successfully.");
                                                                    break;
                                                                }
                                                                case 3: {
                                                                    Temp_Transaction = null;
                                                                    System.out.println("The Transaction has been cancelled.");
                                                                    //calling the Method that contains the user dashboard

                                                                }

                                                            }
                                                        }

                                                    } else {
                                                        if (Temp_Transaction.getBookList().isEmpty()) {
                                                            System.out.println("Sorry...You have reached the maximum number of borrowed books!\n");
                                                            System.out.println("---------------------------------------------------");
                                                            System.out.println("1. Back to the Main Menu");

                                                            //calling the function that contains the user dashboard

                                                        } else {
                                                            System.out.println("1. Complete Transaction\n2. Cancel Transaction");

                                                        }


                                                    }
                                                }while(trans_choice==1);
                                            }
                                            case 8:{
                                                try {
                                                    customer.OrderHistory();
                                                }catch (NoOrdersFound e){
                                                    System.out.println(e.getMessage());
                                                }
                                            }
                                            case 9:{

                                            }
                                            case 11:{
                                                System.out.println("Displaying All Available Books:");
                                                for (Book book : Books) {
                                                    if (book.isAvailable()) {
                                                        book.displayDetails();
                                                        System.out.println();
                                                    }
                                                }
                                            }    
                                        }
                                    } while (CustInput != 12);


                                }
                                LoggedIn = true;
                                break;
                            }

                        }
                    } catch (IncorrectUsernameORPassword e) {
                        System.out.println(e.getMessage());
                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                        Choice = Input().nextInt();
                    }
                } while (!LoggedIn && Choice == 1);
            }
            else if(Choice==2){
                Customer NewCustomer=new Customer();
                int CustInput;
                do {
                    try {
                        System.out.print("ENTER USER DETAILS" + '\n' + "USERNAME: ");
                        String NewUsername = Input().nextLine();
                        System.out.print("PASSWORD: ");
                        String NewPassword = Input().nextLine();
                        System.out.print("NAME: ");
                        String NewName = Input().nextLine();
                        System.out.print("E-MAIL: ");
                        String NewEmail = Input().nextLine();
                        System.out.print("PHONE NUMBER: ");
                        String NewPhoneNumber = Input().nextLine();
                        System.out.print("ADDRESS: ");
                        String NewAddress = Input().nextLine();
                        NewCustomer.SignUp(NewUsername, NewPassword, NewName, NewEmail, NewPhoneNumber, NewAddress);
                        Users.add(NewCustomer);
                        System.out.println("USER CREATED SUCCESSFULLY");
                        CustInput=2;
                    } catch (UniqueUsernameException | PasswordException | InvalidEmail | InvalidPhoneNumber e) {
                        System.out.println(e.getMessage());
                        System.out.println("TRY AGAIN?" + '\n' + "1-YES" + '\t' + "2-NO");
                        CustInput = Input().nextInt();
                    }
                }while(CustInput==1);
            }
        }while(Choice!=3);
        WritingData(Users,Books);
    }
}
