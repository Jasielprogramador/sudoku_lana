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
        start = System.currentTimeMillis();
        return start;
    }

    public double igarotakoDenbora(){
        double finish = System.currentTimeMillis();
        return finish-start;
    }

    public void setStart(double s){
        start = s;
    }



}
