import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<char[]> array = new ArrayList<char[]>();
		char[] canmessage = new char[32];
		int i=0;
		File file = new File("G://Java/Documents/CANToolDataConvert/src/data.txt");
		Reader reader = null;
		//InputStream in = null;
		
		try{
			//先按字符来读，到时候应该是字节吧？
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while((tempchar = reader.read())!=-1){
				//System.out.println((char)tempchar);
				if((char)tempchar=='t' || (char)tempchar=='T'){
					canmessage=new char[32];
					i=0;
					canmessage[i]=(char)tempchar;
					i++;
				}else if((char)tempchar=='\\'){
					canmessage[i]=(char)tempchar;
					i++;
					array.add(canmessage);
				}else{
					canmessage[i]=(char)tempchar;
					i++;
				}
			}
			for(int j=0;j<array.size();j++){
				System.out.println(array.get(j));
			}
			
			reader.close();
		}catch(IOException e){
			e.printStackTrace();
			return;
		}
		
		//Message data = new Message(array.get(0));
		//data.splitData();
		char[] adata = array.get(2);
		System.out.println(adata);
		char[] messageID = new char[8];
		int messageL = 0;
		char[] messageData = new char[16];
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
		
		HexToBinary hexb = new HexToBinary(messageData);
		char[] bChar = hexb.hex2Binary();
		System.out.println(bChar);
		
		String strbinary = "0000000000010001000100100001001100010100000101010001011000010111";
		char[] binarychar = strbinary.toCharArray();
		BinaryToHex binaryh = new BinaryToHex(binarychar);
		char[] hChar = binaryh.binary2Hex();
		System.out.println(hChar);

	}

}
