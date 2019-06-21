package application.context;

import application.DTO.ConfigurationDTO;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileReader;

public class Configuration {
    private String JSONAPI;
    private String logFile;
    private int maxDisplayed = 400;
    private int maxSearch = 10;

    /**
     * Class used as a store for the config values, given a json file it loads the values into the class
     * @param file - the file to read config values from
     */
    public Configuration(File file) {
        Gson gson = new Gson();
        try {
            FileReader reader = new FileReader(file);
            ConfigurationDTO configurationDTO = gson.fromJson(reader, ConfigurationDTO.class);

            setJSONAPI(configurationDTO.getJSONAPI());
            setLogFile(configurationDTO.getLogFile());

        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }

    /**
     * Constructor used for testing
     * @param logFile
     * @param JSONAPI
     * @param maxDisplayed
     * @param maxSearch
     */
    public Configuration(String logFile, String JSONAPI, int maxDisplayed, int maxSearch){
        this.setLogFile(logFile);
        this.setJSONAPI(JSONAPI);
        this.setMaxDisplayed(maxDisplayed);
        this.setMaxSearch(maxSearch);
    }

    public String getLogFile(){
        return logFile;
    }

    public String getJSONAPI() {
        return JSONAPI;
    }

    public int getMaxDisplayed(){
        return maxDisplayed;
    }

    public int getMaxSearch() {
        return maxSearch;
    }

    private void setJSONAPI(String JSONAPI) {
        this.JSONAPI = JSONAPI;
    }

    private void setLogFile(String logFile) {
        this.logFile = logFile;
    }

    private void setMaxDisplayed(int maxDisplayed) {
        this.maxDisplayed = maxDisplayed;
    }

    private void setMaxSearch(int maxSearch) {
        this.maxSearch = maxSearch;
    }
}