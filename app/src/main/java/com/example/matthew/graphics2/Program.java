package com.example.matthew.graphics2;

import android.opengl.GLES20;

/**
 * Created by Matthew on 2016-03-07.
 */

public class Program {
    private static int myProgram;

    private static final String vertexShaderCode =
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main(){" +
                    "gl_Position = uMVPMatrix*vPosition;" +
                    "}";

    private static final String fragmentShaderCode =
            "precision mediump float; " +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "gl_FragColor = vColor;" +
                    "}";
    public static int createProgram(){
        int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderCode);
        int fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderCode);
        myProgram = GLES20.glCreateProgram();
        GLES20.glAttachShader(myProgram, vertexShader);
        GLES20.glAttachShader(myProgram, fragmentShader);
        GLES20.glLinkProgram(myProgram);
        return myProgram;
    }

    public static int loadShader(int type, String code){
        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader, code);
        GLES20.glCompileShader(shader);

        return shader;
    }
}
