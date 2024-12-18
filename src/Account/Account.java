package Account;
import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
public abstract class  Account implements Serializable {
    private final String Account_Id;
    private String Username;
    private String Password;
    private String Name;
    private String E_mail;
    private String Phone_Number;
    private String Address;
    private boolean IsLoggedIn = false;
    private String Account_Type;
    private static int NomOfUsers = 0;
    protected boolean Admin=false;
    protected static final Set<String> Usernames = new HashSet<>();
    private static final Set<String>AccountIds =new HashSet<>();
    public Account(){
        this.Account_Id=GenerateAccountId();
    }
    public boolean SignUp(String Username, String Password, String Name, String E_mail, String Phone_Number, String Address) throws UniqueUsernameException,PasswordException ,InvalidEmail,InvalidPhoneNumber {
        if(UniqueUsername(Username)){
            this.Username=Username;
        }
        else{
            throw new UniqueUsernameException();
        }
        if(ValidPassword(Password)){
            this.Password=Password;
        }
        else{
            throw new PasswordException();
        }
        if(ValidEmail(E_mail)){
            this.E_mail=E_mail;
        }
        else{
            throw new InvalidEmail();
        }
        if(ValidPhoneNumber(Phone_Number)){
            this.Phone_Number=Phone_Number;
        }
        else{
            throw new InvalidPhoneNumber();
        }
        this.Name=Name;
        this.Address=Address;
        NomOfUsers++;
        Usernames.add(this.Username);
        return true;
    }

    public static boolean UniqueUsername(String Username)throws UniqueUsernameException {
        if (Usernames.contains(Username)) {
            throw new UniqueUsernameException();
        } else {
            return true;
        }
    }

    public static boolean ValidPassword(String Password)throws PasswordException {
        if (Password.length() >= 8 && Password.matches(".*[!@#$%&*].*") && Password.matches(".*[a-z].*") && Password.matches(".*[A-Z].*")&& Password.matches(".*[0-9].*")) {
            return true;
        }
        else {
            throw new PasswordException();
        }
    }
    public boolean LogIn(String Username,String Password,boolean X)throws IncorrectUsernameORPassword{
        if(this.Username.equals(Username)&&this.Password.equals(Password)){
            IsLoggedIn=true;
            return true;
        }
        else{
            if(X){
                throw new IncorrectUsernameORPassword();
            }
            else {
                return false;
            }
        }
    }
    public void LogOut(){
        IsLoggedIn=false;
    }
    public String GenerateAccountId(){
        String TempAccountId;
        Random Id = new Random();
        do {
            TempAccountId = String.format("%06d", Id.nextInt(1000000));
        }while(AccountIds.contains(TempAccountId));
        AccountIds.add(TempAccountId);
        return TempAccountId;
    }
    public boolean ChangePassword(String CurrentPassword,String NewPassword)throws IncorrectPasswordException,PasswordException,LogInException{
        if(IsLoggedIn) {
            if (!CurrentPassword.equals(this.Password)) {
                throw new IncorrectPasswordException();
            }
            if (!ValidPassword(NewPassword)) {
                throw new PasswordException();
            } else {
                this.Password = NewPassword;
                return true;
            }
        }
        else{
            throw new LogInException();
        }
    }
    public boolean ValidEmail(String E_mail){
        String E_mailPattern="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9._%+-]+\\.[A-Za-z]{2,}$";
        if(E_mail.matches(E_mailPattern)){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean ValidPhoneNumber(String PhoneNumber){
        String PhoneNumberPattern="^[0-9]{10,}$";
        if(PhoneNumber.matches(PhoneNumberPattern)){
            return true;
        }
        else{
            return false;
        }
    }
    public void ViewDetails()throws LogInException{
        if(IsLoggedIn){
            System.out.println("---------------------------------");
            System.out.println("ACCOUNT ID: "+this.Account_Id);
            System.out.println("USERNAME: "+this.Username);
            System.out.println("NAME: "+this.Name);
            System.out.println("E-MAIL: "+this.E_mail);
            System.out.println("PHONE NUMBER: "+this.Phone_Number);
            System.out.println("ADDRESS: "+this.Address);
            System.out.println("---------------------------------");
        }
        else{
            throw new LogInException();
        }
    }
    public boolean ChangeUsername(String Username)throws UniqueUsernameException,SameUsernameException,LogInException{
        if(IsLoggedIn) {
            if (this.Username.equals(Username)) {
                throw new SameUsernameException();
            } else {
                if (UniqueUsername(Username)) {
                    Usernames.remove(this.Username);
                    this.Username = Username;
                    Usernames.add(Username);
                    return true;
                } else {
                    throw new UniqueUsernameException();
                }
            }
        }
        else{
            throw new LogInException();
        }
    }
    public boolean DeleteAccount(String Username,String Password)throws LogInException{
        if(IsLoggedIn) {
            if (this.Username.equals(Username) && this.Password.equals(Password)) {
                Usernames.remove(Username);
                AccountIds.remove(this.Account_Id);
                System.out.println("ACCOUNT DELETED SUCCESSFULLY");
                return true;
            }
            else{
                throw new IncorrectPasswordException();
            }
        }
        else{
            throw new LogInException();
        }
    }


    public static void setNomOfUsers(int nomOfUsers) {
        NomOfUsers = nomOfUsers;
    }
    public void setAccount_Type(String account_Type) {
        Account_Type = account_Type;
    }
    public void setLoggedIn(boolean loggedIn) {
        IsLoggedIn = loggedIn;
    }
    public void setPassword(String password) {
        Password = password;
    }
    public void setName(String name) {
        Name = name;
    }
    public void setUsername (String username)throws SameUsernameException,UniqueUsernameException{
        if(this.Username.equals(username)) {
            throw new SameUsernameException();
        }
        else if(UniqueUsername(username)){
                Username=username;
        }
    }
    public void setE_mail (String e_mail)throws InvalidEmail{
        if(ValidEmail(e_mail)) {
            E_mail = e_mail;
        }
        else{
            throw new InvalidEmail();
        }
    }
    public void setPhone_Number (String phone_Number)throws InvalidPhoneNumber{
        if(ValidPhoneNumber(phone_Number)) {
            Phone_Number = phone_Number;
        }
        else{
            throw new InvalidPhoneNumber();
        }
    }
    public void setAddress (String address){
        Address = address;
    }
    public String getUsername () {
        return Username;
    }
    public String getPassword () {
        return Password;
    }
    public String getName () {
        return Name;
    }
    public String getE_mail () {
        return E_mail;
    }
    public String getPhone_Number () {
        return Phone_Number;
    }
    public String getAddress () {
        return Address;
    }
    public String getAccount_Id () {
        return Account_Id;
    }
    public boolean isLoggedIn() {
        return IsLoggedIn;
    }
    public static int getNomOfUsers() {
        return NomOfUsers;
    }
    public String getAccount_Type() {
        return Account_Type;
    }
    public static boolean SearchForUser(ArrayList<Account> Users,String Username,String Password){
        for(Account user:Users){
            if(user.getUsername().equals(Username)&&user.getPassword().equals(Password)){
                return true;
            }
        }
        return false;
    }

}

