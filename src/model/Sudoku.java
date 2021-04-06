package model;

import model.modelutils.Timerra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

public class Sudoku extends Observable {

	private Gelaxka[][] matrizea=new Gelaxka[9][9];

	private HashMap<Integer, ArrayList<Gelaxka[][]>> matrizeak= new HashMap<Integer, ArrayList<Gelaxka[][]>>();

	private int maila=0;

	private int laguntzaKop=0;

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

	public void laguntzaKopHanditu(){
		laguntzaKop++;
	}


	public boolean setJokalariarenBalioa(int zut, int errenkada,String zbkString){
		int zbk=Integer.parseInt(zbkString);
		matrizea[zut][errenkada].setBalioa(zbk);
		return true;
	}

	public boolean emaitzaEgiaztatu(){
		boolean emaitza = true;
		int i = 0;
		while(i<matrizea.length && emaitza){
			int j = 0;
			while(j<matrizea[i].length && emaitza){
				Gelaxka g = getGelaxka(j,i);
				if(!g.balioOnaDa(g.getBalioa()) ||g.getBalioa()==0){
					emaitza = false;
				}
				j++;
			}
			i++;
		}
		return emaitza;
	}


	public double puntuazioaKalkulatu(){
		double puntuazioa = 0.0;

		if(emaitzaEgiaztatu()){
			double denbora = (Timerra.getInstance().igarotakoDenbora())/10000;
			System.out.println(String.format("%.2f", denbora));
			puntuazioa = (3000*maila)/(denbora+(30*laguntzaKop));
		}
		return puntuazioa;
	}
}
