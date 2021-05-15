package model.sudoku;

import model.ranking.Session;
import model.sudoku.gelaxka.Gelaxka;
import model.sudoku.gelaxka.GelaxkaEditable;
import model.sudoku.gelaxka.GelaxkaFactory;
import model.laguntza.Sole;
import model.modelutils.Enumeratzailea;
import model.modelutils.Timerra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class JokoMatrizea extends Observable {

    private int laguntzaKop = 0;

    private int maila;


    private final Gelaxka[][] sudoku = new Gelaxka[9][9];

    private static final JokoMatrizea instantzia = new JokoMatrizea();

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

    private void hasierakoHautagaiakLortu(int i, int j) {
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
            if (!lista[i]) {
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

        kargatuHautagaiak();
    }

    public void kargatuHautagaiak(){
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


    public double puntuazioaKalkulatu() {

        double milis=Timerra.getInstance().igarotakoDenbora();

        double puntuazioa;
        double denbora = milis / 1000; //segundutan
        puntuazioa = (3000 * maila) / (denbora + (30 * laguntzaKop));

        Session.getInstantzia().setPuntuazioa(puntuazioa);

        return puntuazioa;
    }

}
