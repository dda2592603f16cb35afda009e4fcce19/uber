package application.DTO;

/**
 * DTO for handling the food trucks internally
 */
public class FoodTruckDTO {
    private double latitude;
    private double longitude;
    private String applicant;
    private String foodItems;

    public FoodTruckDTO(String applicant, String foodItems, double latitude, double longitude){
        this.setApplicant(applicant);
        this.setFoodItems(foodItems);
        this.setLatitude(latitude);
        this.setLongitude(longitude);

    }
    public FoodTruckDTO(){

    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getFoodItems() {
        return foodItems;
    }

    public void setFoodItems(String foodItems) {
        this.foodItems = foodItems;
    }
}