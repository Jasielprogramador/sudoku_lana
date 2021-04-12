package model.modelutils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import model.Gelaxka;
import model.GelaxkaFactory;
import model.Sudoku;


public class Reader {

	private static Reader instance=new Reader();

	public void irakurri() {
		try {

			File fitxategi = new File ("sudoku.txt");
			FileReader fr = new FileReader(fitxategi);
			BufferedReader br = new BufferedReader(fr);

			String unekoLerroa="";

			while((unekoLerroa=br.readLine())!=null){
				System.out.println(unekoLerroa);

				if (unekoLerroa.equals("1")){
					sudokuaGorde("1",br);
				}
				else if(unekoLerroa.equals("2")){
					sudokuaGorde("2",br);
				}
				else if(unekoLerroa.equals("3")){
					sudokuaGorde("3",br);
				}
			}

			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void sudokuaGorde(String s, BufferedReader br) throws IOException {
		//sudokuaren datuak hartu
		int errenkada=0;

		String unekoLerroa="";

		Gelaxka[][] sudokua=new Gelaxka[9][9];

		while(errenkada!=18) {
			unekoLerroa=br.readLine();
			char[] lista = unekoLerroa.toCharArray();

			System.out.println(lista);

			//erabiltzaile matrizea idatzi
			if(errenkada<9) {
				int zut=0;

				for(char i: lista) {
					int intLag=Character.getNumericValue(i);
					//hau aldatuta dago
					Gelaxka gelaxka = GelaxkaFactory.getInstance().sortuGelaxka(intLag);
					sudokua[zut][errenkada]=gelaxka;
					zut++;
				}
				errenkada++;
			}

			//balio ona idatzi
			else {
				int zut=0;

				for(char i: lista) {
					int intLag=Character.getNumericValue(i);
					sudokua[zut][errenkada-9].setBalioOna(intLag);
					zut++;
				}
				errenkada++;
			}
		}

		Sudoku.getInstance().sartuSudokuaMatrizeaken(Integer.parseInt(s),sudokua);

	}

	public static Reader getInstance() {
		return instance;
	}

	public Reader() {

	}
}
