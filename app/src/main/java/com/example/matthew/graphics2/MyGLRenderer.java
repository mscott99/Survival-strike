package com.example.matthew.graphics2;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.os.SystemClock;

import java.util.ArrayList;
import java.util.Random;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Matthew on 2016-02-02.
 */
public class MyGLRenderer implements GLSurfaceView.Renderer {


    static Square[][] squares;

    static int myProgram;
    private static final float[] mMVPMatrix = new float[16];
    private static final float[] mProjectionMatrix = new float[16];
    private static final float[] mViewMatrix = new float[16];
    private static final float[] mTouchMatrix = new float[16];

    public float[] getmTouchMatrix(){
        return mTouchMatrix;
    }

    static public ArrayList<Being> being;
    public static int numBeings;



    private static float[] scratch = new float[16];

    private static float ratio;

    public static volatile float camDist = -3f;
    private static volatile float posX = 0;
    private static volatile float posY = 0;

    public static float getDist(){
        return camDist;
    }
    public static void setDist(float angle){
        camDist = angle;
    }

    public static float getPositionX(){
        return posX;
    }
    public static void setPositionX(float angle){
        posX = angle;
    }

    public static float getPositionY(){
        return posY;
    }
    public static void setPositionY(float angle){
        posY = angle;
    }

    private static Random random;


    static float squareWidth ;
    static float spacing ;
    static int numSquares;
    static float squareWIdth;

    long lastTime = 0;
    int trials = 0;



    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
    GLES20.glClearColor(0f, 1f, 0f, 1.0f);
        myProgram = Program.createProgram();
        numBeings = 30;
        squareWidth = 0.3f;
        spacing = 0.05f;
        numSquares = 30;
        being = new ArrayList(numBeings);

        int row;
        int column;
        squares = new Square[numSquares][numSquares];


        //same direction as reading
    for(int x =0; x < (numSquares*numSquares); x++){
        column = x % numSquares;
        row = x/(numSquares);
        float totalWIdth = numSquares * squareWIdth + (numSquares-1)*spacing;

        float[] input = {
                -(totalWIdth/2) + (squareWidth + spacing)*column,
                totalWIdth/2 - (squareWidth/2 + spacing)*row,

                0.0f,
                -totalWIdth/2 + (squareWidth + spacing)*column,
                totalWIdth/2 - (squareWidth/2 + spacing)*row - squareWidth/2,
                0.0f,
                -totalWIdth/2 + (squareWidth + spacing)*column + squareWidth,
                totalWIdth/2 - (squareWidth/2 + spacing)*row - squareWidth/2,
                0.0f,
                -totalWIdth/2 + (squareWidth + spacing)*column + squareWidth,
                totalWIdth/2 - (squareWidth/2 + spacing)*row,
                0.0f
        };
        squares[row][column] = new Square(input, row, column);


    }


        random = new Random();

        being.ensureCapacity(numBeings);
System.out.println("hello there");

        being.add(new Being());
        being.add(new Being());
        being.get(0).occupy(20, 20);
        being.get(1).occupy(20,21);

        //generateMultipleBeings();


        //debugging mode

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        GLES20.glViewport(0,0,width, height);

        ratio = (float) width / height;

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        GLES20.glClearColor(0.1f, 0f, 0.2f, 1.0f);
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        long time = SystemClock.uptimeMillis();
        //error unclear, attempt to make a single moove with one thing without program crashing,
        //no need for visible results. Try first eliminating,, creating, then mooving
        //being.get(0).mooveTo(1,0);
        //being.get(0).mooveTo(0,1);


        if(time - lastTime > 1000 ){
            System.out.println(being.size());
            if(trials > 3) {
                for (int e = 0; e < being.size(); e++) {
                    being.get(e).cycleFinished();
                }
                for (int e = 0; e < being.size(); e++) {
                    being.get(e).live();
                }
            }trials ++;
                lastTime = time;

        }


       float[] translate = {1f, 0f,0f,0f, 0f, 1f,3f, 0f, 0f, 0f, 1f, 0f, posX ,posY, 0.0f,1f};
        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1f, 1, 2,50);
        Matrix.setLookAtM(mViewMatrix, 0, 1f, 0, camDist, 0f, 0f, 0f, 0f, 1.0f, 0.0f);





       // Matrix.invertM(rectifiedTranslate, 0, translate, 0);




        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);
        //Matrix.multiplyMM(scratch,0,mMVPMatrix, 0, translationMatrix, 0);
        Matrix.multiplyMM(scratch, 0, mMVPMatrix, 0, translate, 0);

        Matrix.invertM(mTouchMatrix, 0, scratch, 0);



        for(int x =0; x < (numSquares); x++){
            for(int y = 0; y< numSquares; y++){
                squares[x][y].draw(scratch);
            }
        }
    }

    private void generateMultipleBeings() {
        for (int x = 0; x < numBeings; x++) {
            being.add(new Being());
            int columns = x % numSquares;
            being.get(x).occupy(x, columns);
        }
    }

}
