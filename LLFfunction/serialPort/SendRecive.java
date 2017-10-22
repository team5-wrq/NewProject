package serialPort;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class SendRecive extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SendRecive frame = new SendRecive();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SendRecive() {
		setTitle("Recive and Send Message");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 761, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Recive Message");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewLabel.setBounds(56, 27, 175, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Send Message");
		lblNewLabel_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(56, 227, 137, 21);
		contentPane.add(lblNewLabel_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Calibri", Font.PLAIN, 22));
		textArea.setBounds(56, 61, 625, 152);
		contentPane.add(textArea);
		textArea.setLineWrap(true);
		
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setFont(new Font("Calibri", Font.PLAIN, 22));
		textArea_1.setBounds(56, 263, 625, 141);
		contentPane.add(textArea_1);
		textArea_1.setLineWrap(true);
		
		JButton btnNewButton = new JButton("Send");
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 22));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(483, 419, 123, 29);
		contentPane.add(btnNewButton);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(649, 122, 26, 48);
		contentPane.add(scrollBar);
	}
}
