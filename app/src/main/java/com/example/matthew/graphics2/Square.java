package com.example.matthew.graphics2;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Matthew on 2016-02-02.
 */
public class Square {
    public Being parasite;
    public boolean isOccupied;
    private int indexX;
    private int indexY;
    public int getIndexX(){
        return indexX;
    }


    public int getIndexY(){
        return indexY;
    }


    private FloatBuffer vertexBuffer;
    private ShortBuffer drawListBuffer;


    static final int COORDS_PER_VERTEX = 3;

    private static short drawOrder[];
    private float[] color = {0.7f, 0.5f, 0f, 1.0f};
    private float[] originalColor = {0.7f, 0.5f, 0f, 1.0f};



    private static int mProgram;

    protected int mMVPMatrixHandle;
    int mPositionHandle;
    int mColorHandle;
   final int vertexCount = 12/COORDS_PER_VERTEX;
    final int vertexStride = COORDS_PER_VERTEX*4;

public void setColor(float[] color){
    this.color = color;

}

    public void setParasite(Being being){
        this.parasite = being;
        isOccupied = true;
    }
    public void clear(){
        color = originalColor;
        parasite = null;
        isOccupied = false;
    }

    public Square(float[] squareCoords, int indexX, int indexY) {
        isOccupied = false;
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        drawOrder = new short[]{ 0, 1, 2, 0, 2, 3 };
        ByteBuffer dlb = ByteBuffer.allocateDirect(

                drawOrder.length * 2);
        dlb.order(ByteOrder.nativeOrder());
        drawListBuffer = dlb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);



        mProgram = MyGLRenderer.myProgram;

        this.indexX = indexX;
        this.indexY = indexY;
    }

    public void draw(float[] mvpMatrix){
        GLES20.glUseProgram(MyGLRenderer.myProgram);

        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");
        GLES20.glEnableVertexAttribArray(mPositionHandle);
        GLES20.glVertexAttribPointer(mPositionHandle,COORDS_PER_VERTEX,GLES20.GL_FLOAT,false,vertexStride,vertexBuffer);

        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);


        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");

        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mvpMatrix, 0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}