package com.example.matthew.graphics2;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.GestureDetectorCompat;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.example.matthew.testing.R;

/**
 * Created by Matthew on 2016-02-02.
 */
public class MainActivity extends Activity{
    static MyGLSurfaceView myGL ;
    private FrameLayout frame;
    private GestureDetectorCompat listener;
    private ScaleGestureDetector complexListener;
<<<<<<< HEAD

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
       if (Build.VERSION.SDK_INT < 16) {
           //fullscreen still inexistent in activity
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                   WindowManager.LayoutParams.FLAG_FULLSCREEN);
       }
        //layout set to add button over opengl view
=======
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

       if (Build.VERSION.SDK_INT < 16) {
           getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                   WindowManager.LayoutParams.FLAG_FULLSCREEN);
       }
>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c
        setContentView(R.layout.main_layout);
        frame = (FrameLayout)findViewById(R.id.framed);
        myGL = new MyGLSurfaceView(this);
        frame.addView(myGL, 0);
<<<<<<< HEAD
        //sets the touch event logic on activity level, may be supplanted in jmonkey implementation
        listener = new GestureDetectorCompat(this, new GestureListener(this));
        complexListener = new ScaleGestureDetector(this, new GestureListener(this));
        //optionnal cool features for higher apis
        if (Build.VERSION.SDK_INT >= 19) {
            complexListener.setQuickScaleEnabled(true);
        }
=======

        listener = new GestureDetectorCompat(this, new GestureListener(this));
        complexListener = new ScaleGestureDetector(this, new GestureListener(this));
        complexListener.setQuickScaleEnabled(true);
>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c
        if (Build.VERSION.SDK_INT >= 23) {
            complexListener.setStylusScaleEnabled(true);
        }

    }
<<<<<<< HEAD
    //callback operates 'menu' button
=======
>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c
    public void returnToMenu(View view){
        Intent intent = new Intent(this,Title.class);
        startActivity(intent);
    }
<<<<<<< HEAD
    //touch call
=======
>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c
    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.listener.onTouchEvent(event);
        this.complexListener.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
<<<<<<< HEAD
=======


>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c
}
