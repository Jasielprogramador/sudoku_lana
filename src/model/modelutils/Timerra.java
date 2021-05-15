package model.modelutils;


public class Timerra {

    private double start;

    private static final Timerra instantzia=new Timerra();

    private Timerra(){

    }

    public static Timerra getInstance() {
        return instantzia;
    }

    public void timerraHasi(){
        start = System.currentTimeMillis();
    }

    public double igarotakoDenbora(){
        double finish = System.currentTimeMillis();
        return finish-start;
    }

}
