package nagusia;

import model.Session;
import model.modelutils.Reader;
import model.sudoku.Sudoku;
import model.sudoku.SudokuLib;
import sudokuGUI.LogInGUI;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		Reader.getInstance().irakurriRanking();

		//BISTA
		LogInGUI log=new LogInGUI();

		//EREDUA
		SudokuLib.getInstance(); //sudokua kargatzeko
		Session.getInstantzia(); //Session kargatzeko
	}

}
