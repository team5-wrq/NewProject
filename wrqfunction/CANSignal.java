
public class CANSignal {
	public char[] SG=new char[32];
	public int MessageId;
	public char[] SignalName=new char[32];
	public char[] bitFormat=new char[10];
	public double A;
	public double B;
	public double C;
	public double D;
	public char[] unit=new char[32];
	public char[] NodeName=new char[255];
	
	public CANSignal(){
		
	}

	public char[] getSG() {
		return SG;
	}

	public void setSG(char[] sG) {
		SG = sG;
	}

	public int getMessageId() {
		return MessageId;
	}

	public void setMessageId(int messageId) {
		MessageId = messageId;
	}

	public char[] getSignalName() {
		return SignalName;
	}

	public void setSignalName(char[] signalName) {
		SignalName = signalName;
	}

	public char[] getBitFormat() {
		return bitFormat;
	}

	public void setBitFormat(char[] bitFormat) {
		this.bitFormat = bitFormat;
	}

	public double getA() {
		return A;
	}

	public void setA(double a) {
		A = a;
	}

	public double getB() {
		return B;
	}

	public void setB(double b) {
		B = b;
	}

	public double getC() {
		return C;
	}

	public void setC(double c) {
		C = c;
	}

	public double getD() {
		return D;
	}

	public void setD(double d) {
		D = d;
	}

	public char[] getUnit() {
		return unit;
	}

	public void setUnit(char[] unit) {
		this.unit = unit;
	}

	public char[] getNodeName() {
		return NodeName;
	}

	public void setNodeName(char[] nodeName) {
		NodeName = nodeName;
	}
	
	

}
