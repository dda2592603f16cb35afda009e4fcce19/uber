package application.util;

import application.DTO.FoodTruckDTO;
import application.viewModel.FoodTruckDataVM;
import application.viewModel.FoodTruckVM;

import java.util.ArrayList;

/**
 * This is a util class for working with different food truck objects
 */
public class FoodTruckUtil {
    /**
     * This method takes an ArrayList of food truck DTOs and maps them to food truck VM
     * @param listOfFoodTruckDTOs
     * @return
     */
    public static ArrayList<FoodTruckVM> DTOtoVM(ArrayList<FoodTruckDTO> listOfFoodTruckDTOs){
        ArrayList<FoodTruckVM> listOfFoodTruckVMs = new ArrayList<FoodTruckVM>();

        for (int i = 0; i < listOfFoodTruckDTOs.size(); i++) {
            FoodTruckVM foodTruckVM = new FoodTruckVM();
            FoodTruckDTO foodTruckDTO = listOfFoodTruckDTOs.get(i);

            foodTruckVM.setApplicant(foodTruckDTO.getApplicant());
            foodTruckVM.setFoodItems(foodTruckDTO.getFoodItems());
            foodTruckVM.setLatitude(foodTruckDTO.getLatitude());
            foodTruckVM.setLongitude(foodTruckDTO.getLongitude());

            listOfFoodTruckVMs.add(foodTruckVM);
        }

        return listOfFoodTruckVMs;

    }

    /**
     * This method takes an ArrayList of food truck Data VMs and maps them to food truck DTOs
     * @param listOfFoodTruckDataVMs
     * @return
     */
    public static ArrayList<FoodTruckDTO> DataVMtoDTO(ArrayList<FoodTruckDataVM> listOfFoodTruckDataVMs){
        ArrayList<FoodTruckDTO> listOfFoodTruckDTOs = new ArrayList<FoodTruckDTO>();

        for (int i = 0; i < listOfFoodTruckDataVMs.size(); i++) {
            FoodTruckDTO foodTruckDTO = new FoodTruckDTO();
            FoodTruckDataVM foodTruckDatavm = listOfFoodTruckDataVMs.get(i);

            foodTruckDTO.setApplicant(foodTruckDatavm.getApplicant());
            foodTruckDTO.setFoodItems(foodTruckDatavm.getFooditems());
            foodTruckDTO.setLatitude(foodTruckDatavm.getLatitude());
            foodTruckDTO.setLongitude(foodTruckDatavm.getLongitude());

            listOfFoodTruckDTOs.add(foodTruckDTO);
        }

        return listOfFoodTruckDTOs;

    }
}
