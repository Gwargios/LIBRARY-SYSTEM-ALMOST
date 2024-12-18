package Account;

public class LogInException extends RuntimeException{
    LogInException(){
        super("YOU ARE LOGGED OUT YOU NEED TO LOG IN");
    }
}
