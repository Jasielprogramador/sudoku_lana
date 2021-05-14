package model.ranking;

import model.modelutils.Reader;

import java.time.chrono.Era;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;

public class Session {

    private String erabIzena;

    private List<Erabiltzaile> listaErab=new ArrayList<>();

    private static Session instantzia = new Session();



    public static Session getInstantzia(){return instantzia;}

    private Session(){
        Reader.getInstance().irakurri();
        listaErab=Reader.getInstance().irakurriRanking();
    }

    public int getMaila(int zbk) {
        return listaErab.get(zbk).getMaila();
    }

    public String getIzena(int zbk){
        return listaErab.get(zbk).getIzena();
    }

    public double getPuntuazioa(int zbk){
        return listaErab.get(zbk).getPuntuazioa();
    }

    public void sartuUnekoa(Erabiltzaile erabiltzaile){
        listaErab.add(0,erabiltzaile);
    }

    public void setErabIzena(String izena){
        erabIzena=izena;
    }

    public void setPuntuazioa(double punt){
        listaErab.get(0).setPuntuazioa(punt);
    }

    public void idatziUnekoa() {
        Reader.getInstance().idatziRanking(toStringLehena());
    }

    private String toStringLehena(){
        return getMaila(0)+"-"+getPuntuazioa(0)+"-"+getIzena(0);
    }



    //listaErab Ordenatu eta halakoak

    public List<Erabiltzaile> ordenatu(){
        return listaErab.stream()
                .sorted(comparing(Erabiltzaile::getPuntuazioa).reversed())
                .collect(Collectors.toList());
    }


}