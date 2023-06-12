package com.example.mini_projet_01;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;

public abstract class OnSwipeTouchListener implements View.OnTouchListener {
    private Context context;
    GestureDetector gestureDetector;
    private static int THRESHOLD = 100;

    public OnSwipeTouchListener(Context context) {
        this.context = context;
        this.gestureDetector = new GestureDetector(context, new GestureListener());
    }

    class GestureListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(@NonNull MotionEvent e1, @NonNull MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() >= THRESHOLD) {
                swipeLeft();
            }

            if (e2.getX() - e1.getX() >= THRESHOLD) {
                swipeRight();
            }

            return super.onFling(e1, e2, velocityX, velocityY);
        }
    }

    public abstract void swipeLeft();

    public abstract void swipeRight();

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        gestureDetector.onTouchEvent(motionEvent);
        return true;
    }
}
