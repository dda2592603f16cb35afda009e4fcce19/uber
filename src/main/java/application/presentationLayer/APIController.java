package application.presentationLayer;

import application.DTO.FoodTruckDTO;
import application.viewModel.MapStateVM;
import application.businessLayer.MapBL;
import application.businessLayer.SearchBL;
import application.context.Context;
import application.exception.TooManyElementsException;
import application.util.FoodTruckUtil;
import application.viewModel.FoodTruckListResponseVM;

import application.viewModel.FoodTruckVM;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Class for defining the different api URLs
 */
@RestController
public class APIController {

    @Autowired
    private Context _ctx;

    /**
     * Given a map position we return the food trucks in that view and a status
     * status 0 means everything went well, status 1 means no food trucks, 2 is too many
     * and 100 is internal error
     * @param mapPosition
     * @return
     */
    @RequestMapping("/api/foodTruckView")
    public FoodTruckListResponseVM map(@RequestBody MapStateVM mapPosition) {

        FoodTruckListResponseVM responseVM = new FoodTruckListResponseVM();

        try {

            ArrayList<FoodTruckDTO> listOfFoodTruckDTOs = new MapBL(_ctx).getFoodTrucks(mapPosition);

            ArrayList<FoodTruckVM> listOfFoodTruckVMs = FoodTruckUtil.DTOtoVM(listOfFoodTruckDTOs);

            if(listOfFoodTruckVMs.size()>0) {

                responseVM.setStatus(0);

                responseVM.setListOfFoodTrucks(listOfFoodTruckVMs);
            }
            else{
                responseVM.setStatus(1);
            }

        } catch(TooManyElementsException ex){

            responseVM.setStatus(2);
        }
        catch (Exception ex){

            _ctx.log(Level.SEVERE,ex.toString());

            responseVM.setStatus(100);
        }
        return responseVM;
    }

    /**
     * Given a search string this end point returns a list of the first food trucks where the applicants beginning
     * are equal to the search string. The list will be alphabetically sorted. The response also contains a status
     * that is 0 if everything went well and 100 on internal error
     * @param searchString
     * @return
     */
    @RequestMapping("/api/search")
    public FoodTruckListResponseVM search(@RequestParam String searchString) {

        FoodTruckListResponseVM responseVM = new FoodTruckListResponseVM();

        try {
            ArrayList<FoodTruckDTO> listOfFoodTruckDTOs = new SearchBL(_ctx).search(searchString);

            ArrayList<FoodTruckVM> listOfFoodTruckVMs = FoodTruckUtil.DTOtoVM(listOfFoodTruckDTOs);


            responseVM.setStatus(0);

            responseVM.setListOfFoodTrucks(listOfFoodTruckVMs);

        } catch (Exception ex) {

            responseVM.setStatus(100);

            _ctx.log(Level.SEVERE, ex.toString());
        }

        return responseVM;
    }
}
