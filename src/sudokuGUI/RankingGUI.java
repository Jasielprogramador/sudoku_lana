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


public class RankingGUI extends JFrame {

    private JPanel contentPane;

    public RankingGUI() throws FileNotFoundException {

        puntuazioaIdatzi();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        ArrayList<String> lista = Reader.getInstance().irakurriRanking();

        String[][] data = new String[50][50];


        for(int i = 0; i<lista.size();i++){
            String[] berria = lista.get(i).split("-");
            data[i] = berria;
        }

        String column[]={"Izena","Puntuazioa"};

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(4, 1, 0, 0));

        JTable jt=new JTable(data,column);
        jt.setBounds(30,40,200,300);
        JScrollPane sp=new JScrollPane(jt);

        contentPane.add(sp);
        contentPane.setSize(300,400);
        setVisible(true);

    }

    public void puntuazioaIdatzi(){
        double puntuazioa = JokoMatrizea.getInstance().puntuazioaKalkulatu();
        String izena = Session.getInstantzia().getIzena();

        try {
            FileWriter myWriter = new FileWriter("ranking.txt",true);
            myWriter.write(izena+"-"+puntuazioa);
            myWriter.write("\n");//appends the string to the file
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}

