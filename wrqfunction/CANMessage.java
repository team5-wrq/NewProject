
public class CANMessage {
	public char[] BO=new char[32];
	public int id;
	public char[] MessageName=new char[32];
	public int DLC;
	public char[] NodeName=new char[32];
	
	public CANMessage(){
		
	}

	public char[] getBO() {
		return BO;
	}

	public void setBO(char[] bO) {
		BO = bO;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public char[] getMessageName() {
		return MessageName;
	}

	public void setMessageName(char[] messageName) {
		MessageName = messageName;
	}

	public int getDLC() {
		return DLC;
	}

	public void setDLC(int dLC) {
		DLC = dLC;
	}

	public char[] getNodeName() {
		return NodeName;
	}

	public void setNodeName(char[] nodeName) {
		NodeName = nodeName;
	}
	

}
