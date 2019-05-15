package com.example.foodtrip;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LeaveCommentActivity extends AppCompatActivity {

    Button btnComment;
    EditText etName;
    EditText etComment;

    int dishId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_comment);

        btnComment = findViewById(R.id.btnComment);
        etName = findViewById(R.id.etName);
        etComment = findViewById(R.id.etCommentSection);

        dishId = Integer.parseInt(getIntent().getStringExtra("DishId"));

        //set on click listener
        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String name = etName.getText().toString();
                    String comment = etComment.getText().toString();
                    if(!name.isEmpty() && !comment.isEmpty()) {
                        DishCollection.addComment(dishId, name, comment);
                    }
                    finish();
                }
                catch (Exception e)
                {
                    Log.e("Error", e.getMessage());
                }
            }
        });
    }
}
