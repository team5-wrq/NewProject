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
		try {
			this.str=new String(data,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//toAnalyse();
	}
	
	public ArrayList<Result> toAnalyse(){
		DataConvert dataConvert = new DataConvert(str);
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
		Main.resultList = findResult.find(Main.messageList,Main.signalList,Main.resultList,id,binarydata);
		
		//存到树里
		//toTree();
		return Main.resultList;
		
	}
	

}
