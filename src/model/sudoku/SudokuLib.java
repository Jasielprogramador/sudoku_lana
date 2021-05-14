package model.sudoku;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

    public Sudoku getSudokuBat(int zailtasuna){
        //mapetik sudoku bat bueltatzen du
        Sudoku sudo=new Sudoku();
        try{
            sudo=matrizeak.get(zailtasuna).get(0);
        }
        catch (IndexOutOfBoundsException e){
            if (zailtasuna==3) sudo=null;
            else sudo=getSudokuBat(zailtasuna+1); //zaitasun gehiagoko sudoku bat bueltatu (suposatuz zailtasun honetakoa egongo dela)
        }
        return sudo;
    }

    public void sudokuaGorde(String s, BufferedReader br) throws IOException {
        //sudokuaren datuak hartu
        int errenkada=0;

        String unekoLerroa="";

        Sudoku sudoku=new Sudoku();

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

                    sudoku.setSudokuHutsaBalioa(zut,errenkada,intLag);
                    zut++;
                }
                errenkada++;
            }

            //balio ona idatzi
            else {
                int zut=0;

                for(char i: lista) {
                    int intLag=Character.getNumericValue(i);
                    sudoku.setEmaitzaBalioa(zut,errenkada-9,intLag);
                    zut++;
                }
                errenkada++;
            }
        }


        sudoku.setMaila(Integer.parseInt(s));
        SudokuLib.getInstance().sartuSudokuaMatrizeaken(Integer.parseInt(s),sudoku);
    }


}
