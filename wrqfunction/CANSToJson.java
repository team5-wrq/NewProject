import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;


public class CANSToJson {
	public String thisLine;
	public int thisID;
	public CANSignal newSignal=new CANSignal();
	
	public CANSToJson(String thisLine){
		this.thisLine=thisLine;
	}
	
	public void tojson(int thisID){
		this.thisID=thisID;
		try{
			File file=new File("G://Java/Documents/CANToolJson/src/CANSignalJSON.json");
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			
			String regExForSG="(.*SG_)\\s(.*?)\\s:\\s(\\d+)\\|(\\d+)@(.*?)\\s\\(([\\-\\.0-9]+),([\\-\\.0-9]+)\\)\\s\\[([\\-\\.0-9]+)\\|([\\-\\.0-9]+)\\]\\s\\\"(.*?)\\\"\\s+(.*)";    //11个组
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
				
				
				System.out.println(newSignal.getSG());
				//System.out.println(newSignal.getMessageId());
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
				System.out.println("-----------");
				
				//开始转JSON
				
				Gson gson = new Gson();
		        String json = gson.toJson(newSignal);
		        System.out.println(json);
		        bw.write(json);
		        bw.close();
		        fw.close();
			}
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
