package main;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class Main {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("CANTOOLAPP");
		frame.setBounds(100, 100, 753, 611);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane_1.setBounds(15, 15, 700, 530);
		tabbedPane_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				serialPort.ComSet window = new serialPort.ComSet();
				window.frame.setVisible(true);	
			}
		});
		
		frame.getContentPane().add(tabbedPane_1);
	
		JPanel panel_1 = new JPanel();
		/*panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				serialPort.ComSet window = new serialPort.ComSet();
				window.frmcom.setVisible(true);	
			}
		});
	    */
		tabbedPane_1.addTab("\u8BBE\u7F6ECOM\u53E3", null,panel_1, null);
		
		
		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("\u8BBE\u7F6E\u88C5\u7F6E", null, panel_2, null);
		
		JPanel panel_3 = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel_3.getLayout();
		tabbedPane_1.addTab("\u63A5\u6536\u53D1\u9001CAN\u4FE1\u606F", null, panel_3, null);
	}

}
