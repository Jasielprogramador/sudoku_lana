package model;

import model.modelutils.Reader;
import model.sudoku.SudokuLib;

public class Session {

    private String izena;
    private int maila;

    private static Session instantzia = new Session();

    public static Session getInstantzia(){return instantzia;}

    private Session(){
        Reader.getInstance().irakurri();
    }

    public void setIzena(String izena) {
        this.izena = izena;
    }

    public void setMaila(int maila) {
        this.maila = maila;
    }
    
    public int getMaila() {
    	return maila;
    }

    public String getIzena(){
        return izena;
    }
}
