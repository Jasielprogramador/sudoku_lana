package model.sudoku;

import model.gelaxka.Gelaxka;

import java.util.*;

public class Sudoku {

	private int[][] emaitza=new int[9][9];

    private int[][] sudokuHutsa=new int[9][9];

	private int maila=0;
	

	public Sudoku() {
//		setZailtasunak();
	}

//	private void setZailtasunak() {
//		matrizeak.put(1, new ArrayList<>());
//		matrizeak.put(2, new ArrayList<>());
//		matrizeak.put(3, new ArrayList<>());
//
//	}

	public void setEmaitzaBalioa(int zut,int errenkada,int balioa){
		emaitza[errenkada][zut]=balioa;
	}

	public void setSudokuHutsaBalioa(int zut,int errenkada,int balioa){
		sudokuHutsa[errenkada][zut]=balioa;
	}

	public void setMaila(int m){
		this.maila = m;
	}

	public int getMaila(){return maila;}


	public int getEmaitzaBalioa(int zut,int errenkada){
		return emaitza[zut][errenkada];
	}

	public int getSudokuHutsaBalioa(int zut,int errenkada){
		return sudokuHutsa[zut][errenkada];
	}

	public int[][] getSudokuHutsa(){
		return sudokuHutsa;
	}


}
