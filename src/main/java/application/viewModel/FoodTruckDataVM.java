package application.viewModel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * VM for parsing the API data into
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruckDataVM {
    private double latitude;
    private double longitude;
    private String applicant;
    private String fooditems;

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

    public String getFooditems() {
        return fooditems;
    }

    public void setFooditems(String fooditems) {
        this.fooditems = fooditems;
    }
}
