package dataprocess;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.io.File;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import tool.HexToBinary;
import tool.JTreeTable;

import javax.swing.JTextPane;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;


public class ReceivedMessage extends JFrame {

	private JPanel contentPane;
	public JTree tree;
	public JTextArea textArea;
	public JScrollPane scrollPane;

	public ArrayList<Result> resultList;
	/**
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ReceivedMessage(ArrayList<Result> resultList) {
		this.resultList=resultList;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 510, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//tree = new JTree();
		//scrollPane.setRowHeaderView(tree);
		
		textArea = new JTextArea();
		textArea.setBounds(144, 0, 348, 377);
		contentPane.add(textArea);
		
		//scrollPane = new JScrollPane();
		//scrollPane.setBounds(0, 0, 492, 379);
		//contentPane.add(scrollPane);
		
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		//JTreeTable treetable=new JTreeTable();
		/*
		//程序一运行，就去转化database让它成为CANMessageList和CANSignalList
		ArrayList<CANMessage> messageList = new ArrayList<CANMessage>();
		ArrayList<CANSignal> signalList = new ArrayList<CANSignal>();
		
		Database database = new Database(messageList,signalList);
		database.toDatabase();
		messageList = database.getMessageList();
		signalList = database.getSignalList();
		
		//每次运行只有一个ResultArray存要显示在树里的结果
		resultList = new ArrayList<Result>();
		
		//还是先来一行吧
		DataConvert dataConvert = new DataConvert("t320880478C2F05A1D29A");
		dataConvert.dataConvert();
		//取到ID、DATA
		Message message = dataConvert.getAdata();
		System.out.println(message.messageID);
		System.out.println(message.messageData);
		
		//messageID转十进制再去找
		int id = Integer.parseInt(String.valueOf(message.messageID),16);
		System.out.println(id);
		//messageData转二进制
		HexToBinary h2b = new HexToBinary(message.messageData);
		char[] binarydata=h2b.hex2Binary();
		System.out.println(binarydata);
		
		//现在去list里找,能找到的就存入resultList
		FindResult findResult = new FindResult();
		resultList = findResult.find(messageList,signalList,resultList,id,binarydata);
		
		System.out.println(resultList.get(0).mstr);
		*/
		//存到树里
		DefaultMutableTreeNode top=new DefaultMutableTreeNode("CANMessage");
		//DefaultMutableTreeNode node1=new DefaultMutableTreeNode(resultList.get(0).messageResult.MessageName);
		//top.add(node1);
		
		for(int i=0;i<resultList.size();i++){
			Result theresult=resultList.get(i);
			DefaultMutableTreeNode node1=new DefaultMutableTreeNode(resultList.get(i).messageResult.MessageName);
			for(int j=0;j<theresult.signalResult.length;j++){
				//System.out.println(i+" "+j+" :"+resultList.get(i).signalResult[j].getSignalName());
				DefaultMutableTreeNode subnode1=new DefaultMutableTreeNode(resultList.get(i).signalResult[j].SignalName);
				node1.add(subnode1);
			}
			top.add(node1);
		}
		
		tree=new JTree(top);
		//tree = new JTree();
		scrollPane=new JScrollPane(tree);
		scrollPane.setBounds(0, 0, 130, 379);
		contentPane.add(scrollPane);
		
		//scrollPane.setRowHeaderView(tree);
		
		//鼠标响应
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			 
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree
                        .getLastSelectedPathComponent();
 
                if (node == null)
                    return;
 
                Object object = node.getUserObject();
                System.out.println(object);
                if (node.isLeaf()) {
                	//Result result=(Result)object;
                	//System.out.println("choose:"+result.mstr);
                	for(int i=0;i<resultList.size();i++){
                		Result theresult=resultList.get(i);
                		
                		for(int j=0;j<theresult.signalResult.length;j++){
                			if(object==theresult.signalResult[j].getSignalName()){
                				System.out.println(theresult.phyResult[j]);
                				textArea.setText(theresult.signalResult[j].getSignalName()+" : "+theresult.phyResult[j]+" "+theresult.signalResult[j].unit);
                			}
                		}
                	}
                    //Result result = (Result) object;
                    //System.out.println(");
                }else{
                	for(int i=0;i<resultList.size();i++){
                		Result theresult=resultList.get(i);
                		if(object==theresult.messageResult.MessageName){
                			//System.out.println("in"+theresult.messageResult.MessageName);
                			String str="";
                			for(int j=0;j<theresult.signalResult.length;j++){
                    			
                				System.out.println(theresult.phyResult[j]);
                				str+=theresult.signalResult[j].getSignalName()+":"+theresult.phyResult[j]+" "+theresult.signalResult[j].unit;
                				str+="\r\n";
                    			textArea.setText(str);
                    			
                    		}
                		}
                	}
                }
 
            }
        });
		
		
	}
}
