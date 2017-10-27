import java.util.ArrayList;


public class FindResult {
	
	
	public FindResult(){
		
	}
	
	public ArrayList<Result> find(ArrayList<CANMessage> messageList,ArrayList<CANSignal> signalList,ArrayList<Result> resultList,int id,char[] data){
		int sum=0;
		for(int i=0;i<signalList.size();i++){
			if(id==signalList.get(i).MessageId){
				sum++;
			}
		}
		CANMessage messageResult = new CANMessage();
		CANSignal[] signalResult = new CANSignal[sum];
		double[] phyResult = new double[sum];
		
		Result result = new Result(messageResult,signalResult,phyResult);
		result.setMstr(String.valueOf(data));
		
		int resultpos = 0;
		//System.out.println(messageList.size());
		for(int i=0;i<messageList.size();i++){
			if(id==messageList.get(i).getId()){
				result.setMessageResult(messageList.get(i));
				for(int j=0;j<signalList.size();j++){
					if(id==signalList.get(j).MessageId){
						CANSignal canSig = signalList.get(j);
						signalResult[resultpos]=canSig;
						//result.setSignalResultI(canSig, resultpos);
						
						//System.out.println(charData[11]);
						char[] charX=new char[canSig.getBitLength()];
						//System.out.println(canSig.getStartPos());
						//System.out.println(canSig.getBitLength());
						int count=0;
						String bitFormat=String.valueOf(canSig.getBitFormat());
						//System.out.println(String.valueOf(canSig.getBitFormat()));
						if(bitFormat.contains("0+")){
							for(int k=canSig.getStartPos();k<=canSig.getStartPos()+canSig.getBitLength()-1;k++){
								charX[count]=data[k];
								count++;
							}
						}else{
							for(int k=canSig.getStartPos();k<=canSig.getStartPos()+canSig.getBitLength()-1;k++){
								charX[canSig.getBitLength()-count-1]=data[k];
								count++;
								
							}
						}
							
						//System.out.println(charX);
							
						String binaryStr=String.valueOf(charX);
						int intX=Integer.parseInt(binaryStr, 2);
						//System.out.println(intX);
						double phyValue=canSig.getA()*intX+canSig.getB();
						phyResult[resultpos]=phyValue;
						//result.setPhyResultI(phyValue, resultpos);
						System.out.println("phyValue:"+phyValue);
						resultpos++;
						System.out.println("resultpos:"+resultpos);
					}else{
						continue;
					}
				}
			}else{
				continue;
			}
		}
		resultList.add(result);
		
		return resultList;
		
	}

}
