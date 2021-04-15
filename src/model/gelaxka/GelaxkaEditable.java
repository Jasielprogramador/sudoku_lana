package model.gelaxka;


import model.gelaxka.Gelaxka;

public class GelaxkaEditable extends Gelaxka {

    public GelaxkaEditable(int pBalio){
        super(pBalio);
    }


    public void setBalioa(int balioa) {
        super.balioa = balioa;
    }

    public void setBalioOna(int balioOna) {
        super.balioOna = balioOna;
    }

    public boolean balioOnaDa(int balioa){
        if(super.balioOna == balioa){
            return true;
        }
        else{
            return false;
        }
    }
}