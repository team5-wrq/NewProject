
public class HexToDecimal {
	String hexString;
	public HexToDecimal(String hexString){
		this.hexString=hexString;
	}
	
	public int hex2Decimal(){
		return Integer.parseInt(hexString,16);
	}

}
