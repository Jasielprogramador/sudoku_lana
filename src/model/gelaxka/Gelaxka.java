package model.gelaxka;


import java.util.ArrayList;

public class Gelaxka {
	protected int balioa;
	protected ArrayList<Integer> balioPosibleak;
	protected int balioOna;

	public Gelaxka(int pBalio) {
		balioa=pBalio;
		balioOna=0;
		balioPosibleak = new ArrayList<Integer>();
	}

}
