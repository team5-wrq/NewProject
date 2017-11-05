package dataprocess;



public class DataConvert {
	Message adata;
	String str;
	
	public DataConvert(String str){
		this.str=str;
	}
	
	public void dataConvert(){
		
		char[] canmessage = str.toCharArray();
		adata = new Message(canmessage);
		adata.splitData();
		
		
		//InputStream in = null;
	}

	public Message getAdata() {
		return adata;
	}

	public void setAdata(Message adata) {
		this.adata = adata;
	}
	

}
