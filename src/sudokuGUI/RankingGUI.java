package sudokuGUI;

import model.Session;
import model.modelutils.Reader;
import model.sudoku.JokoMatrizea;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Stream;


public class RankingGUI extends JFrame {

    private JPanel contentPane;
    private ArrayList<String> lista = Reader.getInstance().irakurriRanking();

    public RankingGUI() throws FileNotFoundException {

        puntuazioaIdatzi();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        String[][] data = new String[50][50];
        
        for(int i = 0; i<lista.size();i++){
            String[] berria = lista.get(i).split("-");
            data[i] = berria;
        }

        String column[]={"Maila","Puntuazioa","Izena"};

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(6, 3, 0, 0));

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);

        contentPane.add(sp);
        contentPane.setSize(300,400);
        setVisible(true);

    }
    
    private void puntuazioaGehitu(int maila, double puntuazioa, String izena) {
    	lista.add(new String(maila+"-"+puntuazioa+"-"+izena));
    }
    
    private void puntuazioaOrdenatu() {
    	lista.sort((e1, e2) -> e2.compareTo(e1));
    }

    public void puntuazioaIdatzi(){
        double puntuazioa = JokoMatrizea.getInstance().puntuazioaKalkulatu();
        String izena = Session.getInstantzia().getIzena();
        int maila = Session.getInstantzia().getMaila();
        
        puntuazioaGehitu(maila, puntuazioa, izena);
        
        puntuazioaOrdenatu();
        System.out.println(lista);
        
        Reader.getInstance().idatzi(lista);
    }
}

