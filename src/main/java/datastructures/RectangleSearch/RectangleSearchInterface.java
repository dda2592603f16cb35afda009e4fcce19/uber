package datastructures.RectangleSearch;

import application.exception.TooManyElementsException;

import java.util.ArrayList;

public interface RectangleSearchInterface<E> {
    public ArrayList<E> search(Coordinate upperLeft, Coordinate lowerRight);
    public ArrayList<E> search(Coordinate upperLeft, Coordinate lowerRight, int maximumNumberOfElements) throws TooManyElementsException;
}