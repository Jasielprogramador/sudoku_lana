package model.modelutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.gelaxka.Gelaxka;
import model.gelaxka.GelaxkaFactory;
import model.sudoku.Sudoku;
import model.sudoku.SudokuLib;


public class Reader {

	private static Reader instance=new Reader();

	public void irakurri() {
		// sudoku.txt fitxategia irakurri,
		// sudokuaGorde metodoaren laguntzarekin
		try {

			File fitxategi = new File ("sudoku.txt");
			FileReader fr = new FileReader(fitxategi);
			BufferedReader br = new BufferedReader(fr);

			String unekoLerroa="";

			while((unekoLerroa=br.readLine())!=null){
				System.out.println(unekoLerroa);

				if (unekoLerroa.equals("1")){
					SudokuLib.getInstance().sudokuaGorde("1",br);
				}
				else if(unekoLerroa.equals("2")){
					SudokuLib.getInstance().sudokuaGorde("2",br);
				}
				else if(unekoLerroa.equals("3")){
					SudokuLib.getInstance().sudokuaGorde("3",br);
				}
			}

			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}


	public static Reader getInstance() {
		return instance;
	}

	public Reader() {

	}
}
