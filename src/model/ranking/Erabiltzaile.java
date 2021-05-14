package model.ranking;

public class Erabiltzaile {

    private String izena;
    private int maila;
    private double puntuazioa;


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

    public double getPuntuazioa() {
        return puntuazioa;
    }

    public void setPuntuazioa(double puntuazioa) {
        this.puntuazioa = puntuazioa;
    }
}
