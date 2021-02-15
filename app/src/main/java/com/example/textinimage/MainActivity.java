package com.example.textinimage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.*;

import com.harsh.instatag.InstaTag;

import java.util.UUID;

import om.example.textinimage.R;

public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private InstaTag instaTag;
    private EditText editText;
    private Button addTag;

    private int addTagInX;
    private int addTagInY;
    private Boolean tagStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        addTag = findViewById(R.id.add_tag);
        editText = findViewById(R.id.edit_text);
        instaTag = findViewById(R.id.insta_tag);
        linearLayout = findViewById(R.id.ll);

        instaTag.setTaggedPhotoEvent(photoEvent);

        instaTag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tagStatus) {
                    instaTag.showTags();
                    tagStatus = false;
                } else {
                    instaTag.hideTags();
                    tagStatus = true;
                }
            }
        });

    }

    private final InstaTag.PhotoEvent photoEvent = new InstaTag.PhotoEvent() {
        @Override
        public void singleTapConfirmedAndRootIsInTouch(int x, int y) {
            addTagInX = x;
            addTagInY = y;
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
            linearLayout.setVisibility(View.VISIBLE);
            addTag.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editText.getText().toString().length() > 1) {
                        instaTag.addTag(addTagInX, addTagInY, editText.getText().toString());
                        linearLayout.setVisibility(View.GONE);
                    } else {
                        Toast.makeText(MainActivity.this, "Enter a text", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    };

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        linearLayout.setVisibility(View.GONE);
    }
}