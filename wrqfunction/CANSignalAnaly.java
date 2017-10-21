import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CANSignalAnaly {
	private File file;
	public CANMessage thisMessage=new CANMessage();
	List<CANSignal> CANSignalList=new ArrayList();
	private int lineNum;
	public CANSignalAnaly(File file,CANMessage newMessage,int lineNum){
		this.file=file;
		this.thisMessage=newMessage;
		this.lineNum=lineNum;
	}
	
	public void analyse(){
		//往下找行，解析CANSignal
		try {
			FileReader reader=new FileReader(file);
			LineNumberReader lnr=new LineNumberReader(reader);
			
			String regExForSG="(.*SG_)\\s(.*?)\\s:\\s(\\d+)\\|(\\d+)@(.*?)\\s\\(([\\-\\.0-9]+),([\\-\\.0-9]+)\\)\\s\\[([\\-\\.0-9]+)\\|([\\-\\.0-9]+)\\]\\s\\\"(.*?)\\\"\\s+(.*)";    //11个组
			
			String thisLine=null;
			while((thisLine=lnr.readLine())!=null){
				if(lnr.getLineNumber()<=lineNum){
					continue;
				}else if((lnr.getLineNumber()>lineNum)&&(thisLine.contains("SG"))){
					//创建CANSignal对象并解析
					CANSignal newSignal=new CANSignal();
					CANSignalList.add(newSignal);
					Pattern pattern=Pattern.compile(regExForSG);
					Matcher matcher=pattern.matcher(thisLine);
					//匹配组
					if(matcher.find()){
						//System.out.println("yes");
						newSignal.setMessageId(thisMessage.getId());
						newSignal.setSG(matcher.group(1).toCharArray());
						newSignal.setSignalName(matcher.group(2).toCharArray());
						newSignal.setStartPos(Integer.parseInt(matcher.group(3)));
						newSignal.setBitLength(Integer.parseInt(matcher.group(4)));
						newSignal.setBitFormat(matcher.group(5).toCharArray());
						newSignal.setA(Double.parseDouble(matcher.group(6)));
						newSignal.setB(Double.parseDouble(matcher.group(7)));
						newSignal.setC(Double.parseDouble(matcher.group(8)));
						newSignal.setD(Double.parseDouble(matcher.group(9)));
						newSignal.setUnit(matcher.group(10).toCharArray());
						newSignal.setNodeName(matcher.group(11).toCharArray());
						
						
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
						
						//调用算物理值的函数
						PhyValue phyValue=new PhyValue(newSignal);
						double phy=phyValue.valueTrans();
					}
				}else {
					break;
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
