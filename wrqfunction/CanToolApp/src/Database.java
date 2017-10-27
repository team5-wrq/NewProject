import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Database {
	public ArrayList<CANMessage> messageList;
	public ArrayList<CANSignal> signalList;
	public int thisID;
	
	public Database(ArrayList<CANMessage> messageList,ArrayList<CANSignal> signalList){
		this.messageList=messageList;
		this.signalList=signalList;
	}
	
	public void toDatabase(){
		try{
			StringBuffer sb=new StringBuffer("");
			File file=new File("G://Java/Documents/CanToolApp/src/database.txt");
			FileReader reader=new FileReader(file);
			//BufferedReader br=new BufferedReader(reader);
			LineNumberReader lnr=new LineNumberReader(reader);
			int thisID=0;
			
			String thisLine=null;
			while((thisLine=lnr.readLine())!=null){
				if(thisLine.contains("BO")){
					//System.out.println(thisLine);
					CANMessage newMessage =new CANMessage();
					String regExForBO="(BO_)\\s(\\d+)\\s(.*?):\\s+(\\d)\\s(\\S+)";    //5个组
					Pattern pattern=Pattern.compile(regExForBO);
					Matcher matcher=pattern.matcher(thisLine);
					
					if(matcher.find()){
						//System.out.println(matcher.group(2));
						
						newMessage.setBO(matcher.group(1));
						newMessage.setId(Integer.parseInt(matcher.group(2)));
						newMessage.setMessageName(matcher.group(3));
						newMessage.setDLC(Integer.parseInt(matcher.group(4)));
						newMessage.setNodeName(matcher.group(5));
						
						//System.out.println(newMessage.getBO());
						//System.out.println(newMessage.getId());
						//System.out.println(newMessage.getMessageName());
						//System.out.println(newMessage.getDLC());
						//System.out.println(newMessage.getNodeName());
					}
					messageList.add(newMessage);
					thisID=newMessage.getId();
				}else{
					//System.out.println(thisLine);
					String regExForSG="(.*SG_)\\s(.*?)\\s:\\s(\\d+)\\|(\\d+)@(.*?)\\s\\(([\\-\\.0-9]+),([\\-\\.0-9]+)\\)\\s\\[([\\-\\.0-9]+)\\|([\\-\\.0-9]+)\\]\\s\\\"(.*?)\\\"\\s+(.*)";    //11个组
					CANSignal newSignal =new CANSignal();
					Pattern pattern=Pattern.compile(regExForSG);
					Matcher matcher=pattern.matcher(thisLine);
					if(matcher.find()){
						//System.out.println("yes");
						newSignal.setMessageId(thisID);
						newSignal.setSG(matcher.group(1));
						newSignal.setSignalName(matcher.group(2));
						newSignal.setStartPos(Integer.parseInt(matcher.group(3)));
						newSignal.setBitLength(Integer.parseInt(matcher.group(4)));
						newSignal.setBitFormat(matcher.group(5));
						newSignal.setA(Double.parseDouble(matcher.group(6)));
						newSignal.setB(Double.parseDouble(matcher.group(7)));
						newSignal.setC(Double.parseDouble(matcher.group(8)));
						newSignal.setD(Double.parseDouble(matcher.group(9)));
						newSignal.setUnit(matcher.group(10));
						newSignal.setNodeName(matcher.group(11));
						
						/*
						System.out.println(newSignal.getSG());
						System.out.println(newSignal.getMessageId());
						System.out.println(newSignal.getSignalName());
						System.out.println(newSignal.getStartPos());
						System.out.println(newSignal.getBitLength());
						System.out.println(newSignal.getBitFormat());
						System.out.println(newSignal.getA());
						System.out.println(newSignal.getB());
						System.out.println(newSignal.getC());
						System.out.println(newSignal.getD());
						System.out.println(newSignal.getUnit());
						System.out.println(newSignal.getNodeName());
						System.out.println("-----------");*/
						
				}
					signalList.add(newSignal);
					
			}
			
		}}catch(IOException e){
			e.printStackTrace();
		}

	
	}
	

	public ArrayList<CANMessage> getMessageList() {
		return messageList;
	}

	public void setMessageList(ArrayList<CANMessage> messageList) {
		this.messageList = messageList;
	}

	public ArrayList<CANSignal> getSignalList() {
		return signalList;
	}

	public void setSignalList(ArrayList<CANSignal> signalList) {
		this.signalList = signalList;
	}
	
	

}
