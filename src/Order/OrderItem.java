package Order;
import Book.*;
public class OrderItem {
    private Book book;
    private int quantity;

    public OrderItem(Book book, int quantity) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null.");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        if (book.getStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock for book: " + book.getTitle());
        }
        this.book = book;
        this.quantity = quantity; // Assign validated values
    }

    public double getTotalPrice() {
        return book.getPrice() * quantity; // Calculate total price for this order item
    }

    public void reduceStock() {
        book.reduceStock(quantity);
    }

    public Book getBook() {
        return book;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0.");
        }
        if (book.getStock() < quantity) {
            throw new IllegalArgumentException("Not enough stock for book: " + book.getTitle());
        }
        this.quantity = quantity;
    }
}
