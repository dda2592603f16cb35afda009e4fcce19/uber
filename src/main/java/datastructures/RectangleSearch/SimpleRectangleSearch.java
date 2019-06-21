package datastructures.RectangleSearch;

import application.exception.TooManyElementsException;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * Given some elements with attached coordinates
 * this class can search for which elements is contained in a square
 * @param <E>
 */
public class SimpleRectangleSearch<E> implements RectangleSearchInterface {

    private ArrayList<Item> _list;

    private class Item{
        public E value;

        public Coordinate coordinate;
        public Item(Coordinate coordinate,E value){
            this.value = value;
            this.coordinate = coordinate;
        }
    }

    /**
     * Constructor taking two ArrayLists one with elements and one with strings
     * @param list
     * @param coordinateList
     */
    public SimpleRectangleSearch(ArrayList<E> list, ArrayList<Coordinate> coordinateList){
        _list = new ArrayList();
        for(int i=0;i<list.size();i++){

            E value = list.get(i);
            Coordinate coordinate = coordinateList.get(i);

            Item item = new Item(coordinate, value);

            _list.add(item);
        }
    }

    /**
     * This constructor is taking an ArrayList of elements and a lambda expression that takes an
     * element and returns the coordinate the element should be tied up with
     * @param elements
     * @param mapElementToCoordinate
     */
    public SimpleRectangleSearch(ArrayList<E> elements, Function<E,Coordinate> mapElementToCoordinate){
        _list = new ArrayList();
        for(int i = 0 ; i < elements.size();i++){
            E element = elements.get(i);
            Coordinate coordinate = mapElementToCoordinate.apply(element);

            Item newItem = new Item(coordinate, element);

            _list.add(newItem);
        }
    }

    /**
     * This method has no limit on the number of elements returned
     * @param upperLeft
     * @param lowerRight
     * @return
     */
    public ArrayList<E> search(Coordinate upperLeft, Coordinate lowerRight){
        try {
            return search(upperLeft, lowerRight, Integer.MAX_VALUE);
        }
        catch (TooManyElementsException ex){
            throw new RuntimeException("Somehow we exceeded Integer.MAX_VALUE, this shouldn't happen");
        }
    }

    /**
     * This method takes two coordinates which defines a rectangle and a maximum number of elements and
     * then returns the elements in the square. If there a more elements in the square the the defined max it
     * throws an TooManyElements Exception
     * @param upperLeft
     * @param lowerRight
     * @param maximumNumberOfElements
     * @return
     * @throws TooManyElementsException
     */
    public ArrayList<E> search(Coordinate upperLeft, Coordinate lowerRight, int maximumNumberOfElements) throws TooManyElementsException {

        ArrayList<E> listToReturn = new ArrayList<E>();

        double lowerX = upperLeft.getX();
        double upperX = lowerRight.getX();

        double lowerY = lowerRight.getY();
        double upperY = upperLeft.getY();

        int numberOfElementsSoFar = 0;

        for(int i=0;i<_list.size();i++){

            Item item = _list.get(i);
            Coordinate coordinate = item.coordinate;

            double xCoordinate = coordinate.getX();
            double yCoordinate = coordinate.getY();

            //Test the coordinate lies within the square
            if(lowerX <= xCoordinate && xCoordinate <= upperX && lowerY <= yCoordinate && yCoordinate <= upperY){

                listToReturn.add(item.value);

                numberOfElementsSoFar+=1;


                if(numberOfElementsSoFar>maximumNumberOfElements){

                    throw new TooManyElementsException();
                }
            }
        }

        return listToReturn;
    }

}
