package com.example.matthew.graphics2;

import android.nfc.Tag;
import android.os.Build;
import android.util.Log;

import java.util.Random;

import static com.example.matthew.graphics2.MyGLRenderer.squares;

/**
 * Created by Matthew on 2016-02-24.
 *
 * This is a class representing each being in the game, now only navigating on single squares
 */
public class Being {
    final public float[] color = {0.1f, 0.7f, 0.2f, 0.0f};
    private int[][] visibleRange = {{1,0},{-1,0},{0,1},{0,-1}};
    private int[][] couplingRange = {{1,0},{-1,0},{0,1},{0,-1}};
    private boolean isReadyToProcreate;
    private int labelled;

    private final Genes genes;
private boolean isAChild  = true;
    private int childPeriod = 3;
    private boolean hasLived;
    //is used to prevent double turns
    Random random;
    public int indexX;
    public int indexY;
    Being(int labbeled){
this.labelled = labbeled;
        isReadyToProcreate = true;
        random = new Random();
        genes = new Genes(random.nextInt(10));
    }
    Being(int labbeled,Genes genes){
        //stops the guys newly created to move immediately
       this.labelled = labbeled;
        hasLived = true;
        isReadyToProcreate = false;
        random = new Random();
        this.genes = genes;
    }

    public void occupy(int indexX,int indexY){
        this.indexX = indexX;
        this.indexY = indexY;
        squares[indexX][indexY].setColor(color);
        squares[indexX][indexY].setParasite(this);
    }

    public boolean moveTo(int side, int up){
        //verifies thatwalker stops before going offmap or into obstacle
        if(squareIsAvailable(indexX+side, indexY+up)) {
            squares[indexX][indexY].clear();
            indexX += side;
            indexY += up;
            this.occupy(indexX, indexY);
            hasLived = true;
            return true;
        }else{
            return false;
        }
    }
//function when a creature acts, checks for ennemies, death or others. In fact, everything
    public void live(){
       if(!hasLived) {
           System.out.println("guy"+labelled+"lives");
           if (otherIsVisible()) {
               if (isReadyToProcreate && otherIsCloseEnoughToCouple()) {
                   sendCouplingRequests();
               }
           }
       }
           if(!hasLived){
               Moving.mooveRandom(this);
           }


    }
  //useless for now




    public void cycleFinished(){
        hasLived = false;
        if(isAChild && childPeriod == 0 ){
            isAChild = false;
        }

        if(!isAChild){
        isReadyToProcreate = true;
    }else{
       childPeriod --;
        }
    }

    private boolean otherIsVisible(){

          Log.d("Being/otherIsVisible", "number" + labelled + "has seen a guy");

          return isInGivenRange(visibleRange);

    }


    private boolean otherIsCloseEnoughToCouple() {

        Log.d("Being", "number" + labelled + "sees being close to couple");
            return isInGivenRange(couplingRange);
    }

    private boolean isInGivenRange(int[][] range){
        for(int[] x : range){
            if(squareIsOnMap(indexX + x[0], indexY + x[1])) {
                if (squares[indexX + x[0]][indexY + x[1]].isOccupied) {
                    return true;
                }
            }
        }
        return false;
    }
//reproduction bugs in this function
    public void matingAccepted(Being mate, Genes genes){
        Log.d("Being","mating accepted");
        if(!hasLived){
            Genes meiosis = Genes.merge(this.genes, genes);

            int differenceX = mate.indexX-indexX;
            int differenceY = mate.indexY - indexY;

            if(differenceX == 0){

                if(moveTo(0, -differenceY)){
                    giveBirth(0, differenceY, meiosis);

                    mate.hasLived = true;

                }else if(mate.moveTo(0, differenceY)){
                    giveBirth(0, differenceY, genes);
                    hasLived = true;
                }


            } else if(differenceY == 0){

                if(moveTo(-differenceX, 0)){
                    giveBirth(differenceX, 0, genes);
                mate.hasLived = true;
                }else if (mate.moveTo(differenceX, 0)){
                    giveBirth(differenceX, 0, genes);
                hasLived = true;
                }

            }

        }
    }


    public void matingRequested(Being being){
   Log.d("Being","Being num "+ labelled+" received request");
        if(isReadyToProcreate && !hasLived){
              Log.d("Being","Being num "+ labelled+" accepted request");
            being.matingAccepted(this, genes);
        }
    }

    public void giveBirth(int side,int up, Genes genes){
        Log.d("being","being"+ labelled+"isgiving birth");


        Being being = new Being(MyGLRenderer.giveNum(), genes);
        being.occupy(indexX+ side, indexY + up);
        MyGLRenderer.being.ensureCapacity(MyGLRenderer.being.size());
        MyGLRenderer.being.add(being);
    }
    //function make new creature through mating
    private void sendCouplingRequests(){ // faults: set disposition to procreation to negative during and in the turn of coupling;
        // also make a reset function more global then what is currently used in MiGLRenderer
        if(isReadyToProcreate) {
            for (int[] x : couplingRange) {
                if (squareIsOnMap(indexX + x[0], indexY + x[1])) {
                    if (squares[indexX + x[0]][indexY + x[1]].isOccupied) {
                        Log.d("Being","Being num"+labelled+" sent request");
                        squares[indexX + x[0]][indexY + x[1]].parasite.matingRequested(this);
                    }
                }
            }

        }else {
            isReadyToProcreate = true;
        }
    }
// view and reproduction section---------------------------------------------------------------------------
    private static boolean squareIsOnMap(int x, int y) {
        return (x < squares.length && x >= 0 && y < squares[0].length && y >= 0);
    }

    private static boolean squareIsAvailable(int x, int y) {
        //cannot put in single if statement: second verification would produce error
        if (  squareIsOnMap(x, y)){
           return (!squares[x][y].isOccupied );
        }
            return false;
    }

    public void debugLive(boolean isGoingRight){
        if(!hasLived) {
            if (otherIsVisible()) {
                if (isReadyToProcreate && otherIsCloseEnoughToCouple()) {
                    sendCouplingRequests();
                }
            }
        }
        if(!hasLived){
            if(isGoingRight) {
                Moving.moveRight(this);

            }else {
                Moving.moveLeft(this);
            }
        }
    }
}

