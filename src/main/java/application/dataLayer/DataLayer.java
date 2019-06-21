package application.dataLayer;

import application.context.Context;
import application.DTO.FoodTruckDTO;
import application.util.FoodTruckUtil;
import application.viewModel.FoodTruckDataVM;
import com.google.gson.Gson;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class DataLayer {

    public DataLayer(){
    }

    /**
     * Given an URL to the API this method returns an ArrayList of FoodTruck DTO,
     * it does both the downloading and parsing of the data
     * @param APIURL - URL to the API
     * @return ArrayList<FoodTruckDTO> containing the food trucks
     */
    public static ArrayList<FoodTruckDTO> getData(String APIURL){
        RestTemplate restTemplate = new RestTemplate();

        FoodTruckDataVM[] arrayOfFoodTruckDataVms = restTemplate.getForObject(APIURL, FoodTruckDataVM[].class);
        ArrayList<FoodTruckDataVM> listOfDataVms = new ArrayList<FoodTruckDataVM>(Arrays.asList(arrayOfFoodTruckDataVms));
        return FoodTruckUtil.DataVMtoDTO(listOfDataVms);
    }
}