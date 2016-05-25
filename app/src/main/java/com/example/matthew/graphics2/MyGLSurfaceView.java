package com.example.matthew.graphics2;

import android.content.Context;

import android.opengl.GLSurfaceView;




/**
 * Created by Matthew on 2016-02-02.
 *SUrface view, view to be soon reemplemented with help of jmonkey
 */
public class MyGLSurfaceView extends GLSurfaceView {

    private MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        //line makes simulator work. why? dont know
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        //next lines initialise the renderer, host of the logic
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);

    }

}