package model.sudoku.gelaxka;


import java.util.ArrayList;

public class GelaxkaEditable extends Gelaxka {

    private int balioOna;

    private ArrayList<Integer> hautagaiak;

    @Override
    public String toString() {
        String emma=hautagaiak.toString();
        emma=emma.replace("[","");
        emma=emma.replace("]","");
        emma=emma.replace(","," ");

        return emma;
    }

    public GelaxkaEditable(int pBalio){
        super(pBalio);
        hautagaiak = new ArrayList<>();
    }

    public ArrayList<Integer> getHautagaiak(){
        return hautagaiak;
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

    public boolean balioOnaDa(){
        return (balioOna == super.balioa);
    }
}