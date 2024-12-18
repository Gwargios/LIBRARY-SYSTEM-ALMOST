package Cart;
import java.io.Serializable;
import java.util.ArrayList;
import Book.*;
public class Cart implements Serializable {
    private ArrayList<CartItem> items;
    public Cart() {
        items = new ArrayList<>();
    }

    public void addToCart(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot add a null book to the cart.");
        }
        if (book.getStock() == 0) {
            throw new IllegalArgumentException("SORRY, That book is not available right now");
        }
        for (CartItem item : items) {
            if (item.getBook().getBookId().equals(book.getBookId()))
            {
                item.setQuantity(item.getQuantity() + 1);
                System.out.printf("Increased quantity of '%s' to %d.%n", book.getTitle(), item.getQuantity());
                return;
            }
        }

        items.add(new CartItem(book, 1));
        System.out.printf("Added '%s' to the cart with quantity 1.%n", book.getTitle());
    }

    public void removeFromCart(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Cannot remove a null book from the cart.");
        }
        for (CartItem item : items)
        {
            if (item.getBook().getBookId().equals(book.getBookId())) {
                items.remove(item);
                System.out.printf("Removed '%s' from the cart.%n", book.getTitle());
                return; //I used return instead of break to exit from the entire for each loop as soon as the condition is true
            }
        }
        System.out.printf("Book '%s' not found in the cart.%n", book.getTitle());
    }


    public double calculateTotal() {
        double total = 0.0;
        for (CartItem item : items) {
            total += item.getTotalPrice();
        }
        return total;
    }

    public void clearCart() {
        items.clear();
        System.out.println("Cart has been cleared.");
    }

    public void displayCart() {
        if (items.isEmpty()) {
            System.out.println("The cart is empty.");
            return;
        }
        System.out.println("Items in the cart:");
        for (CartItem item : items) {
            System.out.println(item);
        }
        System.out.printf("Total Price: $%.2f%n", calculateTotal());
        System.out.printf("Total Items: %d%n", items.size());
    }

    public ArrayList<CartItem> getItems() {
        return new ArrayList<>(items);
    }
}
