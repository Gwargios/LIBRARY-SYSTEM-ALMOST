package Account;

public class IncorrectPasswordException extends RuntimeException{
    IncorrectPasswordException(){
        super("INCORRECT PASSWORD!");
    }
}
