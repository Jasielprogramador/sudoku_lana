package nagusia;

import model.Session;
import model.sudoku.Sudoku;
import model.sudoku.SudokuLib;
import sudokuGUI.LogInGUI;

public class Main {
	public static void main(String[] args) {
		//BISTA
		LogInGUI log=new LogInGUI();

		//EREDUA
		SudokuLib.getInstance(); //sudokua kargatzeko
		Session.getInstantzia(); //Session kargatzeko
	}

}
