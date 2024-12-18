package Administrator;

public class CustomerNotFound extends RuntimeException{
    CustomerNotFound(){
        super("USER NOT FOUND");
    }
}
