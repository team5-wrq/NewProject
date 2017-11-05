package main;

import java.awt.EventQueue;

import serialPort.ComSet;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.border.LineBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.csvreader.CsvWriter;
import com.google.gson.Gson;

import gnu.io.SerialPort;
import serialException.SendDataToSerialPortFailure;
import serialException.SerialPortOutputStreamCloseFailure;
import serialException.TooManyListeners;
import serialPort.ComSet;
import serialPort.SerialListener;
import serialPort.SerialTool;
import tool.BinaryToHex;

import javax.swing.JTree;
import javax.swing.JScrollPane;

import dataprocess.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Main {

	public static JFrame frmCantoolapp;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	public JLabel lblNewLabel_2;
	public JLabel lblNewLabel_3;
	public JLabel lblNewLabel_4;
	public JLabel lblNewLabel_5;
	public JLabel lblNewLabel_6;
	public JLabel lblNewLabel_7;
	public JLabel lblNewLabel_8;
	public JLabel lblNewLabel_9;
	public JRadioButton rdbtnNewRadioButton_2;
	public JButton btnOpen;
	public JButton btnClose;
	public JButton btnInformation;
	public JButton btnSetup;
	public static String textStr="";
	
	
	public JComboBox comboBox;
	public static JTextArea textArea;
	public static JScrollPane scrollPane;
	public static JTree tree;
	public static JPanel panel;
	public static ArrayList<Result> resultList;
	public static SerialListener serialListener;
	public static ArrayList<CANMessage> messageList;
	public static ArrayList<CANSignal> signalList;
	public static File csvfile;
	public static String csvString;
	//public static Object panel;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//csvfile=new File("G://Java/Test/CANCSV.csv");
					csvString="CANCSV.csv";
					
		          
					Main window = new Main();
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
	public Main() {
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 * @return 
	 */

	
	public SerialTool tool;
	public ComSet comSet;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
 	private void initialize(){
 		
 		resultList = new ArrayList<Result>();
 		
 		//程序一运行，就去转化database让它成为CANMessageList和CANSignalList
 		messageList = new ArrayList<CANMessage>();
 		signalList = new ArrayList<CANSignal>();
 		File file=new File("src/database.txt");	
 		Database database = new Database(messageList,signalList,file);
 		database.toDatabase();
 		messageList = database.getMessageList();
 		signalList = database.getSignalList();
 		
 		//每次运行只有一个ResultArray存要显示在树里的结果
 		resultList = new ArrayList<Result>();
		
		
		frmCantoolapp = new JFrame();
		frmCantoolapp.setTitle("CanToolApp");
		frmCantoolapp.setBounds(100, 100, 1030, 721);
		frmCantoolapp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmCantoolapp.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("ComSet");
		btnNewButton.setBounds(269, 92, 123, 29);
		frmCantoolapp.getContentPane().add(btnNewButton);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comSet =new ComSet();
				//serialPort.ComSet window = new serialPort.ComSet();
				comSet.setVisible(true);	
				
				//byte[] bytes=serialListener.dataReceive();
				//System.out.println(bytes);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Receive Message");
		lblNewLabel.setBounds(55, 162, 140, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel);
		SerialListener serialListener = null;
		//byte[] dataReceive = serialListener.dataReceive();
		//textArea.setText(serialListener.data.toString());	
		
		JLabel lblNewLabel_1 = new JLabel("Send Message");
		lblNewLabel_1.setBounds(55, 372, 129, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_1);
		/*
		JRadioButton rdbtnNewRadioButton = new JRadioButton("856");
		rdbtnNewRadioButton.setBounds(55, 404, 75, 29);
		frmCantoolapp.getContentPane().add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("61");
		rdbtnNewRadioButton_1.setBounds(181, 404, 81, 29);
		frmCantoolapp.getContentPane().add(rdbtnNewRadioButton_1);
		
		rdbtnNewRadioButton_2 = new JRadioButton("1067");
		rdbtnNewRadioButton_2.setBounds(325, 404, 99, 29);
		frmCantoolapp.getContentPane().add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("1056");
		rdbtnNewRadioButton_3.setBounds(483, 404, 99, 29);
		frmCantoolapp.getContentPane().add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("800");
		rdbtnNewRadioButton_4.setBounds(656, 404, 81, 29);
		frmCantoolapp.getContentPane().add(rdbtnNewRadioButton_4);
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtnNewRadioButton);
		group.add(rdbtnNewRadioButton_1);
		group.add(rdbtnNewRadioButton_2);
		group.add(rdbtnNewRadioButton_3);
		group.add(rdbtnNewRadioButton_4);
		
		lblNewLabel_2 = new JLabel("CDU_NMDestAddress:");
		lblNewLabel_2.setBounds(55, 447, 169, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_2);
		
		textField = new JTextField();
		textField.setBounds(239, 444, 203, 27);
		frmCantoolapp.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_3 = new JLabel("CDU_NMAlive:");
		lblNewLabel_3.setBounds(493, 444, 150, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_3);
		
		textField_1 = new JTextField();
		textField_1.setBounds(666, 441, 226, 27);
		frmCantoolapp.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		lblNewLabel_4 = new JLabel("CDU_NMRing:");
		lblNewLabel_4.setBounds(55, 489, 169, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_4);
		
		textField_2 = new JTextField();
		textField_2.setBounds(239, 486, 203, 27);
		frmCantoolapp.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		lblNewLabel_5 = new JLabel("CDU_NMLimpHome:");
		lblNewLabel_5.setBounds(493, 489, 150, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_5);
		
		textField_3 = new JTextField();
		textField_3.setBounds(665, 483, 227, 27);
		frmCantoolapp.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		lblNewLabel_6 = new JLabel("CDU_NMSleepInd:");
		lblNewLabel_6.setBounds(55, 531, 169, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_6);
		
		textField_4 = new JTextField();
		textField_4.setBounds(239, 528, 203, 27);
		frmCantoolapp.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		lblNewLabel_7 = new JLabel("CDU_NMSleepAck:");
		lblNewLabel_7.setBounds(493, 531, 150, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_7);
		
		textField_5 = new JTextField();
		textField_5.setBounds(666, 525, 226, 27);
		frmCantoolapp.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		lblNewLabel_8 = new JLabel("CDU_NMWakeupOrignin:");
		lblNewLabel_8.setBounds(55, 571, 185, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_8);
		
		textField_6 = new JTextField();
		textField_6.setBounds(238, 570, 204, 27);
		frmCantoolapp.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		lblNewLabel_9 = new JLabel("CDU_NMDataField:");
		lblNewLabel_9.setBounds(493, 567, 150, 21);
		frmCantoolapp.getContentPane().add(lblNewLabel_9);
		
		textField_7 = new JTextField();
		textField_7.setBounds(664, 567, 228, 27);
		frmCantoolapp.getContentPane().add(textField_7);
		textField_7.setColumns(10);*/
		/*
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.setBounds(644, 609, 123, 29);
		frmCantoolapp.getContentPane().add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				List<String> binaryList=new ArrayList();
				List<CANSignal> thelist=new ArrayList();
				List<String> strlist=new ArrayList<String>();
				double[] doublex=new double[8];
				int id=Integer.parseInt(rdbtnNewRadioButton_2.getText());
				
				strlist.add(lblNewLabel_2.getText());
				strlist.add(lblNewLabel_3.getText());
				strlist.add(lblNewLabel_4.getText());
				strlist.add(lblNewLabel_5.getText());
				strlist.add(lblNewLabel_6.getText());
				strlist.add(lblNewLabel_7.getText());
				strlist.add(lblNewLabel_8.getText());
				strlist.add(lblNewLabel_9.getText());
				
				for(int i=0;i<signalList.size();i++){
					if(signalList.get(i).MessageId==id){
						//for(int j=0;j<strlist.size();j++){
						//	if(signalList.get(i).SignalName==strlist.get(j)){
						//		thelist.add(signalList.get(i));
						//	}
						thelist.add(signalList.get(i));
					}
					
					}
				
				
				double x1=Double.valueOf(textField.getText()).doubleValue();
				double x2=Double.valueOf(textField_1.getText()).doubleValue();
				double x3=Double.valueOf(textField_2.getText()).doubleValue();
				double x4=Double.valueOf(textField_3.getText()).doubleValue();
				double x5=Double.valueOf(textField_4.getText()).doubleValue();
				double x6=Double.valueOf(textField_5.getText()).doubleValue();
				double x7=Double.valueOf(textField_6.getText()).doubleValue();
				double x8=Double.valueOf(textField_7.getText()).doubleValue();
				
				doublex[0]=x1;
				doublex[1]=x2;
				doublex[2]=x3;
				doublex[3]=x4;
				doublex[4]=x5;
				doublex[5]=x6;
				doublex[6]=x7;
				doublex[7]=x8;
				
				for(int i=0;i<doublex.length;i++){
					int x=(int) ((doublex[i]-thelist.get(i).getB())/thelist.get(i).getA());
					String bin=Integer.toBinaryString(x);
					binaryList.add(bin);
				}
				
				//逻辑好像还有点问题
				char[] binaryChar=new char[64];
				for(int i=0;i<64;i++){
					System.out.println("i:"+i);
					for(int j=0;j<thelist.size();j++){
						System.out.println("j:"+j);
						if(i==thelist.get(j).getStartPos()){
							System.out.println("j:"+j);
							System.out.println(binaryList.get(j));
							char[] thischar=binaryList.get(j).toCharArray();
							System.out.println(thischar.length);
							for(int m=0;m<thelist.get(j).getBitLength();m++){
								if(m<thischar.length){
									System.out.println("m:"+m);
									binaryChar[i+m]=thischar[m];
									System.out.println("thischar:"+thischar[m]);
									System.out.println("binaryChar:"+binaryChar[i+m]);
									System.out.println(binaryChar);
								}else{
									//System.out.println("m:"+m);
									//binaryChar[i+m-1]='0';
									//System.out.println(binaryChar);
									continue;
								}
								//System.out.println(signalList.get(j).getBitLength());
								
							}
						}else{
							continue;
						}
					}
				}
				
				for(int i=0;i<64;i++){
					if(binaryChar[i]!='1' && binaryChar[i]!='0'){
						binaryChar[i]='0';
					}
				}
				
				//再把binarychar转化为16进制的
				BinaryToHex b2h=new BinaryToHex(binaryChar);
				char[] hexchar=b2h.binary2Hex();
				String datastr=String.valueOf(hexchar);
				String l=String.valueOf(hexchar.length/2);
				String hexid=Integer.toHexString(id);
				String messagestr='t'+hexid+l+datastr;
				
				byte[] dataSend=messagestr.getBytes();
				
				
				
				//byte[] dataSend = {1,1,1,1,1,1};
				try {
					tool.sendToPort(comSet.getSerialPort(), dataSend);
					//try {
					//	comSet.tool.addListener(comSet.serialPort, new SerialListener(comSet.tool,comSet.serialPort));
					//} catch (TooManyListeners e1) {
					//	// TODO Auto-generated catch block
					//	e1.printStackTrace();
					//}
				} catch (SendDataToSerialPortFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SerialPortOutputStreamCloseFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Send a message to CAN Device.");
				
			}
		});
		*/
		/*
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String dataSend = null;
				dataSend = lblNewLabel_2.getText();
				try {
					try {
						tool.addListener(comSet.serialPort, new SerialListener(tool,comSet.serialPort));
					} catch (TooManyListeners e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					tool.sendToPort(comSet.serialPort, dataSend.getBytes());
				} catch (SendDataToSerialPortFailure | SerialPortOutputStreamCloseFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
		});
		*/
		
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel.setForeground(Color.BLACK);
		panel.setBounds(39, 148, 925, 208);
		frmCantoolapp.getContentPane().add(panel);
		panel.setLayout(null);
		
		/*tree=new JTree();
		JScrollPane scrollPane = new JScrollPane(tree);
		scrollPane.setBounds(14, 42, 177, 153);
		panel.add(scrollPane);*/
	
		DefaultMutableTreeNode top=new DefaultMutableTreeNode("CANMessage");
		//DefaultMutableTreeNode node1=new DefaultMutableTreeNode(resultList.get(0).messageResult.MessageName);
		//top.add(node1);
		
		for(int i=0;i<Main.resultList.size();i++){
			Result theresult=Main.resultList.get(i);
			DefaultMutableTreeNode node1=new DefaultMutableTreeNode(Main.resultList.get(i).messageResult.MessageName);
			for(int j=0;j<theresult.signalResult.length;j++){
				System.out.println("in");
				//System.out.println(i+" "+j+" :"+resultList.get(i).signalResult[j].getSignalName());
				DefaultMutableTreeNode subnode1=new DefaultMutableTreeNode(Main.resultList.get(i).signalResult[j].SignalName);
				node1.add(subnode1);
			}
			top.add(node1);
		}
		
		tree=new JTree(top);
		scrollPane=new JScrollPane(tree);
		scrollPane.setBounds(14, 42, 177, 153);
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setBounds(243, 40, 616, 155);
		panel.add(textArea);
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.LIGHT_GRAY));
		panel_1.setBounds(39, 372, 925, 278);
		frmCantoolapp.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setBounds(92, 48, 72, 30);
		panel_1.add(lblId);
		
		JLabel lblDlc = new JLabel("DLC");
		lblDlc.setBounds(92, 109, 72, 30);
		panel_1.add(lblDlc);
		
		JLabel lblData = new JLabel("DATA");
		lblData.setBounds(92, 173, 72, 30);
		panel_1.add(lblData);
		
		textField_8 = new JTextField();
		textField_8.setBounds(179, 51, 86, 27);
		panel_1.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(178, 115, 57, 24);
		panel_1.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(179, 176, 203, 24);
		panel_1.add(textField_10);
		textField_10.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Send");
		btnNewButton_1.setBounds(580, 236, 123, 29);
		panel_1.add(btnNewButton_1);
		
		JLabel lblMmmm = new JLabel("mmmm");
		lblMmmm.setBounds(92, 236, 72, 18);
		panel_1.add(lblMmmm);
		
		textField_11 = new JTextField();
		textField_11.setBounds(179, 238, 86, 24);
		panel_1.add(textField_11);
		textField_11.setColumns(10);
		btnNewButton_1.setVisible(true);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String id=textField_8.getText();
				String dlc=textField_9.getText();
				String data=textField_10.getText();
				String mmmm=textField_11.getText();
				
				if((id.length()==0)||(dlc.length()==0)||(data.length()==0)||(mmmm.length()==0)){
					JOptionPane.showMessageDialog(null, "Please enter all content.");
					
				}else if((id.length()!=3)||(dlc.length()!=1)||(data.length()!=16)||(mmmm.length()!=4)){
					JOptionPane.showMessageDialog(null, "Please enter right format.");
				}else{
					String message="t"+id+dlc+data+mmmm;
					byte[] dataSend=message.getBytes();
					try {
						tool.sendToPort(comSet.getSerialPort(), dataSend);
						//try {
						//	comSet.tool.addListener(comSet.serialPort, new SerialListener(comSet.tool,comSet.serialPort));
						//} catch (TooManyListeners e1) {
						//	// TODO Auto-generated catch block
						//	e1.printStackTrace();
						//}
					} catch (SendDataToSerialPortFailure e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SerialPortOutputStreamCloseFailure e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "Send a message to CAN Device.");
				}
			}
		});
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(39, 13, 938, 66);
		frmCantoolapp.getContentPane().add(panel_2);
		//panel_2.add(btnNewButton_1);
		panel_2.setLayout(null);
		
		btnInformation = new JButton("INFORMATION");
		btnInformation.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String version="V\r";
				byte[] dataSend = version.getBytes();
				
				try {
					tool.sendToPort(comSet.getSerialPort(), dataSend);
				} catch (SendDataToSerialPortFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SerialPortOutputStreamCloseFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Version Information");
			}
		});
		btnInformation.setBounds(550, 19, 140, 34);
		panel_2.add(btnInformation);
		
		btnClose = new JButton("CLOSE");
		
		btnClose.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnClose.setEnabled(false);
				btnOpen.setEnabled(true);
				btnSetup.setEnabled(true);
				String close="C\r";
				byte[] dataSend = close.getBytes();
				
				try {
					tool.sendToPort(comSet.getSerialPort(), dataSend);
				} catch (SendDataToSerialPortFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SerialPortOutputStreamCloseFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "CAN Device is closed! ");
			}
		});
		btnClose.setBounds(372, 19, 140, 34);
		panel_2.add(btnClose);
		
		btnOpen = new JButton("OPEN");
		btnOpen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnOpen.setEnabled(false);
				btnClose.setEnabled(true);
				btnSetup.setEnabled(false);
				String open="O1\r";
				byte[] dataSend = open.getBytes();
				
				try {
					tool.sendToPort(comSet.getSerialPort(), dataSend);
					
				} catch (SendDataToSerialPortFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SerialPortOutputStreamCloseFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "CAN Device is opened! ");
				
			}
		});
		btnOpen.setBounds(200, 19, 140, 34);
		panel_2.add(btnOpen);
		
		JLabel lblControl = new JLabel("CANDevice Control:");
		lblControl.setBounds(14, 27, 172, 18);
		panel_2.add(lblControl);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"S0", "S1", "S2", "S3", "S4", "S5", "S6", "S7", "S8"}));
		comboBox.setBounds(748, 19, 76, 34);
		panel_2.add(comboBox);
		
		btnSetup = new JButton("Setup");
		btnSetup.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				String setup=(String) comboBox.getSelectedItem();
				//System.out.println(setup);
				byte[] dataSend = setup.getBytes();
				
				try {
					tool.sendToPort(comSet.getSerialPort(), dataSend);
				} catch (SendDataToSerialPortFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SerialPortOutputStreamCloseFailure e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Set up Communication Speed. ");
			}
		});
		btnSetup.setBounds(832, 23, 73, 27);
		panel_2.add(btnSetup);
		
		if(btnOpen.isEnabled()==true){
			btnClose.setEnabled(false);
			//btnInformation.setEnabled(false);
		}
		
		JLabel lblSerialportConfigure = new JLabel("SerialPort Configure:");
		lblSerialportConfigure.setBounds(55, 97, 185, 18);
		frmCantoolapp.getContentPane().add(lblSerialportConfigure);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(627, 78, 350, 60);
		frmCantoolapp.getContentPane().add(panel_3);
		panel_3.setLayout(null);
		panel_3.setBorder(BorderFactory.createTitledBorder("More operation")); 
		
		JButton btnNewButton_3 = new JButton("More Database");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fd = new JFileChooser();
				fd.showOpenDialog(null);  
				File f = fd.getSelectedFile(); 
				if(f!=null){
					messageList = new ArrayList<CANMessage>();
			 		signalList = new ArrayList<CANSignal>();
			 		//File file=new File("src/database.txt");	
			 		Database database = new Database(messageList,signalList,f);
			 		database.toDatabase();
			 		messageList = database.getMessageList();
			 		signalList = database.getSignalList();
			 		JOptionPane.showMessageDialog(null, "You choose the file: "+f);
				}else{
					
				}
				
				
			}
		});
		btnNewButton_3.setBounds(40, 23, 149, 37);
		panel_3.add(btnNewButton_3);
		
		JButton btnNewButton_2 = new JButton("TO JSON");
		btnNewButton_2.setBounds(223, 24, 113, 34);
		panel_3.add(btnNewButton_2);
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				File file1=new File("CANMessageJSON.json");
				FileWriter fw1;
				try {
					fw1 = new FileWriter(file1, true);
					BufferedWriter bw1 = new BufferedWriter(fw1);
					
					for(int i=0;i<messageList.size();i++){
						CANMessage newmessage=messageList.get(i);
						Gson gson = new Gson();
				        String json = gson.toJson(newmessage);
				        System.out.println(json);
				        bw1.write(json);
				        bw1.close();
				        fw1.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				File file2=new File("CANSignalJSON.json");
				FileWriter fw2;
				try {
					fw2 = new FileWriter(file2, true);
					BufferedWriter bw2 = new BufferedWriter(fw2);
					
					for(int i=0;i<signalList.size();i++){
						CANSignal newsignal=signalList.get(i);
						Gson gson = new Gson();
				        String json = gson.toJson(newsignal);
				        System.out.println(json);
				        bw2.write(json);
				        bw2.close();
				        fw2.close();
					}
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				JOptionPane.showMessageDialog(null, "Transform to JSON");

			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnInformation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		
	}
}
