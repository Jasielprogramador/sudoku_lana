package model.laguntza;

import model.sudoku.gelaxka.Gelaxka;
import model.sudoku.gelaxka.GelaxkaEditable;
import model.sudoku.gelaxka.GelaxkaNotEditable;
import model.modelutils.Enumeratzailea;
import model.sudoku.JokoMatrizea;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class Unique extends Observable implements Strategy {

    public Unique(){

    }

    public int[] soluzioaLortu() {
        return uniqueCandidate();
    }

    public int[] uniqueCandidate() {
        int[] emaitza = new int[3];
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();

        boolean aurkitua = false;

        int i = 0;
        while (i < 9 && !aurkitua) {
            int j = 0;
            while (j < 9 && !aurkitua) {
                if (sudoku[i][j].getBalioa() == 0) {
                    GelaxkaEditable gE = (GelaxkaEditable) sudoku[i][j];
                    ArrayList<Integer> lista = gE.getHautagaiak();

                    int unekoa = 0;

                    int errenkada = i - (i % 3);
                    int zutabea = j - (j % 3);

                    while (unekoa < lista.size() && !aurkitua) {
                        if (aurkitua = begiratuAlbokoak(lista.get(unekoa), errenkada, zutabea, i, j)) {  //lista.get(unekoa),errenkada,zutabea,i,j
                            emaitza[0] = lista.get(unekoa);
                            emaitza[1] = i;
                            emaitza[2] = j;
                        }

                        unekoa++;
                    }

                }
                j++;
            }

            i++;
        }

        //emaitza aurkitu bada
        if (aurkitua) {
            setChanged();
            notifyObservers(Arrays.asList(Enumeratzailea.UNIQUE_PISTA, emaitza));
        }

        return emaitza;
    }

    private boolean begiratuAlbokoak(int unekoa, int errenkada, int zutabea, int i, int j) {
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();
        boolean zut = true;
        boolean erre = true;
        boolean indeter = true;

        boolean karratu = balioaMatrizeanBilatu(unekoa, i, j);

        int zutIndeter = 0;
        int erreIndeter = 0;


        /**
         * 8 |   |
         * ---------
         *   | 7 |   |  8
         *   | x | 5 |
         *   | 9 |   |
         *
         * goiko kasua indeterminazio kasua da eta ezin daiteke balioa determinatu
         *
         * guztiak dauden kasua
         *
         * guztiak egon behar ez direnean GelaxkaNotEditable dagoelako
         *
         *
         */

        if (!karratu) {
            //3 zutabeak begiratu
            for (int a = 0; a <= 2; a++) {
                if (j != zutabea + a) {
                    if (!balioaZutabeanBilatu(unekoa, zutabea + a)) {
                        if ((sudoku[i][zutabea + a] instanceof GelaxkaNotEditable)) zutIndeter++;
                        else zut = false;
//                        zut=zut && (sudoku[i][zutabea+a] instanceof GelaxkaNotEditable);
                    }
//                    if( !(sudoku[i][zutabea+a] instanceof GelaxkaNotEditable) ) zut= zut && balioaZutabeanBilatu(unekoa,zutabea+a);
//                    else zutIndeterminazio[a]=a;
                }
            }


            //aurrekoa ondo joan bada bestea begiratu bestela ezer
            if (zut) {
                for (int a = 0; a <= 2; a++) {
                    if (i != errenkada + a) {
                        if (!balioaErrenkadanBilatu(unekoa, errenkada + a)) {
                            if ((sudoku[errenkada + a][j] instanceof GelaxkaNotEditable)) erreIndeter++;
                            else erre = false;
                        }

                    }
                }

            }

            //indeterminazio kasua konprobatu LoL
            for (int x = 0; x < zutIndeter; x++) {
                for (int y = 0; y < erreIndeter; y++) {
                    if (sudoku[errenkada + y][zutabea + x].getBalioa() == 0) {
                        indeter = false;
                        break;
                    }
                }
            }

        }

        return (zut && erre && indeter && !karratu);

    }

    private boolean balioaMatrizeanBilatu(int balioa, int zutabea, int errenkada) {
        int errenkadaKarratua = errenkada - (errenkada % 3);
        int zutKarratua = zutabea - (zutabea % 3);
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();

        int a = errenkadaKarratua;

        boolean berdina = false;

        while (a < errenkadaKarratua + 3 && !berdina) {

            int b = zutKarratua;
            while (b < zutKarratua + 3 && !berdina) {
                berdina = (sudoku[b][a].getBalioa() == balioa);
                b++;
            }
            a++;
        }

        return berdina;
    }

    private boolean balioaErrenkadanBilatu(int balioa, int errenkada) {
        boolean emaitza = false;
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();
        int j = 0;
        while (j < 9 && !emaitza) {
            if (sudoku[errenkada][j].getBalioa() == balioa) emaitza = true;
            j++;
        }
        return emaitza;
    }

    private boolean balioaZutabeanBilatu(int balioa, int zutabea) {
        boolean emaitza = false;
        Gelaxka[][] sudoku = JokoMatrizea.getInstance().getSudoku();
        int i = 0;
        while (i < 9 && !emaitza) {
            if (sudoku[i][zutabea].getBalioa() == balioa) emaitza = true;
            i++;
        }
        return emaitza;
    }
}