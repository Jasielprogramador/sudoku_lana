package sudokuGUI;

import model.ranking.Erabiltzaile;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.List;


public class RankingGUI extends JFrame {

    private final JPanel contentPane;

    public RankingGUI(List<Erabiltzaile> ordenatuta) throws FileNotFoundException {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 300);

        String[][] data = new String[50][50];

        int i=0;
        for (Erabiltzaile x:ordenatuta){
            String[] unekoa=new String[3];

            unekoa[0]= String.valueOf(x.getMaila());
            unekoa[1]= String.valueOf(x.getPuntuazioa());
            unekoa[2]=x.getIzena();

            data[i]=unekoa;
            i++;

        }


        String[] column ={"Maila","Puntuazioa","Izena"};

        contentPane = new JPanel();
        setContentPane(contentPane);

        JTable jt=new JTable(data,column);
        JScrollPane sp=new JScrollPane(jt);
        sp.setBounds(0,0,500,250);

        contentPane.add(sp,BorderLayout.CENTER);
        contentPane.setSize(300,200);
        setVisible(true);

    }

}

