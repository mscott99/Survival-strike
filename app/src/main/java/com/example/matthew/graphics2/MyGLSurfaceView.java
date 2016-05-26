package com.example.matthew.graphics2;

import android.content.Context;

import android.opengl.GLSurfaceView;




/**
 * Created by Matthew on 2016-02-02.
<<<<<<< HEAD
 *SUrface view, view to be soon reemplemented with help of jmonkey
 */
public class MyGLSurfaceView extends GLSurfaceView {


MyGLRenderer mRenderer;
    public MyGLSurfaceView(Context context) {
        super(context);
mRenderer = new MyGLRenderer();
        setEGLContextClientVersion(2);
        //line makes simulator work. why? dont know
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        //next lines initialise the renderer, host of the logic
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);




    }

}