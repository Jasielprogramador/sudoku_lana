package model.gelaxka;

public class GelaxkaFactory {
    //TODO: this might be an interface hummmm

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
