package dataprocess;
import java.util.ArrayList;


public class FindResult {
	
	
	public FindResult(){
		
	}
	
	public Result find(ArrayList<CANMessage> messageList,ArrayList<CANSignal> signalList,int id,char[][] mchar){
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
		result.setMstr(String.valueOf(mchar));
		
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
						String bitFormat=String.valueOf(canSig.getBitFormat());
						//System.out.println(charData[11]);
						char[] charX=new char[canSig.getBitLength()];
						//System.out.println(canSig.getStartPos());
						//System.out.println(canSig.getBitLength());
						if(bitFormat.contains("0+")){
							//商---->第几行
							int row=canSig.getStartPos()/8;
							//取余
							int remainder=canSig.getStartPos()%8;
							System.out.println(row);
							System.out.println(remainder);
							
							for(int i1=0;i1<canSig.getBitLength();i1++){
								if((7-remainder+i1)<8){
									charX[i1]=mchar[row][7-remainder+i1];
								}else if((7-remainder+i1)>=8&&(7-remainder+i1)<16){
									charX[i1]=mchar[row+1][i1-remainder-1];
								}else{
									charX[i1]=mchar[row+2][i1-8-remainder-1];
								}
								
							}
							System.out.println(charX);
						}else{
							//商---->第几行
							int row=canSig.getStartPos()/8;
							//取余
							int remainder=canSig.getStartPos()%8;
							System.out.println(row);
							System.out.println(remainder);
							
							for(int i1=0;i1<canSig.getBitLength();i1++){
								if((7-remainder+i1)<8){
									charX[i1]=mchar[row][7-remainder+i1];
								}else if((7-remainder+i1)>=8&&(7-remainder+i1)<16){
									charX[i1]=mchar[row+1][i1-remainder-1];
								}else if((7-remainder+i1)>=16&&(7-remainder+i1)<24){
									charX[i1]=mchar[row+2][i1-8-remainder-1];
								}else if((7-remainder+i1)>=24&&(7-remainder+i1)<32){
									charX[i1]=mchar[row+3][i1-16-remainder-1];
								}else if((7-remainder+i1)>=32&&(7-remainder+i1)<40){
									charX[i1]=mchar[row+4][i1-24-remainder-1];
								}else{
									charX[i1]=mchar[row+5][i1-32-remainder-1];
								}
								
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
		//resultList.add(result);
		
		return result;
		
	}

}
