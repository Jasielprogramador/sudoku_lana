package model.modelutils;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import model.ranking.Erabiltzaile;
import model.sudoku.SudokuLib;

import static java.util.stream.Collectors.toList;


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

	public List<Erabiltzaile> irakurriRanking() {
		Path fitx = Paths.get("ranking.txt");
		Stream<String> lines;
		List<Erabiltzaile> datuak=new ArrayList<>();
		try {
			lines = Files.lines(fitx)
					.onClose(() -> System.out.println("'ranking.txt' ondo irakurri da"));

			datuak = lines.map(p -> {
				//getMaila(0)+"-"+getPuntuazioa(0)+"-"+getIzena(0)
				String[] unekoa = p.split("-");
				Erabiltzaile erab = new Erabiltzaile();
				erab.setPuntuazioa(Double.parseDouble(unekoa[1]));
				erab.setMaila(Integer.parseInt(unekoa[0]));
				erab.setIzena(unekoa[2]);
				return erab;
			})
			.collect(toList());

		} catch (IOException e) {
			System.out.println("Fitxategia ez da existitzen, berria sortuko da");
			try {
				rankingBerriaSortu();
			} catch (IOException i) {
				i.printStackTrace();
			}
		}
		return datuak;
	}
	
	private void rankingBerriaSortu() throws IOException {
		File fitx = new File("ranking.txt");
		fitx.createNewFile();
		irakurriRanking();
	}

	public void idatziRanking(String testua) {
		try {
			//FIXME: true parametroak new line moduan funtzionatu beharko luke, baina ez du ezer egiten

			BufferedWriter bw = new BufferedWriter(new FileWriter("ranking.txt",true));
			bw.newLine();
			bw.write(testua);
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Reader getInstance() {
		return instance;
	}

	public Reader() {}
}
