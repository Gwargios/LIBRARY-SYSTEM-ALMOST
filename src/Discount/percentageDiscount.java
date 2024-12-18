package Discount;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class percentageDiscount implements Serializable {
    private double percentage;
    private LocalDate startDate;
    private LocalDate endDate;
    private String Code;
    private String description;
    public void setCode(String code) {
        Code = code;
    }
    public String getCode() {
        return Code;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public ArrayList<percentageDiscount> percentageDiscountList = new ArrayList<>();

    public percentageDiscount() {
    }
    public percentageDiscount(String description, String code, double percentage, LocalDate startDate, LocalDate endDate) {
        this.percentage = percentage;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public ArrayList<percentageDiscount> getPercentageDiscountList() {
        return percentageDiscountList;
    }

    public void setPercentageDiscountList(ArrayList<percentageDiscount> percentageDiscountList) {
        this.percentageDiscountList = percentageDiscountList;
    }

    public boolean isCodeUnique(String code) {
        for (percentageDiscount discount : percentageDiscountList) {
            if (discount.getCode().equalsIgnoreCase(code)) {
                return false;
            }
        }
        return true;
    }

    public void createPercentageDiscount(String description, String code, double percentage, LocalDate startDate, LocalDate endDate) {



        percentageDiscount newDiscount = new percentageDiscount(description, code, percentage, startDate, endDate);
        percentageDiscountList.add(newDiscount);
        System.out.println("New percentage discount added: " + description + " with " + percentage + "% off.");
    }


    public void updatePercentageDiscount(String code, int fieldChoice, String newValue) {
        percentageDiscount discountToUpdate = null;

        // Find the discount with the specified code
        for (percentageDiscount discount : percentageDiscountList) {
            if (discount.getCode().equalsIgnoreCase(code)) {
                discountToUpdate = discount;
                break;
            }
        }

        if (discountToUpdate == null) {
            throw new IllegalArgumentException("Discount with the specified code not found.");
        }

        switch (fieldChoice) {
            case 1:
                discountToUpdate.setDescription(newValue);
                System.out.println("Description updated successfully.");
                break;

            case 2:
                if (!isCodeUnique(newValue)) {
                    throw new IllegalArgumentException("Code already exists. Please enter a different code.");
                }
                discountToUpdate.setCode(newValue);
                System.out.println("Code updated successfully.");
                break;

            case 3:
                double newPercentage = Double.parseDouble(newValue);
                if (newPercentage < 0 || newPercentage > 100) {
                    throw new IllegalArgumentException("Percentage must be between 0 and 100.");
                }
                discountToUpdate.setPercentage(newPercentage);
                System.out.println("Percentage updated successfully.");
                break;

            case 4:
                LocalDate newStartDate = LocalDate.parse(newValue);
                discountToUpdate.setStartDate(newStartDate);
                System.out.println("Start date updated successfully.");
                break;

            case 5:
                LocalDate newEndDate = LocalDate.parse(newValue);
                if (discountToUpdate.getStartDate() != null && newEndDate.isBefore(discountToUpdate.getStartDate())) {
                    throw new IllegalArgumentException("End date cannot be before start date.");
                }
                discountToUpdate.setEndDate(newEndDate);
                System.out.println("End date updated successfully.");
                break;

            default:
                throw new IllegalArgumentException("Invalid field choice. Please select a valid option.");
        }
    }


    public void displayAllPercentageDiscounts () {
        if (percentageDiscountList.isEmpty()) {
            System.out.println("No discounts available.");
            return;
        }
        for (percentageDiscount discount : percentageDiscountList) {
            System.out.println("Description: " + discount.getDescription());
            System.out.println("Code: " + discount.getCode());
            System.out.println("Percentage: " + discount.getPercentage() + "%");
            System.out.println("Start Date: " + discount.getStartDate());
            System.out.println("End Date: " + discount.getEndDate());
            System.out.println("----------------------------------------");
        }
    }

    public void deleteDiscounts (String code){
        percentageDiscount discountToDelete = null;
        for (percentageDiscount discount : percentageDiscountList) {
            if (discount.getCode().equals(code)) {
                discountToDelete = discount;
                break;
            }
        }
        if (discountToDelete != null) {
            percentageDiscountList.remove(discountToDelete);
            System.out.println("Discount deleted successfully.");
        }
    }


    public double applyDiscount ( double price, int quantity){
        return price - (price * (percentage / 100));
    }
}
