package sudokuGUI;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.gelaxka.Gelaxka;
import model.gelaxka.GelaxkaEditable;
import model.gelaxka.GelaxkaFactory;
import model.sudoku.Sudoku;
import model.sudoku.JokoMatrizea;
import model.modelutils.Enumeratzailea;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

public class SudokuGUI extends JFrame implements Observer {

	//Panel generikoak
	private JPanel contentPane;
	private JPanel informazioPanel;
	private JPanel datuakPanel;
	private JPanel hautagaiPanel;
	private JPanel balioakPanel;

	//kontrolatzailea
	private Controller controller;


	//panel eskuz sortuak
	private JPanel sudokuPanel;
	private JPanel gridBagPane=new JPanel(new GridBagLayout());

	private JLabel lblHautatutakoBalioa =new JLabel();
	private JLabel lblHautatutakoHautagaiak=new JLabel();
	private int balioZutabea;
	private int balioErrenkada;
	private boolean flag=false;


	private JLabel lblHautagai;
	private JTextField txtFieldHautagai;
	private JLabel lblBalioak;
	private JTextField txtFieldBalioa;
	private JPanel botoiPanel;
	private JPanel aldatuPanel;
	private JPanel laguntzaPanel;
	private JButton btnAldatu;
	private JButton btnLaguntza;
	private JPanel testuaPanel;
	private JTextArea textArea;

	/**
	 * Create the frame.
	 */
	public SudokuGUI(Sudoku partidakoSudoku) {
		JokoMatrizea.getInstance().addObserver(this);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getSudokuPanel(partidakoSudoku), BorderLayout.CENTER);
		contentPane.add(getInformazioPanel(), BorderLayout.EAST);


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setVisible(false);
	}

	private JPanel getSudokuPanel(Sudoku partidakoSudoku) {
		if (sudokuPanel == null) {
			sudokuPanel = new JPanel();
			sudokuPanel.setLayout(new GridLayout(3, 3));
			matrizeaSortu(partidakoSudoku);
		}
		return sudokuPanel;
	}


	private void matrizeaSortu(Sudoku partidakoSudoku) {
		for(int l=0;l<3;l++) {
			for(int z=0;z<3;z++) {
				sudokuPanel.add(getMatrizeKarratuHandiak(l, z, partidakoSudoku));
			}
		}
	}


	private JPanel getMatrizeKarratuHandiak(int lerro,int zutabe,Sudoku partidakoSudoku) {
		//JPanel  gridBagLayout
		JPanel gridBagLayoutPane = new JPanel();

		GridBagLayout gbl_gridBagLayoutPane = new GridBagLayout();
		gbl_gridBagLayoutPane.columnWidths = new int[]{0};
		gbl_gridBagLayoutPane.rowHeights = new int[] {0, 0, 0};
		gbl_gridBagLayoutPane.columnWeights = new double[]{1.0};
		gbl_gridBagLayoutPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayoutPane.setLayout(gbl_gridBagLayoutPane);
		gridBagLayoutPane.setBorder(new LineBorder(Color.BLACK,2));

		GridBagConstraints c=new GridBagConstraints();

		c.fill=GridBagConstraints.BOTH;

		//JPanel
		JPanel matrizetxoKopia=new JPanel();
		matrizetxoKopia.setLayout(new GridLayout(3,3));


		for(int l=0;l<3;l++) {
			for(int z=0;z<3;z++) {
				matrizetxoKopia.add(getZenbakienMatrizea(l+lerro*3, z+zutabe*3,partidakoSudoku));
			}
		}

		gridBagLayoutPane.add(matrizetxoKopia,c);



		return gridBagLayoutPane;
	}

	private JPanel getZenbakienMatrizea(int zut,int errenkada,Sudoku partidakoSudoku) {

		//JPanel  gridBagLayout
		JPanel gridBagLayoutPane = new JPanel();

		GridLayout gbl_gridBagLayoutPane = new GridLayout(2,2);
		gridBagLayoutPane.setLayout(gbl_gridBagLayoutPane);
		gridBagLayoutPane.setBorder(new LineBorder(Color.BLACK));


		//JLabel hautagaiak
		JLabel lblHautagaiak=new JLabel();
		lblHautagaiak.setHorizontalAlignment(SwingConstants.CENTER);
		lblHautagaiak.setVerticalAlignment(SwingConstants.CENTER);
		lblHautagaiak.setFont(new Font("Verdana",1,9));


		//JLabel balioa
		JLabel lblBalioa=new JLabel();
		lblBalioa.setHorizontalAlignment(SwingConstants.CENTER);
		lblBalioa.setVerticalAlignment(SwingConstants.CENTER);
		lblBalioa.setFont(new Font("Verdana",1,15));

		int zenbakia=partidakoSudoku.getSudokuHutsaBalioa(zut, errenkada);
		String zbk=String.valueOf(zenbakia);

		if(!zbk.equals("0")){
			lblBalioa.setForeground(Color.RED);
			lblBalioa.setText(zbk);
		}
		else {
			lblBalioa.setText("");
		}


		gridBagLayoutPane.add(lblHautagaiak);
		gridBagLayoutPane.add(lblBalioa);


		gridBagLayoutPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if(lblBalioa.getForeground()!=Color.RED){
					gridBagPane.setBorder(new LineBorder(Color.BLACK));

					gridBagLayoutPane.setBorder(new LineBorder(Color.BLUE,3));
					gridBagPane=gridBagLayoutPane;

					lblHautatutakoBalioa =lblBalioa;
					lblHautatutakoHautagaiak=lblHautagaiak;

					balioZutabea=zut;
					balioErrenkada=errenkada;

					flag=true;
				}

			}
		});

		return gridBagLayoutPane;
	}



	private JPanel getInformazioPanel() {
		if (informazioPanel == null) {
			informazioPanel = new JPanel();
			informazioPanel.setLayout(new GridLayout(3, 1, 0, 0));
			informazioPanel.add(getDatuakPanel());
			informazioPanel.add(getBotoiPanel());
			informazioPanel.add(getTestuaPanel());
		}
		return informazioPanel;
	}
	private JPanel getDatuakPanel() {
		if (datuakPanel == null) {
			datuakPanel = new JPanel();
			datuakPanel.setLayout(new GridLayout(2, 1, 0, 0));
			datuakPanel.add(getHautagaiPanel());
			datuakPanel.add(getBalioakPanel());
		}
		return datuakPanel;
	}
	private JPanel getHautagaiPanel() {
		if (hautagaiPanel == null) {
			hautagaiPanel = new JPanel();
			hautagaiPanel.add(getLblHautagai());
			hautagaiPanel.add(getTxtFieldHautagai());
		}
		return hautagaiPanel;
	}
	private JPanel getBalioakPanel() {
		if (balioakPanel == null) {
			balioakPanel = new JPanel();
			balioakPanel.add(getLblBalioak());
			balioakPanel.add(getTxtFieldBalioa());
		}
		return balioakPanel;
	}
	private JLabel getLblHautagai() {
		if (lblHautagai == null) {
			lblHautagai = new JLabel("hautagai");
		}
		return lblHautagai;
	}
	private JTextField getTxtFieldHautagai() {
		if (txtFieldHautagai == null) {
			txtFieldHautagai = new JTextField();
			txtFieldHautagai.setColumns(10);
		}
		return txtFieldHautagai;
	}
	private JLabel getLblBalioak() {
		if (lblBalioak == null) {
			lblBalioak = new JLabel("Balioa");
		}
		return lblBalioak;
	}
	private JTextField getTxtFieldBalioa() {
		if (txtFieldBalioa == null) {
			txtFieldBalioa = new JTextField();
			txtFieldBalioa.setColumns(10);
		}
		return txtFieldBalioa;
	}
	private JPanel getBotoiPanel() {
		if (botoiPanel == null) {
			botoiPanel = new JPanel();
			botoiPanel.setLayout(new GridLayout(2, 1, 0, 0));
			botoiPanel.add(getAldatuPanel());
			botoiPanel.add(getLaguntzaPanel());
		}
		return botoiPanel;
	}
	private JPanel getAldatuPanel() {
		if (aldatuPanel == null) {
			aldatuPanel = new JPanel();
			aldatuPanel.add(getBtnAldatu());
		}
		return aldatuPanel;
	}
	private JPanel getLaguntzaPanel() {
		if (laguntzaPanel == null) {
			laguntzaPanel = new JPanel();
			laguntzaPanel.add(getBtnLaguntza());
		}
		return laguntzaPanel;
	}
	private JButton getBtnAldatu() {
		if (btnAldatu == null) {
			btnAldatu = new JButton("Aldatu");
			btnAldatu.addActionListener(getController());
		}
		return btnAldatu;
	}
	private JButton getBtnLaguntza() {
		if (btnLaguntza == null) {
			btnLaguntza = new JButton("Laguntza");
			btnLaguntza.addActionListener(getController());
		}
		return btnLaguntza;
	}
	private JPanel getTestuaPanel() {
		if (testuaPanel == null) {
			testuaPanel = new JPanel();
			testuaPanel.setLayout(new BorderLayout(0, 0));
			testuaPanel.add(getTextArea());
		}
		return testuaPanel;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setLineWrap(true);
		}
		return textArea;
	}

	//1- KONTROLATZAILEA : instantzia
	private Controller getController() {
		if (controller == null) {
			controller = new Controller();
		}
		return controller;
	}

	@Override
	public void update(Observable o, Object arg) {

		//HONI DEITZEN ZAION BAKOITZEAN ENUM KLASEKO BALIOA JARRIKO ZAIO LISTAKO LEHEN POSIZIOAN
		List lista=(List) arg;

		Enumeratzailea enumeratzailea= (Enumeratzailea) lista.get(0);

		switch (enumeratzailea){
			case SOLE_PISTA:
				int [] emaitza1= (int[]) lista.get(1);
				textArea.append("Sole candidate estrategia: \n" +
						"Gelaxka (↓"+emaitza1[1]+", →"+emaitza1[2]+") \n" +
						"Balioa "+emaitza1[0]);

				break;

			case UNIQUE_PISTA:
				int [] emaitza2= (int[]) lista.get(1);
				textArea.append("Unique candidate estrategia: \n" +
						"Gelaxka (↓"+emaitza2[1]+", →"+emaitza2[2]+") \n" +
						"Balioa "+emaitza2[0]);

			case BUKATUTA:
				System.out.println("-------------");
				System.out.println("bokadillo txorizo mesi: "+ JokoMatrizea.getInstance().puntuazioaKalkulatu());
				System.exit(0);
				break;
		}


		//TODO: bukaerarako erabiltzaileak sartutako erroreak zuzenean zuzendu daitezke(kolore batez markatu,...)


	}


	private class Controller implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			JButton btn = (JButton) arg0.getSource();

			if (btn.equals(btnAldatu)) {

				if (!flag) {
					JOptionPane.showMessageDialog(null, "Mesedez gelaxka bat sakatu");
				} else {
					try {
						int zbk;
						if (txtFieldBalioa.getText().equals("")) {
							zbk = 0;
						} else {
							zbk = Integer.parseInt(txtFieldBalioa.getText());
						}

						if (!txtFieldHautagai.getText().equals("") && txtFieldBalioa.getText().equals("")){
							GelaxkaEditable g = (GelaxkaEditable)JokoMatrizea.getInstance().lortuGelaxka(balioErrenkada,balioZutabea);

							List<String> lista= Arrays.asList(txtFieldHautagai.getText().split(" "));

							ArrayList<Integer> listaInt = new ArrayList<>(lista.size());
							for(String s:lista){
								listaInt.add(Integer.parseInt(s));
							}

							g.setHautagaiak(listaInt);

							String a = "";

							for(int i = 0;i<listaInt.size();i++){
								a = listaInt.get(i)+" ";
							}

							lblHautatutakoHautagaiak.setText(a);

						} else if (zbk >= 1 && zbk <= 9) {
							textArea.setText("");
							lblHautatutakoBalioa.setText(txtFieldBalioa.getText());
							lblHautatutakoHautagaiak.setText(txtFieldHautagai.getText());

							JokoMatrizea.getInstance().setJokalariarenBalioa(balioErrenkada, balioZutabea, txtFieldBalioa.getText());

							//konprobatu bukatu duen
							boolean bool = JokoMatrizea.getInstance().emaitzaEgiaztatu();
							if (bool) {
								System.out.println("kaixo");
							}

							//konprobatu sartutako balioa ondo dagoen
							//JokoMatrizea.getInstance().ondoDago(balioErrenkada, balioZutabea, zbk);

						} else {
							JOptionPane.showMessageDialog(null, "Bakarrik 1 eta 9 arteko zenbakiak jarri ditzazkezu");
						}


					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Zenbaki bat sartu mesedez");
					}

					txtFieldBalioa.setText("");
					txtFieldHautagai.setText("");
				}

			}
			else if(btn.equals(btnLaguntza)){
				textArea.setText("");

				//Sole candidate
				JokoMatrizea.getInstance().soleCandidate();

				//Unique candidate
				JokoMatrizea.getInstance().uniqueCandidate();

			}
		}
	}
}