package Account;

public class SameUsernameException extends RuntimeException{
    SameUsernameException() {
        super("CANNOT CHANGE WITH SAME USERNAME");
    }
}
