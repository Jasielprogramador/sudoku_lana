package model.laguntza;

import model.gelaxka.Gelaxka;
import model.modelutils.Enumeratzailea;
import model.sudoku.JokoMatrizea;

import java.util.Arrays;
import java.util.Observable;

public class Sole extends Observable implements Laguntza {

    public int[] soluzioaLortu() {
        return soleCandidate();
    }

    public boolean barrukoMatBegiratu(boolean[] lista, int zutabea, int errenkada) {
        //metodo languntzailea
        //lista aldatzen du gelaxkaren balioa != 0 bada

        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();

        int errenkadaKarratua = errenkada - (errenkada % 3);
        int zutKarratua = zutabea - (zutabea % 3);

        for (int a = errenkadaKarratua; a < errenkadaKarratua + 3; a++) {
            for (int b = zutKarratua; b < zutKarratua + 3; b++) {
                if (sudoku[a][b].getBalioa() != 0) lista[sudoku[a][b].getBalioa() - 1] = true;

            }
        }
        return (konprobatu(lista)[1] == 1);
    }


    private boolean errenkadaBegiratu(boolean[] lista, int errenkada) {
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();
        for (int j = 0; j < 9; j++) {
            if (sudoku[errenkada][j].getBalioa() != 0) {
                lista[sudoku[errenkada][j].getBalioa() - 1] = true;
            }
        }
        return (konprobatu(lista)[1] == 1);
    }

    private boolean zutabeaBegiratu(boolean[] lista, int zutabea) {
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();
        for (int i = 0; i < 9; i++) {
            if (sudoku[i][zutabea].getBalioa() != 0) {
                lista[sudoku[i][zutabea].getBalioa() - 1] = true;
            }
        }

        return (konprobatu(lista)[1] == 1);
    }


    //----------------------------------------------------------------------------------------------------------------

    /*SOLE CANDIDATE*/
    public int[] soleCandidate() {

        JokoMatrizea.getInstance().laguntzaKopHanditu();
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();

        int[] emaitza = new int[3];
        boolean[] lista = new boolean[9];
        boolean aurkitua = false;
        int i = 0;

        while (i < 9 && !aurkitua) {
            int j = 0;
            while (j < 9 && !aurkitua) {
                if (sudoku[i][j].getBalioa() == 0) {
                    if (errenkadaBegiratu(lista, i)) {
                        aurkitua = true;
                    } else if (zutabeaBegiratu(lista, j)) {
                        aurkitua = true;
                    } else if (barrukoMatrizeaBegiratu(lista, j, i)) {
                        aurkitua = true;
                    }

                    if (aurkitua) {
                        emaitza[0] = konprobatu(lista)[0];
                        emaitza[1] = i + 1;
                        emaitza[2] = j + 1;
                    }
                }
                j++;
            }
            i++;
        }

        setChanged();
        notifyObservers(Arrays.asList(Enumeratzailea.SOLE_PISTA, emaitza));

        return emaitza;
    }


    private boolean barrukoMatrizeaBegiratu(boolean[] lista, int zutabea, int errenkada) {
        return barrukoMatBegiratu(lista, zutabea, errenkada);
    }


    private int[] konprobatu(boolean[] lista) {
        //bi zenbaki erabiltzen ditu informazioa emateko
        //int[0] azken errorea non dagoen markatzen du
        //int[1] == 1 bada ondo dago
        int kont = 0;
        int[] emaitza = new int[2];

        for (int i = 0; i < lista.length; i++) {
            if (lista[i]) {
                kont++;
            } else {
                emaitza[0] = i + 1;
            }
        }

        if (kont == lista.length - 1) {
            emaitza[1] = 1;
        } else {
            emaitza[1] = 0;
        }
        return emaitza;
    }
}