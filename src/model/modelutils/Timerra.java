package model.modelutils;

import java.util.Timer;

public class Timerra {

    private double start;

    private static Timerra instantzia=new Timerra();

    private Timerra(){

    }

    public static Timerra getInstance() {
        return instantzia;
    }

    public double timerraHasi(){
        double start = System.nanoTime();
        return start;
    }

    public double igarotakoDenbora(){
        double finish = System.nanoTime();
        return finish-start;
    }

    public void setStart(double s){
        start = s;
    }



}
