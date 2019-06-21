package application.context;

import application.DTO.FoodTruckDTO;
import application.dataLayer.DataLayer;
import datastructures.RectangleSearch.Coordinate;
import datastructures.RectangleSearch.RectangleSearchInterface;
import datastructures.RectangleSearch.SimpleRectangleSearch;
import datastructures.autocompletion.SimpleAutocompletion;
import datastructures.autocompletion.AutocompletionInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Context object used to hold the state of the program
 */
public class Context {
    private Logger logger;
    private Configuration configurationManager;
    private AutocompletionInterface autocompletion;
    private RectangleSearchInterface<FoodTruckDTO> rectangleSearch;

    public Context(){

    }

    /**
     * Given a file json config loads the values into an Configuration object
     * @param file - json file containing the config values
     * @throws Exception
     */
    public Context(File file) throws Exception {

        configurationManager = new Configuration(file);

        logger = Logger.getLogger("Default");

        String logFile = configurationManager.getLogFile();

        FileHandler fileHandler;

        try {
            fileHandler = new FileHandler(logFile, true);

            logger.addHandler(fileHandler);
        } catch (Exception e) {
            //Since we couldn't configure the logger, we write to stderr
            System.err.println("Couldn't add log file");

            throw e;
        }
        getData();
    }

    /**
     * Test constructor
     * This constructor takes a predefined configuration and a list of food truck DTOs
     * and create a context based on that
     * @param configurationManager
     * @param listOfFoodTrucks
     */
    public Context(Configuration configurationManager, ArrayList<FoodTruckDTO> listOfFoodTrucks){

        autocompletion = new SimpleAutocompletion<FoodTruckDTO>(listOfFoodTrucks,(foodTruck)-> foodTruck.getApplicant());

        rectangleSearch = new SimpleRectangleSearch<FoodTruckDTO>(listOfFoodTrucks,(foodTruckDTO -> {
            double latitude = foodTruckDTO.getLatitude();
            double longtitude = foodTruckDTO.getLongitude();
            return new Coordinate(longtitude,latitude);
        }));
        this.configurationManager = configurationManager;
    }

    /**
     * This method calls the data layer and creates the two data structures we need for our program
     */
    public void getData(){

        String APIURL = this.getConfigurationManager().getJSONAPI();

        DataLayer dataLayer = new DataLayer();
        ArrayList<FoodTruckDTO> listOfFoodTrucks = dataLayer.getData(APIURL);

        autocompletion = new SimpleAutocompletion<FoodTruckDTO>(listOfFoodTrucks,(foodTruck)-> foodTruck.getApplicant());

        rectangleSearch = new SimpleRectangleSearch<FoodTruckDTO>(listOfFoodTrucks,(foodTruckDTO -> {
            double latitude = foodTruckDTO.getLatitude();
            double longtitude = foodTruckDTO.getLongitude();
            return new Coordinate(longtitude,latitude);
        }));
    }



    public Configuration getConfigurationManager() {
        return configurationManager;
    }

    public void log(Level level, String logString) {
        logger.log(level, logString);
    }

    public AutocompletionInterface<FoodTruckDTO> getAutocompletion(){
        return autocompletion;
    }

    public RectangleSearchInterface<FoodTruckDTO> getRectangleSearch() {
        return rectangleSearch;
    }
}