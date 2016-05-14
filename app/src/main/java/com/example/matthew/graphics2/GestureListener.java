package com.example.matthew.graphics2;


import android.content.Context;

import android.support.v4.view.MotionEventCompat;
import android.util.DisplayMetrics;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.WindowManager;


/**
 * Created by Matthew on 2016-05-06.
 * serves as both scaling detector and common gesture detector
 */
public class GestureListener implements GestureDetector.OnGestureListener, ScaleGestureDetector.OnScaleGestureListener {
    float SCALING_CONSTANT = 1;
    float NULL = 38473.86463946f;
    float previousX = NULL;
    float previousY = NULL;
    float SCROLL_CONSTANT = 0.006f;

    final private float screenWidth;
    final private float screenHeight;

    public GestureListener(Context context){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        wm.getDefaultDisplay()
                .getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }


    @Override
    public boolean onDown(MotionEvent event) {

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
System.out.println("single Tap up was made");

        final int pointerIndex = MotionEventCompat.getActionIndex(event);



       // MainActivity.myGL.onTapUp(MotionEventCompat.getX(event, pointerIndex),MotionEventCompat.getY(event, pointerIndex));
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        System.out.println("distance Scroll X: "+ distanceX);
        MyGLRenderer.setPositionX((MyGLRenderer.getPositionX()+distanceX*SCROLL_CONSTANT));
        MyGLRenderer.setPositionY((MyGLRenderer.getPositionY() + distanceY*SCROLL_CONSTANT));
        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }


    @Override
    public boolean onScale(ScaleGestureDetector detector) {
        System.out.println("scaling in progress" + detector.getFocusX());
        MyGLRenderer.setDist(SCALING_CONSTANT*(1/detector.getScaleFactor())*MyGLRenderer.getDist());

        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector detector) {
        previousX = detector.getFocusX();
        previousY = detector.getFocusY();
        return true;
    }

    @Override
    public void onScaleEnd(ScaleGestureDetector detector) {
        previousX = NULL;
        previousY = NULL;
    }
    private float convertToProportion(float screen, float position){
        return position/screen;
    }
}
