package application.DTO;
import application.exception.TooManyElementsException;
import datastructures.RectangleSearch.Coordinate;
import datastructures.RectangleSearch.SimpleRectangleSearch;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class RectangleSearchTest {

    private SimpleRectangleSearch<Integer> rectangleSearch;

    /*
     * This method makes an 11 times 11 grid we can test on.
     * The grid goes from x,y=-5 to x,y=5
     * The elements we put in the rectangleSearch are Integers from 0 to 121
     * It is filled from (-5,-5) then right and up
     */
    @Before
    public void init(){
        ArrayList<Integer> elements = new ArrayList();
        ArrayList<Coordinate> coordinates= new ArrayList();
        int elementCount = 0;
        for(int i=-5;i<=5;i++){
            for(int j=-5;j<=5;j++){
                double x = (double) j;
                double y = (double) i;

                Coordinate newCoordinate = new Coordinate(x,y);
                coordinates.add(newCoordinate);
                elements.add(elementCount);

                elementCount++;

            }
        }
          rectangleSearch =  new SimpleRectangleSearch(elements,coordinates);


    }

    @Test
    public void testWithNoPoints(){
        Coordinate upperLeft = new Coordinate(8,12);
        Coordinate lowerRight = new Coordinate(12,8);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight);
        assert(result.size()==0);

    }

    @Test
    public void testWithEveryPoint(){
        Coordinate upperLeft = new Coordinate(-6,6);
        Coordinate lowerRight = new Coordinate(6,-6);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight);
        assert(result.size()==121);
    }

    /*
     * This test ask for the 2. column from the right
     */
    @Test
    public void testVerticalLine(){
        Coordinate upperLeft = new Coordinate(-4.5,6);
        Coordinate lowerRight = new Coordinate(-3.5,-6);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight);

        HashSet<Integer> correctResult = new HashSet<Integer>();

        //The 2. column is 1,12,23,...
        for(int i=1;i<121;i+=11){
            correctResult.add(i);
        }

        assert(correctResult.size()==result.size());

        assert(correctResult.equals(new HashSet<>(result)));
    }

    /*
     * This test ask for the 3. row from the bottom
     */
    @Test
    public void testHorizontalLine(){
        Coordinate upperLeft = new Coordinate(-6,-2.5);
        Coordinate lowerRight = new Coordinate(6,-3.5);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight);


        HashSet<Integer> correctResult = new HashSet<Integer>();

        //The 3. row is 22,23,24,...
        for(int i=22;i<33;i+=1){
            correctResult.add(i);
        }

        assert(correctResult.size()==result.size());

        assert(correctResult.equals(new HashSet<>(result)));
    }

    /*
     * This test asks for a 2 x 2 square
     */
    @Test
    public void TestSmallRectangle(){
        Coordinate upperLeft = new Coordinate(-4.5,-1.5);
        Coordinate lowerRight = new Coordinate(-2.5,-3.5);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight);

        Integer[] arr = { 34, 35,
                          23, 24};

        HashSet<Integer> correctResult = new HashSet<Integer>(Arrays.asList(arr));

        assert(correctResult.size()==result.size());

        assert(correctResult.equals(new HashSet<>(result)));

    }

    @Test(expected= TooManyElementsException.class)
    public void TestLimitOnNumberOfFoodTrucksWithTooMany() throws TooManyElementsException {
        Coordinate upperLeft = new Coordinate(-6,6);
        Coordinate lowerRight = new Coordinate(6,-6);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight,120);
        assert(result.size()==121);

    }

    @Test
    public void TestLimitOnNumberOfFoodTrucksWithTooFew() throws TooManyElementsException {
        Coordinate upperLeft = new Coordinate(-6,6);
        Coordinate lowerRight = new Coordinate(6,-6);

        ArrayList<Integer> result = rectangleSearch.search(upperLeft,lowerRight,121);
        assert(result.size()==121);

    }

}