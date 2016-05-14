package com.example.matthew.graphics2;

import android.content.Context;

import android.opengl.GLSurfaceView;




/**
 * Created by Matthew on 2016-02-02.
 *
 */
public class MyGLSurfaceView extends GLSurfaceView {






    private MyGLRenderer mRenderer;

    public MyGLSurfaceView(Context context) {
        super(context);

        setEGLContextClientVersion(2);
        super.setEGLConfigChooser(8 , 8, 8, 8, 16, 0);
        mRenderer = new MyGLRenderer();
        setRenderer(mRenderer);
        //setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);



    }

}