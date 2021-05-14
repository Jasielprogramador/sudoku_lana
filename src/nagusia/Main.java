package nagusia;

import model.ranking.Session;
import model.sudoku.SudokuLib;
import sudokuGUI.LogInGUI;

import java.io.FileNotFoundException;

public class Main {
	public static void main(String[] args) throws FileNotFoundException {

		//BISTA
		LogInGUI log=new LogInGUI();

		//EREDUA
		SudokuLib.getInstance(); //sudokua kargatzeko
		Session.getInstantzia(); //Session kargatzeko
	}

}
