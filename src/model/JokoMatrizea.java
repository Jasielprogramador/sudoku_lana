package model;

import model.modelutils.Enumeratzailea;
import model.modelutils.Timerra;

import java.util.Arrays;
import java.util.Observable;

public class JokoMatrizea extends Observable {

    private int laguntzaKop=0;

    private static JokoMatrizea instantzia = new JokoMatrizea();

    private JokoMatrizea(){

    }

    public static JokoMatrizea getInstantzia(){return instantzia;}


    public void laguntzaKopHanditu(){
        laguntzaKop++;
    }

    public boolean emaitzaEgiaztatu(){
        boolean emaitza = true;
        int i = 0;
        while(i< Sudoku.getInstance().getMatrizea().length && emaitza){
            int j = 0;
            while(j< Sudoku.getInstance().getMatrizea()[i].length && emaitza){
                Gelaxka g = Sudoku.getInstance().getGelaxka(j,i);
                if(!g.balioOnaDa(g.getBalioa()) ||g.getBalioa()==0){
                    emaitza = false;
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
        puntuazioa = (3000* Sudoku.getInstance().getMaila())/(denbora+(30*laguntzaKop));

        return String.format("%.2f", puntuazioa);
    }


    public boolean ondoDago(int errenkada, int zut, int balioa){

        boolean bool=true;
        //zutabea begiratu
        for(int i=0;i<9;i++){
            if(i!=zut && Sudoku.getInstance().getMatrizea()[errenkada][i].getBalioa()==balioa){
                setChanged();
                notifyObservers(Arrays.asList(Enumeratzailea.BALIO_TXARRA,errenkada+1,i+1));
                bool=false;
            }
        }

        //errenkada begiratu
        for(int j=0;j<9;j++){
            if(j!=errenkada && Sudoku.getInstance().getMatrizea()[j][zut].getBalioa()==balioa){
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
                if(Sudoku.getInstance().getMatrizea()[a][b].getBalioa()==balioa){
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
