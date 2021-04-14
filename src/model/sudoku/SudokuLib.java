package model.sudoku;

import model.gelaxka.Gelaxka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SudokuLib {
    private static SudokuLib instantzia=new SudokuLib();

    //private static List<Sudoku> sudokuak=new ArrayList<>();

    private final HashMap<Integer, ArrayList<Sudoku>> matrizeak= new HashMap<>();


    private SudokuLib() {
        setMatrizeak();
    }

    private void setMatrizeak() {
        matrizeak.put(1,new ArrayList<>());
        matrizeak.put(2,new ArrayList<>());
        matrizeak.put(3,new ArrayList<>());
    }


    public static SudokuLib getInstance() {
        return instantzia;
    }

    public void sartuSudokuaMatrizeaken(Integer zailtasuna, Sudoku sudokua){
        ArrayList<Sudoku> lista=matrizeak.get(zailtasuna);
        lista.add(sudokua);
    }

    public Sudoku getSudokuBat(String zailtasuna){
        //mapetik sudoku bat bueltatzen du
        return matrizeak.get(Integer.parseInt(zailtasuna)).get(0);
    }

}
