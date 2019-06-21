package application.businessLayer;

import application.DTO.FoodTruckDTO;
import application.context.Context;
import datastructures.autocompletion.AutocompletionInterface;

import java.util.ArrayList;

public class SearchBL {

    private Context ctx;

    public SearchBL(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * Given a search string returns a list of food trucks where the beginning of the applicant matches the string
     * @param searchString - the string to search for
     * @return ArrayList<FoodTruckDTO> - list of food trucks
     */

    public ArrayList<FoodTruckDTO> search(String searchString) {
        AutocompletionInterface autocompletion = ctx.getAutocompletion();

        int maxNumberOfElements = ctx.getConfigurationManager().getMaxSearch();

        ArrayList<FoodTruckDTO> autocompletionList = autocompletion.search(searchString,maxNumberOfElements);

        return autocompletionList;
    }
}