package Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import Rating.*;

public class Book {
    private static final HashSet<String> usedBookIds = new HashSet<>();
    private final String bookId;
    private String title;
    private String author;
    private int publicationYear;
    private boolean isAvailable;
    private double price;
    private final ArrayList<Double> ratingsList;
    private final HashMap<String, Boolean> usersRated;
    //***************************************************
    private ArrayList<Rating> BookRating ;
    private float AverageRating ;
    //***************************************************
    private String Category;
    private int stock;
    private int number_of_customer;



    public Book(String title, String author, int publicationYear, boolean isAvailable, double price, String Category, int stock) {
        this.bookId = generateUniqueBookId();
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isAvailable = isAvailable;
        this.price = price;
        this.ratingsList = new ArrayList<>();
        this.usersRated = new HashMap<>();
        this.Category=Category;
        this.stock = stock;
    }
    //**********

    private String generateUniqueBookId() {
        Random random = new Random();
        String id;
        do {
            id = String.format("%06d", random.nextInt(1000000));
        } while (usedBookIds.contains(id));
        usedBookIds.add(id);
        return id;
    }
    public void setNumber_of_customer(int number_of_customer) {
        this.number_of_customer = number_of_customer;
    }

    public int getNumber_of_customer() {
        return number_of_customer;
    }


    // I added this method for the Search by Category feature


    public int getStock() {
        return stock;
    }
    //***************************************************
    public void  SetRating (String UserId , int Ratingnum, String Review){
        Rating TempRating = new Rating(UserId,Ratingnum,Review) ;
        BookRating.add(TempRating);
    }

    public float CalculateTheAverageRating (int new_rate){
        return ((AverageRating * number_of_customer ) + new_rate) / ++number_of_customer;
    }

    public float GetAverageRating(){
        return AverageRating ;

    }
    //***************************************************
    //This method can be used in many ways.
    //I Personally used it in the Order_Item Class
    public void reduceStock(int quantity) {
        try {
            if(quantity==0){
                isAvailable=false;
            }
            if (quantity < 0) {
                throw new IllegalArgumentException("Quantity must be greater than 0.");
            }
            if (quantity > stock) {
                throw new IllegalArgumentException("Not enough stock available.");
            }
            this.stock -= quantity;
        } catch (IllegalArgumentException e) {
            System.out.println("Error reducing stock: " + e.getMessage());
        }
    }


    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getPrice() {
        return price;
    }

    public double getRating() {
        if (ratingsList.isEmpty()) return 0.0;

        double total = 0.0;
        for (Double rating : ratingsList) {
            total += rating;
        }

        return total / ratingsList.size();
    }
    public void setCategory(String category) {
        Category = category;
    }

    public String getCategory() {
        return Category;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public void setBookRating(ArrayList<Rating> bookRating) {
        BookRating = bookRating;
    }

    public void setAverageRating(float averageRating) {
        AverageRating = averageRating;
    }



    public void setStock(int stock) {
        this.stock = stock;
    }

    public void addRating(String userId, double rating) {
        if (usersRated.containsKey(userId)) {
            throw new IllegalArgumentException("User has already rated this book.");
        }
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        ratingsList.add(rating);
        usersRated.put(userId, true); //built in method (Hach_map)
    }

    public void displayDetails() {
        System.out.println("Book ID: " + bookId);
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Publication Year: " + publicationYear);
        System.out.println("Availability: " + (isAvailable ? "Available" : "Not Available"));
        System.out.println("Rating: " + (getRating() > 0 ? String.format("%.2f", getRating()) : "No ratings yet"));
        System.out.println("Price: $" + price);
    }

}