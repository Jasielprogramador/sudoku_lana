package model.sudoku;

import model.modelutils.Enumeratzailea;
import model.modelutils.Timerra;

import java.util.Arrays;
import java.util.Observable;

public class JokoMatrizea extends Observable {

    private int laguntzaKop=0;

    private Sudoku sudoku;

    private int[][] jokokoSudoku=new int[9][9];

    private static JokoMatrizea instantzia = new JokoMatrizea();

    private JokoMatrizea(){

    }

    public static JokoMatrizea getInstance(){return instantzia;}

    public void setSudoku(Sudoku sudokum){
        sudoku=sudokum;
        jokokoSudoku=sudokum.getSudokuHutsa();
    }

    public boolean setJokalariarenBalioa(int errenkada, int zut,String zbkString){
        int zbk=Integer.parseInt(zbkString);
        jokokoSudoku[zut][errenkada]=zbk;
        return true;
    }



    public void laguntzaKopHanditu(){
        laguntzaKop++;
    }

    public boolean emaitzaEgiaztatu(){
        boolean emaitza = true;
        int i = 0;
        while(i< 9 && emaitza){
            int j = 0;
            while(j< 9 && emaitza){
                int g = jokokoSudoku[j][i];
                if(g!=sudoku.getEmaitzaBalioa(j,i) || g==0){
                    System.out.println("j="+j+"     i="+i);
                    emaitza=false;
                }
                j++;
            }
            i++;
        }
        if (emaitza){
            setChanged();
            notifyObservers(Arrays.asList(Enumeratzailea.BUKATUTA));
        }
        return emaitza;
    }


    public String puntuazioaKalkulatu(){
        double puntuazioa = 0.0;
        double denbora = (Timerra.getInstance().igarotakoDenbora())/1000; //segundutan
        puntuazioa = (3000* sudoku.getMaila())/(denbora+(30*laguntzaKop));

        return String.format("%.2f", puntuazioa);
    }


    public boolean ondoDago(int errenkada, int zut, int balioa){

        //TODO: metodoa kendu daiteke ....
        boolean bool=true;
        //zutabea begiratu
        for(int i=0;i<9;i++){
            if(i!=zut && jokokoSudoku[i][errenkada]==balioa){
                setChanged();
                notifyObservers(Arrays.asList(Enumeratzailea.BALIO_TXARRA,errenkada+1,i+1));
                bool=false;
            }
        }

        //errenkada begiratu
        for(int j=0;j<9;j++){
            if(j!=errenkada && jokokoSudoku[zut][j]==balioa){
                setChanged();
                notifyObservers(Arrays.asList(Enumeratzailea.BALIO_TXARRA,j+1,zut+1));  //Arrays.asList(zut,j)
                bool=false;
            }
        }

        //karratua begiratu
        int errenkadaKarratua=errenkada-(errenkada % 3);
        int zutKarratua=zut-(zut % 3);

        for (int a=errenkadaKarratua;a<errenkadaKarratua+3;a++){
            for (int b=zutKarratua;b<zutKarratua+3;b++){
                if(jokokoSudoku[b][a]==balioa){
                    if(a!=errenkada || b!=zut){  //a==errenkada && b==zut  --logika negatua--> a!=errenkada || b!=zut
                        setChanged();
                        notifyObservers(Arrays.asList(Enumeratzailea.BALIO_TXARRA,-1));
                        bool=false;
                    }

                }
            }
        }

        return bool;
    }

    public int[] soleCandidate(){
        int[] emaitza = new int[3];
        boolean[] lista = new boolean[9];
        for (int i = 0;i<lista.length;i++){
            lista[i] = false;
        }
        boolean aurkitua = false;
        int i = 0;
        while(i<9 && !aurkitua){
            int j = 0;
            while(j<9 && !aurkitua){
                if(jokokoSudoku[i][j] == 0) {
                    errenkadaBegiratu(lista, i);
                    zutabeaBegiratu(lista, j);
                    barrukoMatrizeaBegiratu(lista, j);
                    int[] aux = konprobatu(lista);
                    if(aux[1] == 1){
                        aurkitua = true;
                        emaitza[0] = aux[0];
                        emaitza[1] = i+1;
                        emaitza[2] = j+1;
                    }
                }
                j++;
            }
            i++;
        }
        return emaitza;
    }

    private void errenkadaBegiratu(boolean[] lista,int errenkada){
        for(int j = 0;j<9;j++){
            if(jokokoSudoku[errenkada][j] != 0){
                lista[jokokoSudoku[errenkada][j]-1] = true;
            }
        }
    }

    private void zutabeaBegiratu(boolean[] lista,int zutabea){
        for(int i = 0;i<9;i++){
            if(jokokoSudoku[i][zutabea] != 0){
                lista[jokokoSudoku[i][zutabea]-1] = true;
            }
        }
    }

    private void barrukoMatrizeaBegiratu(boolean[] lista,int zutabea){
        if(zutabea == 1 || zutabea == 4 || zutabea == 7) {
            zutabea--;
        }
        else if(zutabea == 2 || zutabea == 5 || zutabea == 8){
            zutabea--;
            zutabea--;
        }
        for (int j = zutabea; j < zutabea + 3; j++) {
            for (int i = 0; i < 3; i++) {
                if(jokokoSudoku[i][j] != 0){
                    lista[jokokoSudoku[i][j]-1] = true;
                }
            }
        }
    }

    private int[] konprobatu(boolean[] lista){
        int kont = 0;
        int[] emaitza = new int[2];
        for(int i =0;i<lista.length;i++){
            if(lista[i] == true){
                kont ++;
            }
            else{
                emaitza[0]=i+1;
            }
        }
        if(kont == lista.length-1){
            emaitza[1] = 1;
        }
        else{
            emaitza[1] = 0;
        }
        return emaitza;
    }

    public int[] uniqueCandidate(){
        int[] emaitza = new int[3];
        boolean aurkitua = false;
        int aux;
        int i = 0;
        while(i<9 && !aurkitua) {
            int j = 0;
            while (j < 9 && !aurkitua) {
                if (jokokoSudoku[i][j] == 0) {
                    aux =
                }
            }
        }
    }

    private void balioaErrenkadanBilatu(int errenkada, int zutabea, int balioa){
        int kont = 0;
        for(int j = 0;j<9;j++){
            if(jokokoSudoku[errenkada][j] == balioa){
                kont++;
            }
            else if(j - zutabea <3){
                kont++;
            }
        }
    }

    private void balioaZutabeanBilatu(int errenkada, int zutabea, int balioa){
        int kont = 0;
        for(int i = 0;i<9;i++){
            if(jokokoSudoku[i][zutabea] == balioa){
                kont++;
            }
            else if(i - errenkada <3){
                kont++;
            }
        }
    }

    private int zutabekoLehenBalioaLortu(int zutabea){
        int aux = 0;
        for(int i = 0;i<9;i++){
            if(jokokoSudoku[i][zutabea] != 0){
                aux = jokokoSudoku[i][zutabea];
                break;
            }
        }
        return aux;
    }
}
