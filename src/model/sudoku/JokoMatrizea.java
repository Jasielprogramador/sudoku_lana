package model.sudoku;

import model.gelaxka.Gelaxka;
import model.gelaxka.GelaxkaEditable;
import model.gelaxka.GelaxkaFactory;
import model.gelaxka.GelaxkaNotEditable;
import model.modelutils.Enumeratzailea;
import model.modelutils.Timerra;
import sun.java2d.x11.XSurfaceData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;

public class JokoMatrizea extends Observable {

    private int laguntzaKop=0;

    private int maila;


    private Gelaxka[][] sudoku=new Gelaxka[9][9];

    private static JokoMatrizea instantzia = new JokoMatrizea();

    private JokoMatrizea(){

    }

    public static JokoMatrizea getInstance(){return instantzia;}

    public Gelaxka[][] getSudoku() {
        return sudoku;
    }

    public int getMaila(){ return maila; }


    public Gelaxka lortuGelaxka(int errenkada, int zutabea){
        return sudoku[errenkada][zutabea];
    }

    public void hasierakoHautagaiakLortu(int i, int j) {
	//booleanez osatutako array bat erabiliz hautagaiak kalkulatzen du
	//errenkada, zutabe eta matrize metodoek kalkulatzen laguntzen dituzte
        boolean[] lista = new boolean[9];

        hasierakoErrenkadaBegiratu(lista,i);
        hasierakoZutabeaBegiratu(lista,j);
        hasierakoMatrizeaBegiratu(lista,j,i);

        ArrayList<Integer> aux = hasierakoHautagaienListaLortu(lista);

        if(lortuGelaxka(i,j) instanceof GelaxkaEditable){
            GelaxkaEditable g = (GelaxkaEditable) lortuGelaxka(i,j);
            g.setHautagaiak(aux);
        }

    }

    private void hasierakoErrenkadaBegiratu(boolean[] lista,int errenkada){
	//metodo languntzailea
	//lista aldatzen du gelaxkaren balioa != 0 bada
        for(int j = 0;j<9;j++){
            if(sudoku[errenkada][j].getBalioa() != 0){
                lista[sudoku[errenkada][j].getBalioa() -1] = true;
            }
        }
    }

    private void hasierakoZutabeaBegiratu(boolean[] lista,int zutabea){
	//metodo languntzailea
	//lista aldatzen du gelaxkaren balioa != 0 bada
        for(int i = 0;i<9;i++){
            if(sudoku[i][zutabea].getBalioa() != 0){
                lista[sudoku[i][zutabea].getBalioa() -1] = true;
            }
        }
    }

    private void hasierakoMatrizeaBegiratu(boolean[] lista,int zutabea,int errenkada){
        barrukoMatBegiratu(lista, zutabea,errenkada);
    }

    private ArrayList<Integer> hasierakoHautagaienListaLortu(boolean[] lista){
        ArrayList<Integer> emaitza = new ArrayList<>();
        for(int i = 0;i<lista.length;i++){
            if(lista[i] == false){
                emaitza.add(i+1);
            }
        }
        return emaitza;
    }

    public void setSudoku(Sudoku sudokum){

        maila=sudokum.getMaila();

        //jokoko matrizea kargatu
        for (int i=0;i<9;i++){
            for (int j=0;j<9;j++){
                int unekoa=sudokum.getSudokuHutsaBalioa(i,j);
                if(unekoa==0){
                    GelaxkaEditable gelEdit= (GelaxkaEditable) GelaxkaFactory.getInstance().sortuGelaxka(unekoa);
                    gelEdit.setBalioOna(sudokum.getEmaitzaBalioa(i,j));

                    sudoku[i][j]=gelEdit;
                }
                else{
                    sudoku[i][j]=GelaxkaFactory.getInstance().sortuGelaxka(unekoa);
                }
            }
        }

        //hautagaiak kargatu
        for (int i=0;i<9;i++) {
            for (int j = 0; j < 9; j++) {
                hasierakoHautagaiakLortu(i,j);
            }
        }
    }


    public void setJokalariarenBalioa(int errenkada, int zut,int zbk){
        GelaxkaEditable uneko= (GelaxkaEditable) sudoku[zut][errenkada];
        uneko.setBalioa(zbk);
        sudoku[zut][errenkada]=uneko;
    }


    public void laguntzaKopHanditu(){
        laguntzaKop++;
    }


    public boolean emaitzaEgiaztatu(){
	// egiaztatu sudokuaren balioak emaitzaren balioeekin konparatuz
        boolean emaitza = true;
        int i = 0;
        while(i< 9 && emaitza){
            int j = 0;
            while(j< 9 && emaitza){
                if(sudoku[i][j] instanceof GelaxkaEditable ){
                    if( !((GelaxkaEditable) sudoku[i][j]).balioOnaDa() ) emaitza=false;
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
        double puntuazioa;
        double denbora = (Timerra.getInstance().igarotakoDenbora())/1000; //segundutan
        puntuazioa = (3000* maila)/(denbora+(30*laguntzaKop));

        return String.format("%.2f", puntuazioa);
    }

    // konprobazio metodoak

    private boolean barrukoMatBegiratu(boolean[] lista, int zutabea,int errenkada) {
	//metodo languntzailea
	//lista aldatzen du gelaxkaren balioa != 0 bada

        int errenkadaKarratua=errenkada-(errenkada % 3);
        int zutKarratua=zutabea-(zutabea % 3);

        for (int a=errenkadaKarratua;a<errenkadaKarratua+3;a++){
            for (int b=zutKarratua;b<zutKarratua+3;b++){
                if(sudoku[a][b].getBalioa() != 0) lista[sudoku[a][b].getBalioa() -1] = true;

            }
        }
        return (konprobatu(lista)[1] == 1);
    }


    private boolean errenkadaBegiratu(boolean[] lista,int errenkada){
        for(int j = 0;j<9;j++){
            if(sudoku[errenkada][j].getBalioa() != 0){
                lista[ sudoku[errenkada][j].getBalioa() -1] = true;
            }
        }
        return (konprobatu(lista)[1] == 1);
    }

    private boolean zutabeaBegiratu(boolean[] lista,int zutabea){
        for(int i = 0;i<9;i++){
            if(sudoku[i][zutabea].getBalioa() != 0){
                lista[ sudoku[i][zutabea].getBalioa() -1] = true;
            }
        }

        return (konprobatu(lista)[1] == 1);
    }


    //----------------------------------------------------------------------------------------------------------------

    /*SOLE CANDIDATE*/
    public int[] soleCandidate(){

        laguntzaKopHanditu();

        int[] emaitza = new int[3];
        boolean[] lista = new boolean[9];
        boolean aurkitua = false;
        int i = 0;

        while(i<9 && !aurkitua){
            int j = 0;
            while(j<9 && !aurkitua){
                if(sudoku[i][j].getBalioa() == 0) {
                    if(errenkadaBegiratu(lista, i)){
                        aurkitua = true;
                    }
                    else if(zutabeaBegiratu(lista, j)){
                        aurkitua = true;
                    }
                    else if(barrukoMatrizeaBegiratu(lista, j,i)){
                        aurkitua = true;
                    }

                    if(aurkitua){
                        emaitza[0] = konprobatu(lista)[0];
                        emaitza[1] = i+1;
                        emaitza[2] = j+1;
                    }
                }
                j++;
            }
            i++;
        }

        setChanged();
        notifyObservers(Arrays.asList(Enumeratzailea.SOLE_PISTA,emaitza));

        return emaitza;
    }


    private boolean barrukoMatrizeaBegiratu(boolean[] lista,int zutabea,int errenkada){
        return barrukoMatBegiratu(lista, zutabea,errenkada);
    }


    private int[] konprobatu(boolean[] lista){
	//bi zenbaki erabiltzen ditu informazioa emateko
	//int[0] azken errorea non dagoen markatzen du
	//int[1] == 1 bada ondo dago
        int kont = 0;
        int[] emaitza = new int[2];

        for(int i = 0;i<lista.length;i++){
            if(lista[i]){
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


    //----------------------------------------------------------------------------------------------------------------

    //UNIQUE CANDIDATE

    public int[] uniqueCandidate(){
        int[] emaitza=new int[3];

        boolean aurkitua = false;

        int i = 0;
        while(i<9 && !aurkitua) {
            int j = 0;
            while (j < 9 && !aurkitua) {
                if(sudoku[i][j].getBalioa() == 0) {
                    GelaxkaEditable gE=(GelaxkaEditable)sudoku[6][7];
                    ArrayList<Integer> lista=gE.getHautagaiak();

                    int unekoa=0;

                    int errenkada=i-(i % 3);
                    int zutabea=j-(j % 3);

                    while(unekoa<lista.size() && !aurkitua){
                        if( aurkitua=begiratuAlbokoak(lista.get(unekoa),errenkada,zutabea,i,j) ){  //lista.get(unekoa),errenkada,zutabea,i,j
                            emaitza[0]=lista.get(unekoa);
                            emaitza[1]=i;
                            emaitza[2]=j;
                        }

                        unekoa++;
                    }

                }
                j++;
            }

            i++;
        }

        //emaitza aurkitu bada
        if(aurkitua){
            setChanged();
            notifyObservers(Arrays.asList(Enumeratzailea.UNIQUE_PISTA,emaitza));
        }

        return emaitza;
    }

    private boolean begiratuAlbokoak(int unekoa, int errenkada, int zutabea,int i,int j) {
        boolean zut=true;
        boolean erre=true;
        boolean indeter=true;

        boolean karratu=balioaMatrizeanBilatu(unekoa,i,j);

        int zutIndeter=0;
        int erreIndeter=0;


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

        if(!karratu){
            //3 zutabeak begiratu
            for(int a=0;a<=2;a++){
                if(j!= zutabea+a){
                    if (!balioaZutabeanBilatu(unekoa,zutabea+a)){
                        if((sudoku[i][zutabea+a] instanceof GelaxkaNotEditable)) zutIndeter++;
                        else zut=false;
//                        zut=zut && (sudoku[i][zutabea+a] instanceof GelaxkaNotEditable);
                    }
//                    if( !(sudoku[i][zutabea+a] instanceof GelaxkaNotEditable) ) zut= zut && balioaZutabeanBilatu(unekoa,zutabea+a);
//                    else zutIndeterminazio[a]=a;
                }
            }


            //aurrekoa ondo joan bada bestea begiratu bestela ezer
            if(zut){
                for(int a=0;a<=2;a++){
                    if(i!= errenkada+a){
                        if(!balioaErrenkadanBilatu(unekoa,errenkada+a)){
                            if((sudoku[errenkada+a][j] instanceof GelaxkaNotEditable)) erreIndeter++;
                            else erre=false;
                        }
//                        if( !(sudoku[errenkada+a][j] instanceof GelaxkaNotEditable) ) erre = erre && balioaErrenkadanBilatu(unekoa,errenkada+a);
//                        else erreIndeterminazio[a]=a;
                    }
                }

            }

            //indeterminazio kasua konprobatu LoL
            for (int x=0;x<zutIndeter;x++){
                for (int y=0;y<erreIndeter;y++){
                    if (sudoku[errenkada + y][zutabea + x].getBalioa() == 0) {
                        indeter = false;
                        break;
                    }
                }
            }

        }

        return (zut && erre && indeter && !karratu);

    }

    private boolean balioaMatrizeanBilatu(int balioa,int zutabea,int errenkada) {
        int errenkadaKarratua = errenkada - (errenkada % 3);
        int zutKarratua = zutabea - (zutabea % 3);

        int a=errenkadaKarratua;

        boolean berdina=false;

        while(a<errenkadaKarratua+3 && !berdina){

            int b=zutKarratua;
            while(b<zutKarratua+3 && !berdina){
                berdina = (sudoku[b][a].getBalioa()==balioa);
                b++;
            }
            a++;
        }

        return berdina;
    }
    private boolean balioaErrenkadanBilatu(int balioa, int errenkada){
        boolean emaitza = false;
        int j=0;
        while (j<9 && !emaitza){
            if(sudoku[errenkada][j].getBalioa() == balioa) emaitza = true;
            j++;
        }
        return emaitza;
    }

    private boolean balioaZutabeanBilatu(int balioa,int zutabea){
        boolean emaitza = false;
        int i=0;
        while (i<9 && !emaitza){
            if(sudoku[i][zutabea].getBalioa() == balioa) emaitza = true;
            i++;
        }
        return emaitza;
    }

//    private boolean barrukoMatrizekoBalioakBegiratu(boolean[] lista,int zutabea){
//        return barrukoMatBegiratu(lista, zutabea);
//    }
}
