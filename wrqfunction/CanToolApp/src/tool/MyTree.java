package tool;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.tree.DefaultMutableTreeNode;

import main.Main;
import dataprocess.Result;

public class MyTree extends JFrame{
	public JFrame frame;
	public ArrayList<Result> resultList;
	public MyTree(JFrame frame,ArrayList<Result> resultList){
		this.frame=frame;
		this.resultList=resultList;
		//test();
		
	}
/*	
	public void toTree(){
		DefaultMutableTreeNode top=new DefaultMutableTreeNode("CANMessage");
		//DefaultMutableTreeNode node1=new DefaultMutableTreeNode(resultList.get(0).messageResult.MessageName);
		//top.add(node1);
		
		for(int i=0;i<Main.resultList.size();i++){
			Result theresult=Main.resultList.get(i);
			DefaultMutableTreeNode node1=new DefaultMutableTreeNode(Main.resultList.get(i).messageResult.MessageName);
			for(int j=0;j<theresult.signalResult.length;j++){
				//System.out.println(i+" "+j+" :"+resultList.get(i).signalResult[j].getSignalName());
				DefaultMutableTreeNode subnode1=new DefaultMutableTreeNode(Main.resultList.get(i).signalResult[j].SignalName);
				node1.add(subnode1);
			}
			top.add(node1);
		}
		
		tree=new JTree(top);
		Main.scrollPane=new JScrollPane(tree);
		Main.scrollPane.setBounds(14, 42, 177, 153);
		Main.panel.add(Main.scrollPane);
	}*/
	
}

