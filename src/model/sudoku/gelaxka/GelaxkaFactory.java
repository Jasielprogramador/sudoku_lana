package model.sudoku.gelaxka;

public class GelaxkaFactory {

    private static GelaxkaFactory instance = new GelaxkaFactory();

    public static GelaxkaFactory getInstance(){return instance;}

    private GelaxkaFactory(){
    }

    public Gelaxka sortuGelaxka(int mota){
        switch (mota){
            case 0:
                return new GelaxkaEditable(mota);
            default:
                return new GelaxkaNotEditable(mota);
        }
    }

}
