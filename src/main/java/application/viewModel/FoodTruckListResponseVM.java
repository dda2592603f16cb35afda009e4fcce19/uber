package application.viewModel;

import java.util.ArrayList;

/**
 * VM for returning a request to the user
 */
public class FoodTruckListResponseVM {
    private ArrayList<FoodTruckVM> listOfFoodTrucks;
    private int status;

    public ArrayList<FoodTruckVM> getListOfFoodTrucks() {
        return listOfFoodTrucks;
    }

    public void setListOfFoodTrucks(ArrayList<FoodTruckVM> listOfFoodTrucks) {
        this.listOfFoodTrucks = listOfFoodTrucks;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}