package tool;


public class BinaryToHex {
	private String bString="";
	public BinaryToHex(char[] bString){
		this.bString=String.valueOf(bString);
	}
	
	public char[] binary2Hex(){
		if (bString == null || bString.equals("") || bString.length() % 8 != 0)  
            return null;  
        StringBuffer tmp = new StringBuffer();  
        int iTmp = 0;  
        for (int i = 0; i < bString.length(); i += 4)  
        {  
            iTmp = 0;  
            for (int j = 0; j < 4; j++)  
            {  
                iTmp += Integer.parseInt(bString.substring(i + j, i + j + 1)) << (4 - j - 1);  
            }  
            tmp.append(Integer.toHexString(iTmp));  
        }  
        return tmp.toString().toCharArray();  
    
	}
}
