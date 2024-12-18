package Account;

public class UniqueUsernameException extends RuntimeException{
    UniqueUsernameException(){
        super("USERNAME ALREADY EXISTS!");
    }
}
