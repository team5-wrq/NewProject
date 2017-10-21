
public class PhyValue {
	public double phyValue;
	public CANSignal canSig;
	public String messageData;
	public PhyValue(CANSignal canSig){
		this.canSig=canSig;
	}
	
	
	public double getPhyValue() {
		return phyValue;
	}


	public void setPhyValue(double phyValue) {
		this.phyValue = phyValue;
	}

	//这里先直接写了一串Data
	public double valueTrans(){
		messageData="0000000000010001000100100001001100010100000101010001011000010111";
		//System.out.println(messageData);
		char[] charData=messageData.toCharArray();
		//System.out.println(charData[11]);
		char[] charX=new char[canSig.getBitLength()];
		//System.out.println(canSig.getStartPos());
		//System.out.println(canSig.getBitLength());
		int j=0;
		for(int i=canSig.getStartPos();i<=canSig.getStartPos()+canSig.getBitLength()-1;i++){
			//System.out.println(i);
			charX[j]=charData[i];
			//System.out.println(charX);
			j++;
		}
		System.out.println(charX);
		String binaryStr=String.valueOf(charX);
		int intX=Integer.parseInt(binaryStr, 2);
		System.out.println(intX);
		phyValue=canSig.getA()*intX+canSig.getB();
		System.out.println(phyValue);
		return phyValue;
		
	}

}


