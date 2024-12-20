package Customer;

public class NoOrdersFound extends RuntimeException{
    NoOrdersFound(){
        super("You Haven't Placed Any Orders Yet");
    }
}
