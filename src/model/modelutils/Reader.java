package model.modelutils;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import model.gelaxka.Gelaxka;
import model.gelaxka.GelaxkaFactory;
import model.sudoku.Sudoku;
import model.sudoku.SudokuLib;


public class Reader {

	private static Reader instance=new Reader();
	private String rankingFitx = "ranking.txt";

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

	public ArrayList<String> irakurriRanking() {
		Path fitx = Paths.get("ranking.txt");
		Stream<String> lines;
		ArrayList<String> datuak;
		try {
			lines = Files.lines(fitx)
					.onClose(() -> System.out.println("'ranking.txt' ondo irakurri da"));
			datuak = (ArrayList<String>) lines.collect(Collectors.toList());
			return datuak;
		} catch (IOException e) {
			System.out.println("Fitxategia ez da existitzen, berria sortuko da");
			try {
				rankingBerriaSortu();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		return new ArrayList<String>();
	}
	
	private void rankingBerriaSortu() throws IOException {
		File fitx = new File(rankingFitx);
		fitx.createNewFile();
	}

	public void idatzi(ArrayList<String> lista) {
		try {
			Files.write(Paths.get(rankingFitx), lista, false);
			     //(Iterable<String>)StringStream.range(0, 5000).mapToObj(String::valueOf)::iterator);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Reader getInstance() {
		return instance;
	}

	public Reader() {}
}
