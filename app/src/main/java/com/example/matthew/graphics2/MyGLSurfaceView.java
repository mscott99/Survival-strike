package com.example.matthew.graphics2;

import android.content.Context;

import android.opengl.GLSurfaceView;




/**
 * Created by Matthew on 2016-02-02.
<<<<<<< HEAD
 *SUrface view, view to be soon reemplemented with help of jmonkey
 */
public class MyGLSurfaceView extends GLSurfaceView {

=======
 *
 */
public class MyGLSurfaceView extends GLSurfaceView {






>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c
    private MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
<<<<<<< HEAD
        //line makes simulator work. why? dont know
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        //next lines initialise the renderer, host of the logic
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
=======
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);


>>>>>>> 367fd83b610d624a639b1d18075e843b4e979b2c

    }

}