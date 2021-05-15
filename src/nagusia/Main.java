package nagusia;

import model.ranking.Session;
import model.sudoku.SudokuLib;
import sudokuGUI.LogInGUI;

public class Main {
	public static void main(String[] args) {

		//BISTA
		new LogInGUI();

		//EREDUA
		SudokuLib.getInstance(); //sudokua kargatzeko
		Session.getInstantzia(); //Session kargatzeko
	}

}
