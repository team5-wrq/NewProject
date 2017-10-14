package main;

import serialPort.*;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JFormattedTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
		frmCantoolapp.setBounds(100, 100, 753, 611);
		frmCantoolapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCantoolapp.getContentPane().setLayout(null);
		
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(15, 15, 700, 530);
		frmCantoolapp.getContentPane().add(tabbedPane_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				serialPort.ComSet window = new serialPort.ComSet();
				window.frmcom.setVisible(true);	
			}
		});
		tabbedPane_1.addTab("\u8BBE\u7F6ECOM\u53E3", null, panel_1, null);
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("\u8BBE\u7F6E\u88C5\u7F6E", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		tabbedPane_1.addTab("\u63A5\u6536\u53D1\u9001CAN\u4FE1\u606F", null, panel_3, null);
	}
}
