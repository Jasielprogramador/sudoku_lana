package model.gelaxka;


import model.gelaxka.Gelaxka;

public class GelaxkaEditable extends Gelaxka {

    private int balioOna;

    public GelaxkaEditable(int pBalio){
        super(pBalio);
    }


    public void setBalioa(int balioa) {
        super.balioa = balioa;
    }

    public void setBalioOna(int balioOna) {
        this.balioOna = balioOna;
    }

    public boolean balioOnaDa(int balioa){
        if(balioOna == balioa){
            return true;
        }
        else{
            return false;
        }
    }
}