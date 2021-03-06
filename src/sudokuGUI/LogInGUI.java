package sudokuGUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Gelaxka;
import model.Sudoku;
import model.modelutils.Reader;
import model.modelutils.Timerra;

import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class LogInGUI extends JFrame implements Observer{

	private JPanel contentPane;
	private JPanel izenaPane;
	private JLabel lblIzena;
	private JTextField txtIzena;
	private JPanel paneHutsa;
	private JPanel zailtasunaPane;
	private JLabel lblZailtasuna;
	private JTextField textField;
	private JPanel panel;
	private JButton btnNewButton;

	//controller
	private Controller controller;

	/**
	 * Create the frame.
	 */
	public LogInGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		contentPane.add(getPaneHutsa());
		contentPane.add(getIzenaPane());
		contentPane.add(getZailtasunaPane());
		contentPane.add(getPanel());
		setVisible(true);
	}
	private JPanel getIzenaPane() {
		if (izenaPane == null) {
			izenaPane = new JPanel();
			izenaPane.add(getLblIzena());
			izenaPane.add(getTxtIzena());
		}
		return izenaPane;
	}
	private JLabel getLblIzena() {
		if (lblIzena == null) {
			lblIzena = new JLabel("Izena:");
		}
		return lblIzena;
	}
	private JTextField getTxtIzena() {
		if (txtIzena == null) {
			txtIzena = new JTextField();
			txtIzena.setColumns(10);
		}
		return txtIzena;
	}
	private JPanel getPaneHutsa() {
		if (paneHutsa == null) {
			paneHutsa = new JPanel();
		}
		return paneHutsa;
	}
	private JPanel getZailtasunaPane() {
		if (zailtasunaPane == null) {
			zailtasunaPane = new JPanel();
			zailtasunaPane.add(getLblZailtasuna());
			zailtasunaPane.add(getTextField());
		}
		return zailtasunaPane;
	}
	private JLabel getLblZailtasuna() {
		if (lblZailtasuna == null) {
			lblZailtasuna = new JLabel("Zailtasuna:");
		}
		return lblZailtasuna;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setColumns(10);
		}
		return textField;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setLayout(new GridLayout(0, 3, 0, 0));
			//panel.add(getPaneHutsa());
			panel.add(getBtnNewButton());
		}
		return panel;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Sartu");
			btnNewButton.addActionListener(
				getController()
			);
		}
		return btnNewButton;
	}


	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub

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
			try {
				int zailtasuna = Integer.parseInt(textField.getText());
				if (zailtasuna > 3 || zailtasuna < 1) {
					JOptionPane.showMessageDialog(null, "Mesedez 1 eta 3 artean dagoen zbk bat sartu");
					textField.setText("");
				} else {
					Gelaxka[][] partidakoSudoku = Sudoku.getInstance().getSudokuBat(textField.getText());
					Sudoku.getInstance().setMatrizea(partidakoSudoku);
					Sudoku.getInstance().setMaila(Integer.parseInt(textField.getText()));
					double start = Timerra.getInstance().timerraHasi();
					Timerra.getInstance().setStart(start);
					SudokuGUI sud = new SudokuGUI();
					sud.setVisible(true);
					setVisible(false);
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Zenbaki bat sartu");
				textField.setText("");
			}
		}
	}

}
