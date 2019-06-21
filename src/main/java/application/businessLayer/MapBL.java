package application.businessLayer;

import application.DTO.*;
import application.context.Context;
import application.exception.TooManyElementsException;
import application.viewModel.MapStateVM;
import datastructures.RectangleSearch.Coordinate;
import datastructures.RectangleSearch.RectangleSearchInterface;

import java.util.ArrayList;

public class MapBL {

    private Context ctx;

    public MapBL(Context ctx) {
        this.ctx = ctx;
    }

    /**
     * This method takes a map position and returns a list of food trucks visable in the region if
     * there are more food trucks then specified in the configuration it will throw an exception
     *
     * @param mapPosition - Map position DTO which tells the current view
     * @return ArrayList<FoodTruckDTO> - a list of food trucks
     * @throws TooManyElementsException
     */
    public ArrayList<FoodTruckDTO> getFoodTrucks(MapStateVM mapPosition) throws TooManyElementsException {

        RectangleSearchInterface rectangleSearch = ctx.getRectangleSearch();

        Coordinate upperLeft = new Coordinate(mapPosition.getWestBorder(), mapPosition.getNorthBorder());
        Coordinate lowerRight = new Coordinate(mapPosition.getEastBorder(), mapPosition.getSouthBorder());

        int maxNumberOfElements = ctx.getConfigurationManager().getMaxDisplayed();

        ArrayList<FoodTruckDTO> listOfFoodTruckDTOs = rectangleSearch.search(upperLeft,lowerRight,maxNumberOfElements);

        return listOfFoodTruckDTOs;
    }


}