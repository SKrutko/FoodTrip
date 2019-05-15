package com.example.foodtrip;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class  Dish {
    private String name;
    private String description;
    private String detailedDescription;
    private float rating;
    private int numberOfRating;
    private String image;
    private String other;
    private int countryCode;
    private int dishId;
    private boolean wasRatingChanged = false;
    private boolean isNumberOfRatingSet = true;

    public boolean isWasRatingChanged() {
        return wasRatingChanged;
    }

    public void setWasRatingChanged(boolean wasRatingChanged) {
        this.wasRatingChanged = wasRatingChanged;
    }



    public String getDetailedDescription() {
        return detailedDescription;
    }

    public void setDetailedDescription(String detailedDescription) {
        this.detailedDescription = detailedDescription;
    }

    public int getNumberOfRating() {
        return numberOfRating;
    }

    public int getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(int countryCode) {
        this.countryCode = countryCode;
    }

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public Dish(String name, String description, float rating, int numberOfRating, String image, String other, int countryCode,int dishId) {
        this.name = name;
        this.description = description;
        this.detailedDescription = "";
        this.rating = rating;
        this.numberOfRating = numberOfRating;
        this.image = image;
        this.other = other;
        this.countryCode = countryCode;
        this.dishId = dishId;
    }

    public Dish(String name, String description, String detailedDescription, float rating, int numberOfRating, String image, String other, int countryCode,int dishId) {
        this.name = name;
        this.description = description;
        this.detailedDescription = detailedDescription;
        this.rating = rating;
        this.numberOfRating = numberOfRating;
        this.image = image;
        this.other = other;
        this.countryCode = countryCode;
        this.dishId = dishId;
    }

    public int getCountyCode() {
        return countryCode;
    }

    public void setCountyCode(int countyCode) {
        this.countryCode = countyCode;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void rate(float newRating)
    {
        try {
            countNewRating(newRating);

            FirebaseDatabase database;
            DatabaseReference myRef;

            database = FirebaseDatabase.getInstance();
            myRef= database.getReference();

            myRef.child("Dish").child(String.valueOf(getDishId())).child("Rating").setValue(rating);
            setNumberOfRatingToFirebase(myRef);
        }
        catch (Exception e)
        {
            Log.d("ERROR", e.getMessage());
        }
    }

    public void setNumberOfRatingToFirebase(DatabaseReference myRef)
    {
        if(!isNumberOfRatingSet)
            myRef.child("Dish").child(String.valueOf(getDishId())).child("NumberOfRating").setValue(numberOfRating);
        isNumberOfRatingSet = true;
    }

    public void countNewRating(float newRating)
    {
        if (numberOfRating == 0) {
            this.rating = newRating;
            numberOfRating++;
        } else {
            rating = rating * (numberOfRating);
            numberOfRating ++;
            rating = rating/ numberOfRating;
            rating += newRating / numberOfRating;
        }
    }

    public void setIsNumberOfRatingSet(boolean b)
    {
        isNumberOfRatingSet = b;
    }


}
