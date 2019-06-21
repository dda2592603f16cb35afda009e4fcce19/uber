package datastructures.RectangleSearch;

/**
 * Coordinate class
 */
public class Coordinate {
    private double _x;
    private double _y;
    public Coordinate(double x, double y){
        _x = x;
        _y = y;
    }

    public static int sortByX(Coordinate first, Coordinate second){
        int xSort = sortHelper(first.getX(), second.getX());
        if(xSort != 0) return xSort;
        else return sortHelper(first.getY(), second.getY());
    }

    public static int sortByY(Coordinate first, Coordinate second){
        int ySort = sortHelper(first.getY(), second.getY());
        if(ySort != 0) return ySort;
        else return sortHelper(first.getX(), second.getY());
    }

    private static int sortHelper(double first, double second){
        if(first<second){
            return -1;
        }
        else if(first>second){
            return 1;
        }
        else{
            return 0;
        }
    }



    public double getX(){
        return _x;
    }

    public double getY(){
        return _y;
    }
}
