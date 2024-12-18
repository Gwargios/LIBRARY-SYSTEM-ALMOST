package Account;

public class InvalidEmail extends RuntimeException{
    InvalidEmail(){
        super("INVALID E-MAIL");
    }
}
