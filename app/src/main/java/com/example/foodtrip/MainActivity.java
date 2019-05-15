package com.example.foodtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnToMainSearch;
    TextView txvWelcomeMessage;

    FirebaseDatabase database;
    DatabaseReference rootRef, dishRef, commentRef;

    ArrayList<Dish> dishes;
    ArrayList<Comment> comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        rootRef= database.getReference();
        dishRef = rootRef.child("Dish");
        commentRef = rootRef.child("Comment");

        txvWelcomeMessage = findViewById(R.id.txvWelcome);

        btnToMainSearch = findViewById(R.id.btnFindFood);
        btnToMainSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //txvWelcomeMessage.setText("You clicked;)");
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);


            }
        });

        ArrayList<Dish> dishes = new ArrayList<>();
        comments = new ArrayList<>();




        fillInDishes();
        DishCollection.setDishes(dishes);
        DishCollection.setDComments(comments);
        Log.d("DishesSize", "Value is: " + dishes.size());

    }

    protected void fillInDishes()
    {
        dishRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {

                    dishes = new ArrayList<>();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        float rating;
                        try {
                            rating = ds.child("Rating").getValue(float.class);
                        }
                        catch (Exception e)
                        {
                            rating = 0;
                        }

                        int id;
                        try{
                             id = ds.child("Id").getValue(int.class);
                        }
                        catch (Exception e)
                        {
                            id = -1;
                        }

                        int countryId;
                        try {
                            countryId =ds.child("CountryId").getValue(int.class);
                        }
                        catch (Exception e)
                        {
                            countryId = -1;
                        }

                        int numberOfRating;
                        try {
                            numberOfRating =ds.child("NumberOfRating").getValue(int.class);
                        }
                        catch (Exception e)
                        {
                            numberOfRating = 0;
                        }


                        String name = ds.child("Name").getValue(String.class);
                        String description = ds.child("Description").getValue(String.class);
                        String detailedDescription = ds.child("DetailedDescription").getValue(String.class);
                        String other = ds.child("Other").getValue(String.class);
                        String image = ds.child("Image").getValue(String.class);
                        Dish d = new Dish(name, description, detailedDescription,  rating, numberOfRating, image, other, countryId, id );
                        dishes.add(d);
                        Log.d("Dish", "Value is: " + rating + ", " + name + ", " + description );

                    }
                    DishCollection.setDishes(dishes);
                }
                catch (Exception e)
                {
                    Log.d("ERRRRRRRRRRRORRRRRRRRRR",  "ERRROR" + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("Dish", "Failed to read value.", error.toException());
            }
        });

        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    comments = new ArrayList<>();
                    for(DataSnapshot ds : dataSnapshot.getChildren()) {
                        String commentatorName = ds.child("CommentatorName").getValue(String.class);
                        String comment = ds.child("Comment").getValue(String.class);
                        int dishId = ds.child("dishId").getValue(int.class);
                        Comment c = new Comment(dishId, commentatorName, comment);
                        comments.add(c);
                        Log.d("Comment", "Value is: " + dishId + ", " + commentatorName + ", " + comment );
                    }
                    DishCollection.setDComments(comments);
                }
                catch (Exception e)
                {
                    Log.d("ERROR4",  "ERROR" + e.getMessage());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("ERROR5", "Failed to read value.", error.toException());
            }
        });
    }

}
