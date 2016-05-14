package com.example.matthew.graphics2;

import java.util.Random;

/**
 * Created by Matthew on 2016-04-28.
 * THis class is a library class for m=possible movements of beings
 */
public class Moving {
    static Random random = new Random();


    static public void moveRight(Being being){
        being.moveTo(1, 0);

    }
static public void moveLeft(Being being){
    being.moveTo(-1,0);
}
    public static void mooveRandom(Being being) {

        int value = random.nextInt(4);

        switch (value) {
            case 0:
                being.moveTo(-1, 0);
                break;
            case 1:
                being.moveTo(1, 0);
                break;
            case 2:
                being.moveTo(0, -1);
                break;
            case 3:
                being.moveTo(0, 1);
                break;

        }
    }

}
