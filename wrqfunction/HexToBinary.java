
public class HexToBinary {
	private static String hexStr =  "0123456789ABCDEF"; 
	private String hexString;
	public HexToBinary(char[] hexString){
		this.hexString = String.valueOf(hexString);
	}
    
	public char[] hex2Binary(){
		//hexString的长度对2取整，作为bytes的长度  
        //int len = hexString.length()/2;  
        //byte[] bytes = new byte[len];  
        //byte high = 0;//字节高四位  
        //byte low = 0;//字节低四位  
        //for(int i=0;i<len;i++){  
             //右移四位得到高位  
             //high = (byte)((hexStr.indexOf(hexString.charAt(2*i)))<<4);  
             //low = (byte)hexStr.indexOf(hexString.charAt(2*i+1));  
             //bytes[i] = (byte) (high|low);//高地位做或运算  
        //}  
        //return bytes;  
		if (hexString == null || hexString.length() % 2 != 0)  
			return null;  
	    String bString = "", tmp;  
	    for (int i = 0; i < hexString.length(); i++)  
	    {  
	        tmp = "0000"  
	                    + Integer.toBinaryString(Integer.parseInt(hexString  
	                            .substring(i, i + 1), 16));  
	        bString += tmp.substring(tmp.length() - 4);  
	    }  
	    return bString.toCharArray();  
	      
	}
}
