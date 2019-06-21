package datastructures.autocompletion;

import application.exception.TooManyElementsException;

import java.util.ArrayList;

public interface AutocompletionInterface<E> {
    public ArrayList<E> search(String searchText);
    public ArrayList<E> search(String searchText, int maximumNumberOfElements);
}
