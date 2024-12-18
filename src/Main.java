
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import Account.*;
import Administrator.*;
import Book.*;
import Borrower.*;
import Cart.*;
import Customer.*;
import Discount.*;
import Notification.*;
import Order.*;
import Rating.*;
import Transaction.*;

public class Main {
    public static Scanner Input(){
        Scanner Input=new Scanner(System.in);
        return Input;
    }
    public static  void ReadingData(ArrayList<Account> Accounts){
        File file=new File("Details.ser");
        System.out.println(file.getAbsolutePath());
        try{
            ObjectInputStream obj=new ObjectInputStream(new FileInputStream(file));
            while(true){
                Object object=obj.readObject();
                if(object instanceof Account){
                    Accounts.add((Account)object);
                }
            }
        }  catch (EOFException eof) {
            System.out.println("End of file reached.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    public static void WritingData(ArrayList<Account> Accounts){
        File file=new File("Details.ser");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            for (Account user : Accounts) {
                outputStream.writeObject(user);
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
        ReadingData(Users);
//        Administrator Admin1=new Administrator();
//        Customer Cust1=new Customer();
//        Admin1.SignUp("Haitch","7112005aA@","Ahmed","ahmedhesham@gmail.com","01210650900","masr el gedida");
//        Cust1.SignUp("Haitch2","7112005aA@","Ahmed","ahmedhesham@gmail.com","01210650900","masr el gedida");
//        Users.add(Admin1);
//        Users.add(Cust1);
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
                                    Customer Customer = (Customer) user;
                                    int CustInput;
                                    do {
                                        System.out.println("1. Search\n" +
                                                "2. Add purchase\n" +
                                                "3. Borrow a Book ( Add Transaction )\n" +
                                                "4. Return Borrowed Books\n" +
                                                "5. Show History of Transaction\n" +
                                                "6. Available Discount\n" +
                                                "7. Available Notification\n" +
                                                "8. Rate a Book\n" +
                                                "9. Rate Us\n" +
                                                "10.Log Out");
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
                                                                    Customer.searchBooksByAuthor(Books, AuthorName);
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
                                                                    Customer.searchBooksByAuthor(Books, TitleName);
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
                                        }
                                    } while (CustInput != 10);


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
        WritingData(Users);
    }
}