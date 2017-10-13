
public class Message {
	private char[] adata = new char[32];
	public char[] messageID = new char[8];
	public int messageL = 0;
	public char[] messageData = new char[16];
	
	public Message(char[] adata){
		this.adata=adata;
	}
	
	public void splitData(){
		if(adata[0]=='t'){
			for(int a=0;a<3;a++){
				messageID[a] = adata[a+1];
			}
			System.out.println(messageID);
			messageL = Integer.valueOf(adata[4]) - 48;
			System.out.println(messageL);
			for(int b=0;b<messageL*2;b++){
				messageData[b] = adata[b+5];
			}
			System.out.println(messageData);
		}else{
			for(int a=0;a<8;a++){
				messageID[a] = adata[a+1];
			}
			System.out.println(messageID);
			messageL = Integer.valueOf(adata[9]) - 48;
		    System.out.println(messageL);
			for(int b=0;b<messageL*2;b++){
				messageData[b] = adata[b+10];
			}
			System.out.println(messageData);
		}
	}

}
