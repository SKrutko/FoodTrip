package com.example.foodtrip;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DishCollection {
    private static ArrayList<Dish> dishes;
    private static ArrayList<Comment> comments;



    public static ArrayList<Dish> getDishes() {
        return dishes;
    }

    public static void setDishes(ArrayList<Dish> dishes) {
        DishCollection.dishes = dishes;
    }

    public static Dish getDishById(int id)
    {
        for (Dish d: dishes
             ) {
            if(d.getDishId() == id)
                return d;
        }
        return null;
    }

    public static ArrayList<Comment> getComments() {
        return comments;
    }

    public static void setDComments(ArrayList<Comment> comments) {
        DishCollection.comments = comments;
    }

    public static Comment getCommentByDishId(int id)
    {
        for (Comment c: comments
        ) {
            if(c.getDishId() == id)
                return c;
        }
        return null;
    }

    public static void addComment(int dishId, String name, String comment)
    {
        FirebaseDatabase database;
        DatabaseReference commentRef;

        database = FirebaseDatabase.getInstance();
        commentRef = database.getReference().child("Comment");

        commentRef.child(String.valueOf(comments.size())).child("dishId").setValue(dishId);
        commentRef.child(String.valueOf(comments.size())).child("CommentatorName").setValue(name);
        commentRef.child(String.valueOf(comments.size())).child("Comment").setValue(comment);
    }
}
