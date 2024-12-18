package Cart;
import Book.*;
public class CartItem {
    private Book book;
    private int quantity;

    public CartItem(Book book, int quantity) {
        try {
            if (book == null) {
                throw new IllegalArgumentException("Book cannot be null, You must add a book not just a quantity.");
            }
            if (quantity <= 0) {
                throw new IllegalArgumentException("Book Quantity must be greater than 0.");
            }
            if (quantity > book.getStock()) {
                throw new IllegalArgumentException("Quantity cannot exceed available stock of " + book.getStock() + ".");
            }
            this.book = book;
            this.quantity = quantity;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public Book getBook() {
        return book;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        try {
            if (quantity <= 0) {
                throw new IllegalArgumentException("Book Quantity must be greater than 0.");
            }
            if (quantity > book.getStock()) {
                throw new IllegalArgumentException("Quantity cannot exceed available stock of " + book.getStock() + ".");
            }
            this.quantity = quantity;
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public double getTotalPrice() {
        return book.getPrice() * quantity; // Calculate total price for this item
    }

    @Override
    public String toString() {
        return book.getTitle() + " by " + book.getAuthor() +
                " (Quantity: " + quantity + ", Total Price: $" + getTotalPrice() + ")";
    }

}

