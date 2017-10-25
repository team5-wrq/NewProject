package serialPort;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SendDataToSerialPortFailure;
import serialException.SerialPortOutputStreamCloseFailure;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;

public class ComSet {

	public JFrame frame;

	public ComSet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	SerialTool tool = new SerialTool();
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("\u8BBE\u7F6ECOM\u53E3");
		frame.setBounds(100, 100, 765, 535);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COM");
		lblNewLabel.setFont(new Font("Calibri", Font.PLAIN, 24));
		lblNewLabel.setBounds(56, 31, 303, 32);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(56, 72, 303, 32);
		frame.getContentPane().add(comboBox);
		Enumeration<CommPortIdentifier> portList=tool.findPort();
		ArrayList<String> portNameList = new ArrayList<>();

        //将可用串口名添加到List并返回该List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
            comboBox.addItem(portName);
        }
		
		JPanel panel = new JPanel();
		panel.setBounds(56, 128, 637, 319);
		frame.getContentPane().add(panel);
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
		comboBox_1.addItem("9600");
		comboBox_1.addItem("1500");
		comboBox_1.addItem("2400");
		comboBox_1.addItem("4800");
	//  comboBox_1.addItem("9600");
		comboBox_1.addItem("14400");
		comboBox_1.addItem("19500");
		comboBox_1.addItem("115500");
	
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(200, 143, 109, 27);
		panel.add(comboBox_2);
		comboBox_2.addItem("NONE");
		comboBox_2.addItem("ODD");
		comboBox_2.addItem("EVEN");
		
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
		comboBox_4.addItem(8);
		comboBox_4.addItem(7);
		comboBox_4.addItem(6);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setBounds(474, 143, 109, 27);
		panel.add(comboBox_5);
		comboBox_5.addItem(1);
		comboBox_5.addItem(2);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					SerialPort serialPort = tool.openPort("COM2", 9600);
					//SerialPortEventListener listener=new SerialPortEventListener();
					try {
						
						tool.addListener(serialPort, new SerialListener(tool,serialPort));
						byte [] k={1,1,1,1,1,1,};
						try {
							tool.sendToPort(serialPort, k);
						} catch (SendDataToSerialPortFailure e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (SerialPortOutputStreamCloseFailure e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} catch (TooManyListeners e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				} catch (SerialPortParameterFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotASerialPort e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NoSuchPort e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (PortInUse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//SendRecive window = new SendRecive();
				//window.setVisible(true);
				//frmcom.setVisible(false);
				
			}
		});
		
		btnNewButton.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnNewButton.setBounds(296, 275, 97, 29);
		panel.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setVisible(false);
			}
		});
		btnCancel.setFont(new Font("Calibri", Font.PLAIN, 20));
		btnCancel.setBounds(437, 275, 97, 29);
		panel.add(btnCancel);
	}

}
