package model.gelaxka;


import model.gelaxka.Gelaxka;

public class GelaxkaEditable extends Gelaxka {

    public GelaxkaEditable(int pBalio){
        super(pBalio);
    }


    public int getBalioa() {
        return super.getBalioa();
    }

    public void setBalioa(int balioa) {
        super.setBalioa(balioa);
    }

    public int getBalioOna(){
        return super.getBalioOna();
    }

    public void setBalioOna(int balioOna) {
        super.setBalioOna(balioOna);
    }

    public boolean balioOnaDa(int balioa){
        if(super.getBalioOna() == balioa){
            return true;
        }
        else{
            return false;
        }
    }
}