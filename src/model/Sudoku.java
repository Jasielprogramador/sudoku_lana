package model;

import java.util.*;

public class Sudoku {

	private Gelaxka[][] matrizea=new Gelaxka[9][9];

	private final HashMap<Integer, ArrayList<Gelaxka[][]>> matrizeak= new HashMap<>();

	private int maila=0;

	private static Sudoku instantzia=new Sudoku();


	public Sudoku() {
		setZailtasunak();
	}

	private void setZailtasunak() {
		matrizeak.put(1, new ArrayList<>());
		matrizeak.put(2, new ArrayList<>());
		matrizeak.put(3, new ArrayList<>());

	}

	public static Sudoku getInstance() {
		return instantzia;
	}

	public Gelaxka[][] getSudokuBat(String zailtasuna){
		//mapetik sudoku bat bueltatzen du
		return matrizeak.get(Integer.parseInt(zailtasuna)).get(0);
	}

	public void sartuSudokuaMatrizeaken(Integer zailtasuna,Gelaxka[][] sudokua){
		ArrayList<Gelaxka[][]> lista=matrizeak.get(zailtasuna);
		lista.add(sudokua);
	}

	public void setMatrizea(Gelaxka[][] matrizea) {
		this.matrizea = matrizea;
	}

	public String getMatrizeaBalioa(int lerro, int zut) {
		Integer unekoa= (Integer)matrizea[lerro][zut].getBalioa();
		return unekoa.toString();
	}
	public Gelaxka getGelaxka(int lerro,int zut){
		Gelaxka unekoa= matrizea[lerro][zut];
		return unekoa;
	}

	public void setMaila(int m){
		this.maila = m;
	}

	public boolean setJokalariarenBalioa(int errenkada, int zut,String zbkString){
		int zbk=Integer.parseInt(zbkString);
		matrizea[errenkada][zut].setBalioa(zbk);
		return true;
	}

	public Gelaxka[][] getMatrizea(){return matrizea; }

	public int getMaila(){return maila;}


}
