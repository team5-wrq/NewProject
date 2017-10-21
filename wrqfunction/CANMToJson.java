import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.Gson;





public class CANMToJson {
	public String thisLine;
	public CANMessage newMessage=new CANMessage();
	public CANMToJson(String thisLine){
		this.thisLine=thisLine;
	}
	
	public void toJson(){
		//CANMessage newMessage=new CANMessage();
		
		try {
			File file=new File("G://Java/Documents/CANToolJson/src/CANMessageJSON.json");
			FileWriter fw = new FileWriter(file, true);
			BufferedWriter bw = new BufferedWriter(fw);
			//FileWriter writer = new FileWriter("G://Java/Documents/CANToolJson/src/CANMessageJSON.json");
		
		String regExForBO="(BO_)\\s(\\d+)\\s(.*?):\\s+(\\d)\\s(\\S+)";    //5个组
		Pattern pattern=Pattern.compile(regExForBO);
		Matcher matcher=pattern.matcher(thisLine);
		//CANMessage newMessage=new CANMessage();
		if(matcher.find()){
			System.out.println(matcher.group(2));
			
			newMessage.setBO(matcher.group(1));
			newMessage.setId(Integer.parseInt(matcher.group(2)));
			newMessage.setMessageName(matcher.group(3));
			newMessage.setDLC(Integer.parseInt(matcher.group(4)));
			newMessage.setNodeName(matcher.group(5));
			
			System.out.println(newMessage.getBO());
			System.out.println(newMessage.getId());
			System.out.println(newMessage.getMessageName());
			System.out.println(newMessage.getDLC());
			System.out.println(newMessage.getNodeName());
		}
		
		//开始转JSON
		//JSONObject json=JSONObject.fromObject(newMessage);
		//String strJson=json.toString();
		//System.out.println("strJson:"+strJson);
		Gson gson = new Gson();
        String json = gson.toJson(newMessage);
        System.out.println(json);
        bw.write(json);
        bw.close();
        fw.close();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
	
	public int messageId(){
		return newMessage.getId();
	}
}
