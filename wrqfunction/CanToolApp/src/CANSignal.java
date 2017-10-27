

public class CANSignal {
	public String SG;
	public int MessageId;
	public String SignalName;
	public int startPos;
	public int bitLength;
	public String bitFormat;
	public double A;
	public double B;
	public double C;
	public double D;
	public String unit;
	public String NodeName;
	
	public CANSignal(){
		
	}

	public String getSG() {
		return SG;
	}

	public void setSG(String sG) {
		SG = sG;
	}

	public int getMessageId() {
		return MessageId;
	}

	public void setMessageId(int messageId) {
		MessageId = messageId;
	}

	public String getSignalName() {
		return SignalName;
	}

	public void setSignalName(String signalName) {
		SignalName = signalName;
	}

	public String getBitFormat() {
		return bitFormat;
	}

	public void setBitFormat(String bitFormat) {
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

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getNodeName() {
		return NodeName;
	}

	public void setNodeName(String nodeName) {
		NodeName = nodeName;
	}

	public int getStartPos() {
		return startPos;
	}

	public void setStartPos(int startPos) {
		this.startPos = startPos;
	}

	public int getBitLength() {
		return bitLength;
	}

	public void setBitLength(int bitLength) {
		this.bitLength = bitLength;
	}
	
	

}
