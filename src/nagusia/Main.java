package nagusia;

import model.Session;
import model.Sudoku;
import model.modelutils.Reader;
import sudokuGUI.LogInGUI;

public class Main {
	public static void main(String[] args) {
		//BISTA
		LogInGUI log=new LogInGUI();

		//EREDUA
		Sudoku.getInstance(); //sudokua kargatzeko
		Session.getInstantzia(); //Session kargatzeko
	}

}
