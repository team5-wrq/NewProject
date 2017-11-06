package dataprocess;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import tool.HexToBinary;
import main.Main;

public class Analyse {
	public String str; 
	public Analyse(byte[] data){
		this.str=new String(data);
	}
	
	public Result toAnalyse(){
		DataConvert dataConvert = new DataConvert(str);
		dataConvert.dataConvert();
		//取到ID、DATA
		Message message = dataConvert.getAdata();
		System.out.println(message.messageID);
		System.out.println(message.messageData);
		
		//messageID转十进制再去找
		int id = Integer.parseInt(String.valueOf(message.messageID),16);
		System.out.println(id);
		//messageData转char数组
		HexToBinary h2b=new HexToBinary(message.messageData);
		char[] messagechar=h2b.hex2Binary();
		
		int dlc=message.messageL;
		char[][] mchar=new char[dlc][8];   //dlc
		//System.out.println(oldmessageData);
		//String str="";
		//ArrayList<char[]> oldmessageList=new ArrayList<char[]>();
		//char[] oldmessageChar=oldmessageData.toCharArray();
		
		for(int i=0;i<dlc;i++){
			for(int j=0;j<8;j++){
				mchar[i][j]=messagechar[i*8+j];
			}
		}
		
				
		//现在去list里找,能找到的就存入resultList
		FindResult findResult = new FindResult();
		Result res = findResult.find(Main.messageList,Main.signalList,id,mchar);
		
		//存到树里
		//toTree();
		return res;
		
	}
	

}
