package application.DTO;

import application.exception.TooManyElementsException;
import datastructures.autocompletion.SimpleAutocompletion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class AutocompleteTest {

    private SimpleAutocompletion<Integer> autocompletion;

    @Before
    public void init(){
        Integer[] ints = {0,1,2,3,4,5,6,7,8,9};
        String[] strings = {
                "Alfa",
                "Beta",
                "Gamma",
                "Her",
                "Him",
                "Peter",
                "Pape",
                "Q",
                "QQ",
                "QQQ"
        };

        ArrayList<Integer> intList = new ArrayList(Arrays.asList(ints));
        ArrayList<String> StringList = new ArrayList(Arrays.asList(strings));
        autocompletion = new SimpleAutocompletion<>(intList,StringList);
    }

    @Test
    public void askForElementOne(){
        ArrayList<Integer> result = autocompletion.search("B");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(1);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }

    @Test
    public void askForElementTwo(){
        ArrayList<Integer> result = autocompletion.search("G");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(2);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }

    @Test
    public void askForElementThreeAndFour(){
        ArrayList<Integer> result = autocompletion.search("H");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(3);
        correct.add(4);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }

    @Test
    public void askForElementZeroIgnoreCase(){
        ArrayList<Integer> result = autocompletion.search("a");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(0);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }

    @Test
    public void checkMoreThanOneLetter(){
        ArrayList<Integer> result = autocompletion.search("Gamma");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(2);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }

    @Test
    public void searchWordLongerThanSomeWords(){
        ArrayList<Integer> result = autocompletion.search("Gamma");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(2);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }
    @Test
    public void testIfStringsGetSorted(){
        ArrayList<Integer> result = autocompletion.search("P");
        ArrayList<Integer> correct = new ArrayList();

        correct.add(6);
        correct.add(5);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }

    @Test
    public void TestLimitOnNumberOfFoodTrucksWithTooMany() {
        ArrayList<Integer> result = autocompletion.search("Q",2);
        ArrayList<Integer> correct = new ArrayList();

        correct.add(7);
        correct.add(8);

        assert(result.size()==2);
        assert(result.equals(correct));
    }

    @Test
    public void TestLimitOnNumberOfFoodTrucksWithTooFew() {
        ArrayList<Integer> result = autocompletion.search("Q",3);
        ArrayList<Integer> correct = new ArrayList();

        correct.add(7);
        correct.add(8);
        correct.add(9);

        assert(result.size()==correct.size());
        assert(result.equals(correct));
    }
}
