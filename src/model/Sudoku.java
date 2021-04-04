package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Sudoku extends Observable {

	private Gelaxka[][] matrizea=new Gelaxka[9][9];

	private HashMap<Integer, ArrayList<Gelaxka[][]>> matrizeak= new HashMap<Integer, ArrayList<Gelaxka[][]>>();

	private static Sudoku instantzia=new Sudoku();



	public Sudoku() {
		setZailtasunak();
	}

	private void setZailtasunak() {
		matrizeak.put(1,new ArrayList<Gelaxka[][]>());
		matrizeak.put(2,new ArrayList<Gelaxka[][]>());
		matrizeak.put(3,new ArrayList<Gelaxka[][]>());

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
		setChanged();
		notifyObservers(matrizea);
	}

	public String getMatrizeaBalioa(int lerro, int zut) {
		Integer unekoa= (Integer)matrizea[lerro][zut].getBalioa();
		return unekoa.toString();
	}
	public Gelaxka getGelaxka(int lerro,int zut){
		Gelaxka unekoa= matrizea[lerro][zut];
		return unekoa;
	}


	public boolean setJokalariarenBalioa(int zut, int errenkada,String zbkString){
		int zbk=Integer.parseInt(zbkString);
		matrizea[zut][errenkada].setBalioa(zbk);
		return true;
	}
}
