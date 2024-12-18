package Account;

public class IncorrectUsernameORPassword extends RuntimeException{
    IncorrectUsernameORPassword(){
        super("INCORRECT USERNAME OR PASSWORD");
    }
}
