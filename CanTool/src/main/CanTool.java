package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;

public class CanTool {

	private JFrame frmCantoolapp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CanTool window = new CanTool();
					window.frmCantoolapp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CanTool() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmCantoolapp = new JFrame();
		frmCantoolapp.setTitle("CANToolAPP");
		frmCantoolapp.setBounds(100, 100, 755, 597);
		frmCantoolapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCantoolapp.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(14, 13, 709, 537);
		frmCantoolapp.getContentPane().add(tabbedPane_1);
		
		JPanel panel_1 = new JPanel();
		tabbedPane_1.addTab("\u8BBE\u7F6ECOM\u53E3", null, panel_1, null);
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("\u8BBE\u7F6E\u88C5\u7F6E", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("\u63A5\u6536CAN\u4FE1\u606F", null, panel_3, null);
		
		JPanel panel_4 = new JPanel();
		tabbedPane_1.addTab("\u53D1\u9001CAN\u4FE1\u606F", null, panel_4, null);
	}
}
