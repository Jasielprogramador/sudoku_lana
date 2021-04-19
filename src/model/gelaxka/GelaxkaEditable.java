package model.gelaxka;


import model.gelaxka.Gelaxka;

import java.util.ArrayList;

public class GelaxkaEditable extends Gelaxka {

    private int balioOna;

    private ArrayList<Integer> hautagaiak;

    public GelaxkaEditable(int pBalio){
        super(pBalio);
        hautagaiak = new ArrayList<>();
    }

    public void setHautagaiak(ArrayList<Integer> lista){
        hautagaiak = lista;
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