package application.DTO;

/**
 * DTO for handling the configuration
 */

public class ConfigurationDTO {
    private String JSONAPI;
    private String logFile;

    public String getJSONAPI() {
        return JSONAPI;
    }

    public void setJSONAPI(String JSONAPI) {
        this.JSONAPI = JSONAPI;
    }

    public String getLogFile() {
        return logFile;
    }

    public void setLogFile(String logFile) {
        this.logFile = logFile;
    }
}