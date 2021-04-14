package model.sudoku;

import model.gelaxka.Gelaxka;
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
}
