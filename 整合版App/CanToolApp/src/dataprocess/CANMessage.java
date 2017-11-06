package dataprocess;



public class CANMessage {
	public String BO;
	public int id;
	public String MessageName;
	public int DLC;
	public String NodeName;
	
	public CANMessage(){
		
	}

	public String getBO() {
		return BO;
	}

	public void setBO(String string) {
		BO = string;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessageName() {
		return MessageName;
	}

	public void setMessageName(String messageName) {
		MessageName = messageName;
	}

	public int getDLC() {
		return DLC;
	}

	public void setDLC(int dLC) {
		DLC = dLC;
	}

	public String getNodeName() {
		return NodeName;
	}

	public void setNodeName(String nodeName) {
		NodeName = nodeName;
	}
	
    
	public String toString(){
		return "null";
	}
}