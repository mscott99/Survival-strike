package com.example.matthew.graphics2;

/**
 * Created by Matthew on 2016-04-28.
 */
public class Genes {
    public int property;
    Genes(int property){
        this.property = property;
    }

    public static Genes merge(Genes genes1, Genes genes2) {
        return new Genes(averageOf(genes1.property, genes2.property));
    }
    private static int averageOf(int first, int second){
        return((first + second)/2);
    }
}
