package datastructures.autocompletion;

import application.exception.TooManyElementsException;

import java.util.ArrayList;
import java.util.function.Function;

/**
 * This is the data structure for handling the auto completion. This data structure is generic
 * so it can handle any object. As long as it is created with a string
 * @param <E>
 */
public class SimpleAutocompletion<E> implements AutocompletionInterface {

    /**
     * We keep a list of pairs with string and element
     */
    ArrayList<Pair> _list = new ArrayList();

    /**
     * The pair class
     */
    private class Pair{
        private String text;
        private E element;

        public Pair(String t, E e){
            text = t;
            element = e;
        }

        public void setText(String s){
            text = s;
        }

        public void setElement(E e){
            element = e;
        }

        public E getElement(){
            return element;
        }

        public String getText(){
            return text;
        }
    }

    /**
     * This method is used by the constructors it takes an
     * ArrayList of elements and a corresponding ArrayList of strings and creates
     * the pairs and then sort the list with regard to the string in the pairs
     * @param elements
     * @param strings
     */
    private void constructList(ArrayList<E> elements, ArrayList<String> strings){
        for(int i = 0 ; i < elements.size();i++){
            String text = strings.get(i);
            E element = elements.get(i);
            Pair newPair = new Pair(text, element);

            _list.add(newPair);
        }
        _list.sort((pair1,pair2)->pair1.getText().compareTo(pair2.getText()));
    }

    /**
     * Constructor taking two ArrayLists one with elements and one with strings
     * @param elements
     * @param strings
     */
    public SimpleAutocompletion(ArrayList<E> elements, ArrayList<String> strings){
        constructList(elements,strings);
    }

    /**
     * Constructor taking an ArrayList of elements and a lambda expression that takes an
     * element and returns the string the element should be tied up with
     * @param elements
     * @param mapElementToString
     */
    public SimpleAutocompletion(ArrayList<E> elements, Function<E,String> mapElementToString){
        ArrayList<String> strings = new ArrayList<>();

        for(int i = 0 ; i < elements.size();i++){
            E element = elements.get(i);
            String text = mapElementToString.apply(element);
            strings.add(text);
        }
        constructList(elements,strings);
    }

    /**
     * Method for searching without any limit,
     * it just calls the search other search with max int value
     * @param searchString
     * @return
     */
    public ArrayList<E> search(String searchString){
            return search(searchString,Integer.MAX_VALUE);
    }

    /**
     * This method takes a string and returns the first maxNumberOfElements strings
     * in alphabetically order which has a prefix the corresponds to the search string
     * @param searchString
     * @param maxNumberOfElements
     * @return
     */
    @Override
    public ArrayList<E> search(String searchString, int maxNumberOfElements){

        ArrayList<E> result = new ArrayList<>();

        int lengthOfSearchString = searchString.length();

        String searchStringLowerCase = searchString.toLowerCase();

        int numberOfElementsSoFar = 0;

        for(int i=0;i<_list.size();i++){

            if(numberOfElementsSoFar==maxNumberOfElements){
                break;
            }

            Pair pair = _list.get(i);
            E element = pair.getElement();
            String text = pair.getText();

            //Check if word searched for is longer than the current word
            if(searchString.length()>text.length()){
                continue;
            }

            //only compare the first part
            String subText = text.substring(0,lengthOfSearchString);

            //Make the search case insensitive
            String subtextLowerCase = subText.toLowerCase();

            if(searchStringLowerCase.equals(subtextLowerCase)){
                result.add(element);
                numberOfElementsSoFar+=1;

            }
        }
        return result;
    }
}
