package model;


import java.util.ArrayList;

public class Gelaxka {
	private int balioa;
	private ArrayList<Integer> balioPosibleak;
	private int balioOna;

	public Gelaxka(int pBalio) {
		balioa=pBalio;
		balioOna=0;
		balioPosibleak = new ArrayList<Integer>();
	}

	public int getBalioa() {
		return balioa;
	}

	public void setBalioa(int balioa) {
		this.balioa = balioa;
	}

	public int getBalioOna() {
		return balioOna;
	}

	public void setBalioOna(int balioOna) {
		this.balioOna = balioOna;
	}

	public void setBalioPosibleak(ArrayList<Integer> a){balioPosibleak=a;}

	public ArrayList<Integer> getBalioPosibleak(){return balioPosibleak;}

}
