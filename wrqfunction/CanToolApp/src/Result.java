
public class Result {
	public CANMessage messageResult;
	public CANSignal[] signalResult;
	public double[] phyResult;
	public String mstr;
	
	public Result(CANMessage messageResult,CANSignal[] signalResult,double[] phyResult){
		this.messageResult=messageResult;
		this.signalResult=signalResult;
		this.phyResult=phyResult;
	}

	public CANSignal[] getSignalResult() {
		return signalResult;
	}

	public void setSignalResult(CANSignal[] signalResult) {
		this.signalResult = signalResult;
	}

	public double[] getPhyResult() {
		return phyResult;
	}

	public void setPhyResult(double[] phyResult) {
		this.phyResult = phyResult;
	}

	public String getMstr() {
		return mstr;
	}

	public void setMstr(String mstr) {
		this.mstr = mstr;
	}

	public CANMessage getMessageResult() {
		return messageResult;
	}

	public void setMessageResult(CANMessage messageResult) {
		this.messageResult = messageResult;
	}
	
	public void setSignalResultI(CANSignal cansig,int i){
		this.signalResult[i]=cansig;
	}
	
	public void setPhyResultI(double phy,int i){
		this.phyResult[i]=phy;
	}

}
