package nagusia;

import model.Sudoku;
import model.modelutils.Reader;
import model.modelutils.Timerra;
import sudokuGUI.LogInGUI;
import sudokuGUI.SudokuGUI;

public class Main {
	public static void main(String[] args) {
		//BISTA
		LogInGUI log=new LogInGUI();

		//EREDUA
		Sudoku.getInstance(); //sudokua kargatzeko
		Reader.getInstance().irakurri(); //sudoku guztiak kargatu testutik :S
		Timerra.getInstance().timerraHasi();
	}

}
