package serialPort;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ComSet {

	public JFrame frmcom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ComSet window = new ComSet();
					window.frmcom.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ComSet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmcom = new JFrame();
		frmcom.setTitle("\u8BBE\u7F6ECOM\u53E3");
		frmcom.setBounds(100, 100, 765, 535);
		frmcom.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmcom.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Send/Receive on Comm.");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel.setBounds(56, 31, 303, 32);
		frmcom.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(56, 72, 303, 32);
		frmcom.getContentPane().add(comboBox);
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 128, 637, 319);
		frmcom.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Com Ports Setting");
		lblNewLabel_1.setBounds(0, 0, 292, 34);
		panel.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 24));
		
		JLabel lblNewLabel_2 = new JLabel("Baud Rate");
		lblNewLabel_2.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(44, 85, 141, 34);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Parity");
		lblNewLabel_3.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_3.setBounds(44, 141, 141, 34);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Parity error char.");
		lblNewLabel_4.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(44, 200, 141, 34);
		panel.add(lblNewLabel_4);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(201, 87, 109, 27);
		panel.add(comboBox_1);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(200, 143, 109, 27);
		panel.add(comboBox_2);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(200, 202, 109, 27);
		panel.add(comboBox_3);
		
		JLabel lblNewLabel_5 = new JLabel("Data Bits");
		lblNewLabel_5.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(381, 90, 81, 21);
		panel.add(lblNewLabel_5);
		
		JLabel lblStopBits = new JLabel("Stop Bits");
		lblStopBits.setFont(new Font("Calibri", Font.PLAIN, 20));
		lblStopBits.setBounds(381, 146, 81, 21);
		panel.add(lblStopBits);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(474, 87, 109, 27);
		panel.add(comboBox_4);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(474, 143, 109, 27);
		panel.add(comboBox_5);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnNewButton.setBounds(296, 275, 97, 29);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frmcom.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnCancel.setBounds(437, 275, 97, 29);
		panel.add(btnCancel);
	}
}
