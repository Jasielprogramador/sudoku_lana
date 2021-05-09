package model.sudoku;

import model.gelaxka.Gelaxka;
import model.gelaxka.GelaxkaEditable;
import model.gelaxka.GelaxkaFactory;
import model.gelaxka.GelaxkaNotEditable;
import model.laguntza.Sole;
import model.modelutils.Enumeratzailea;
import model.modelutils.Timerra;
import sun.java2d.x11.XSurfaceData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class JokoMatrizea extends Observable {

    private int laguntzaKop = 0;

    private int maila;


    private Gelaxka[][] sudoku = new Gelaxka[9][9];

    private static JokoMatrizea instantzia = new JokoMatrizea();

    private JokoMatrizea() {

    }

    public static JokoMatrizea getInstance() {
        return instantzia;
    }

    public Gelaxka[][] getSudoku() {
        return sudoku;
    }

    public int getMaila() {
        return maila;
    }


    public Gelaxka lortuGelaxka(int errenkada, int zutabea) {
        return sudoku[errenkada][zutabea];
    }

    public void hasierakoHautagaiakLortu(int i, int j) {
        //booleanez osatutako array bat erabiliz hautagaiak kalkulatzen du
        //errenkada, zutabe eta matrize metodoek kalkulatzen laguntzen dituzte
        boolean[] lista = new boolean[9];

        hasierakoErrenkadaBegiratu(lista, i);
        hasierakoZutabeaBegiratu(lista, j);
        hasierakoMatrizeaBegiratu(lista, j, i);

        ArrayList<Integer> aux = hasierakoHautagaienListaLortu(lista);

        if (lortuGelaxka(i, j) instanceof GelaxkaEditable) {
            GelaxkaEditable g = (GelaxkaEditable) lortuGelaxka(i, j);
            g.setHautagaiak(aux);
        }

    }

    private void hasierakoErrenkadaBegiratu(boolean[] lista, int errenkada) {
        //metodo languntzailea
        //lista aldatzen du gelaxkaren balioa != 0 bada
        for (int j = 0; j < 9; j++) {
            if (sudoku[errenkada][j].getBalioa() != 0) {
                lista[sudoku[errenkada][j].getBalioa() - 1] = true;
            }
        }
    }

    private void hasierakoZutabeaBegiratu(boolean[] lista, int zutabea) {
        //metodo languntzailea
        //lista aldatzen du gelaxkaren balioa != 0 bada
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][zutabea].getBalioa() != 0) {
                lista[sudoku[i][zutabea].getBalioa() - 1] = true;
            }
        }
    }



    private void hasierakoMatrizeaBegiratu(boolean[] lista, int zutabea, int errenkada) {
        Sole sole = new Sole();
        sole.barrukoMatBegiratu(lista, zutabea, errenkada);
    }

    private ArrayList<Integer> hasierakoHautagaienListaLortu(boolean[] lista) {
        ArrayList<Integer> emaitza = new ArrayList<>();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == false) {
                emaitza.add(i + 1);
            }
        }
        return emaitza;
    }

    public void setSudoku(Sudoku sudokum) {

        maila = sudokum.getMaila();

        //jokoko matrizea kargatu
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int unekoa = sudokum.getSudokuHutsaBalioa(i, j);
                if (unekoa == 0) {
                    GelaxkaEditable gelEdit = (GelaxkaEditable) GelaxkaFactory.getInstance().sortuGelaxka(unekoa);
                    gelEdit.setBalioOna(sudokum.getEmaitzaBalioa(i, j));

                    sudoku[i][j] = gelEdit;
                } else {
                    sudoku[i][j] = GelaxkaFactory.getInstance().sortuGelaxka(unekoa);
                }
            }
        }

        //hautagaiak kargatu
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                hasierakoHautagaiakLortu(i, j);
            }
        }
    }


    public void setJokalariarenBalioa(int errenkada, int zut, int zbk) {
        GelaxkaEditable uneko = (GelaxkaEditable) sudoku[zut][errenkada];
        uneko.setBalioa(zbk);
        sudoku[zut][errenkada] = uneko;
    }


    public void laguntzaKopHanditu() {
        laguntzaKop++;
    }


    public boolean emaitzaEgiaztatu() {
        // egiaztatu sudokuaren balioak emaitzaren balioeekin konparatuz
        boolean emaitza = true;
        int i = 0;
        while (i < 9 && emaitza) {
            int j = 0;
            while (j < 9 && emaitza) {
                if (sudoku[i][j] instanceof GelaxkaEditable) {
                    if (!((GelaxkaEditable) sudoku[i][j]).balioOnaDa()) emaitza = false;
                }
                j++;
            }
            i++;
        }
        if (emaitza) {
            setChanged();
            notifyObservers(Arrays.asList(Enumeratzailea.BUKATUTA));
        }
        return emaitza;
    }


    public String puntuazioaKalkulatu() {
        double puntuazioa;
        double denbora = (Timerra.getInstance().igarotakoDenbora()) / 1000; //segundutan
        puntuazioa = (3000 * maila) / (denbora + (30 * laguntzaKop));

        return String.format("%.2f", puntuazioa);
    }

}
