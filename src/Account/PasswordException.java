package Account;

public class PasswordException extends RuntimeException{
    PasswordException(){
        super("PASSWORD MUST HAVE ATLEAST 8 CHARACTERS " +'\n'+
                "1 UPPERCASE 1 LOWERCASE AND 1 SPECIAL CHARACTER");
    }
}
