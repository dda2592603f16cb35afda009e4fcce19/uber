package application.viewModel;

/**
 * VM for handling the map state from the user
 */
public class MapStateVM {
    private double northBorder;
    private double eastBorder;
    private double southBorder;
    private double westBorder;

    public double getNorthBorder() {
        return northBorder;
    }

    public void setNorthBorder(double northBorder) {
        this.northBorder = northBorder;
    }

    public double getEastBorder() {
        return eastBorder;
    }

    public void setEastBorder(double eastBorder) {
        this.eastBorder = eastBorder;
    }

    public double getSouthBorder() {
        return southBorder;
    }

    public void setSouthBorder(double southBorder) {
        this.southBorder = southBorder;
    }

    public double getWestBorder() {
        return westBorder;
    }

    public void setWestBorder(double westBorder) {
        this.westBorder = westBorder;
    }


}
