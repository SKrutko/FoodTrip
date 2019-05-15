package com.example.foodtrip;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        Dish d = new Dish("", "", "", 5f, 0, "", "", -1, -1);
        d.countNewRating(3.5f);
        assertEquals(3.5f, d.getRating(), 0);
    }
  /*  @Test
    public void countNewRating_given3and5_ratingSetTo4() {
        Dish d = new Dish("", "", "", 5f, 1, "", "", -1, -1);
        d.countNewRating(3f);
        assertEquals(2, d.getNumberOfRating());
        assertEquals(4f, d.getRating(), 0);
    }
    @Test
    public void countNewRating_given34and5_ratingSetTo4() {
        Dish d = new Dish("", "", "", 4.5f, 2, "", "", -1, -1);
        d.countNewRating(3f);
        assertEquals(3, d.getNumberOfRating());
        assertEquals(4f, d.getRating(), 0);
    }*/


}