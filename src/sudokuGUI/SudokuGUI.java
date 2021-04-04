package sudokuGUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.Gelaxka;
import model.Sudoku;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class SudokuGUI extends JFrame {

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

	private JLabel lblHautatutakoKasilla=new JLabel();
	private int balioZutabea;
	private int balioErrenkada;
	private boolean flag=false;
	private boolean sakatua = false;


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
	public SudokuGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		contentPane.add(getSudokuPanel(), BorderLayout.CENTER);
		contentPane.add(getInformazioPanel(), BorderLayout.EAST);
		setVisible(false);
	}

	private JPanel getSudokuPanel() {
		if (sudokuPanel == null) {
			sudokuPanel = new JPanel();
			sudokuPanel.setLayout(new GridLayout(3, 3));
			matrizeaSortu();
		}
		return sudokuPanel;
	}


	private void matrizeaSortu() {
		for(int l=0;l<3;l++) {
			for(int z=0;z<3;z++) {
				sudokuPanel.add(getMatrizeKarratuHandiak(l, z));
			}
		}
	}


	private JPanel getMatrizeKarratuHandiak(int lerro,int zutabe) {
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
				matrizetxoKopia.add(getZenbakienMatrizea(l+lerro*3, z+zutabe*3));
			}
		}

		gridBagLayoutPane.add(matrizetxoKopia,c);



		return gridBagLayoutPane;
	}

	private JPanel getZenbakienMatrizea(int zut,int errenkada) {

		//JPanel  gridBagLayout
		JPanel gridBagLayoutPane = new JPanel();

		GridLayout gbl_gridBagLayoutPane = new GridLayout(2,2);
		gridBagLayoutPane.setLayout(gbl_gridBagLayoutPane);
		gridBagLayoutPane.setBorder(new LineBorder(Color.BLACK));



		//JTextField
		JLabel lblBalioa=new JLabel();
		String zenbakia=Sudoku.getInstance().getMatrizeaBalioa(errenkada, zut);
		if(!zenbakia.equals("0")){
			lblBalioa.setForeground(Color.RED);
			lblBalioa.setText(zenbakia);
		}
		else lblBalioa.setText("");

		gridBagLayoutPane.add(lblBalioa);



		gridBagLayoutPane.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {

				if(lblBalioa.getForeground()!=Color.RED){
					sakatua = true;
					gridBagPane.setBorder(new LineBorder(Color.BLACK));

					gridBagLayoutPane.setBorder(new LineBorder(Color.BLUE,3));
					gridBagPane=gridBagLayoutPane;

					lblHautatutakoKasilla=lblBalioa;
					flag=true;

					balioZutabea=zut;
					balioErrenkada=errenkada;



					ArrayList<Integer> hautagaiak = Sudoku.getInstance().getGelaxka(balioErrenkada, balioZutabea).getBalioPosibleak();

					String s = "";

					for (int i = 0;i<hautagaiak.size();i++){
						s = s + " " + hautagaiak.get(i).toString();

					}

					txtFieldHautagai.setText(s);

					for (int i = 0;i<hautagaiak.size();i++){
						hautagaiak.remove(i);
					}
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


	private class Controller implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {

			JButton btn = (JButton) arg0.getSource();

			if (btn.equals(btnAldatu)) {
				if (!sakatua) {
					JOptionPane.showMessageDialog(null, "Mesedez gelaxka bat sakatu ezazu");
				} else {
					try {
						ArrayList<Integer> h = new ArrayList<Integer>();
						String[] s = txtFieldHautagai.getText().split(" ");

						for (int i = 0; i < s.length; i++) {
							if (!s[i].equals("")) {
								h.add(Integer.parseInt(s[i]));
							}
						}

						int zbk = Integer.parseInt(txtFieldBalioa.getText());

						if (txtFieldBalioa.getText() != "") {
							if (zbk >= 1 && zbk <= 9) {
								lblHautatutakoKasilla.setText(txtFieldBalioa.getText());
								Sudoku.getInstance().setJokalariarenBalioa(balioZutabea, balioErrenkada, txtFieldBalioa.getText());
							} else
								JOptionPane.showMessageDialog(null, "Bakarrik 1 eta 9 arteko zenbakiak jarri ditzakezu");
						}

						if (txtFieldHautagai.getText() != "") {
							Sudoku.getInstance().getGelaxka(balioErrenkada, balioZutabea).setBalioPosibleak(h);
						}

					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Zenbaki bat sartu mesedez");
						txtFieldBalioa.setText("");
					}
				}
			}

			else if(btn.equals(btnLaguntza)){
				Sudoku.getInstance().laguntzaKopHanditu();
				//TODO

			}
		}
	}
}
