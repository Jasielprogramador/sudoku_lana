package sudokuGUI;

import model.modelutils.Reader;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class RankingGUI extends JFrame {

    private JPanel contentPane;

    private String[][] lortuRanking() throws FileNotFoundException {
        ArrayList<String> lista = Reader.getInstance().irakurriRanking();

        String data[][] = new String[50][50];

        for(int i = 0; i<lista.size();i++){
            String[] berria = lista.get(i).split("-");
            data[0][i] = berria[0]+berria[1];
        }
        return data;
    }

    public RankingGUI() throws FileNotFoundException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);

        String data[][]=lortuRanking();
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
}