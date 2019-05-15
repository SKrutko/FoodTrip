package com.example.foodtrip;

public class Comment {
    private int dishId;
    private String commentatorName;
    private String comment;

    public Comment(int dishId, String commentatorName, String comment) {
        this.dishId = dishId;
        this.commentatorName = commentatorName;
        this.comment = comment;
    }

    public int getDishId() {
        return dishId;
    }

    public String getCommentatorName() {
        return commentatorName;
    }

    public String getComment() {
        return comment;
    }
}
