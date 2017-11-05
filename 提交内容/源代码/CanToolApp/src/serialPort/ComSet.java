package serialPort;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;

import org.omg.CORBA.PUBLIC_MEMBER;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import serialException.NoSuchPort;
import serialException.NotASerialPort;
import serialException.PortInUse;
import serialException.SerialPortParameterFailure;
import serialException.TooManyListeners;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ComSet extends JFrame {
	
	private JPanel contentPane;
	public JComboBox comboBox;
	public SerialTool tool = new SerialTool();
	public static String strPort;
	public SerialPort serialPort;
	/**
	 * Create the frame.
	 */
	public ComSet() {
		setTitle("ComSet");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 441, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("COM:");
		lblNewLabel.setBounds(64, 43, 81, 21);
		contentPane.add(lblNewLabel);
		
		comboBox = new JComboBox();
		comboBox.setBounds(186, 40, 151, 27);
		contentPane.add(comboBox);
		Enumeration<CommPortIdentifier> portList=tool.findPort();
		ArrayList<String> portNameList = new ArrayList<>();

        //�����ô�������ӵ�List�����ظ�List
        while (portList.hasMoreElements()) {
            String portName = portList.nextElement().getName();
            portNameList.add(portName);
            comboBox.addItem(portName);
        }
		
		
		JLabel lblNewLabel_1 = new JLabel("Baud Rate:");
		lblNewLabel_1.setBounds(64, 90, 105, 21);
		contentPane.add(lblNewLabel_1);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(186, 87, 151, 27);
		contentPane.add(comboBox_1);
		comboBox_1.addItem("9600");
		comboBox_1.addItem("1500");
		comboBox_1.addItem("2400");
		comboBox_1.addItem("4800");
	//  comboBox_1.addItem("9600");
		comboBox_1.addItem("14400");
		comboBox_1.addItem("19500");
		comboBox_1.addItem("115200");
		comboBox_1.setSelectedIndex(6);
		JLabel lblNewLabel_2 = new JLabel("Data Bits:");
		lblNewLabel_2.setBounds(64, 136, 105, 21);
		contentPane.add(lblNewLabel_2);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setBounds(186, 133, 151, 27);
		contentPane.add(comboBox_2);
		comboBox_2.addItem(8);
		comboBox_2.addItem(7);
		comboBox_2.addItem(6);
		
		JLabel lblNewLabel_3 = new JLabel("Parity:");
		lblNewLabel_3.setBounds(64, 178, 81, 21);
		contentPane.add(lblNewLabel_3);
		
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setBounds(186, 178, 151, 27);
		contentPane.add(comboBox_3);
		comboBox_3.addItem("NONE");
		comboBox_3.addItem("ODD");
		comboBox_3.addItem("EVEN");
		
		JLabel lblNewLabel_4 = new JLabel("Stop Bits:");
		lblNewLabel_4.setBounds(64, 224, 105, 21);
		contentPane.add(lblNewLabel_4);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setBounds(186, 220, 151, 27);
		contentPane.add(comboBox_4);
		comboBox_4.addItem(1);
		comboBox_4.addItem(2);
		
		JButton btnNewButton = new JButton("OK");
		btnNewButton.setBounds(65, 282, 123, 29);
		contentPane.add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				//ѡ���Ĵ��ڵ�����
				strPort = comboBox.getSelectedItem().toString();
				//�õ��򿪵Ĵ���
				//SerialPort serialPort = null;
				try {
					
					serialPort = tool.openPort(strPort, 115200);
				} catch (SerialPortParameterFailure | NotASerialPort | NoSuchPort | PortInUse e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					Main.serialListener=new SerialListener(tool,serialPort);
					tool.addListener(serialPort, Main.serialListener);
				} catch (TooManyListeners e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				setVisible(false);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setBounds(203, 282, 123, 29);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setVisible(false);	
			}
		});
	}
	
	public SerialPort getSerialPort(){
		return serialPort;
	}
}
