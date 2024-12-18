package Account;

public class InvalidPhoneNumber extends RuntimeException{
    InvalidPhoneNumber(){
        super("INVALID PHONE NUMBER");
    }
}
