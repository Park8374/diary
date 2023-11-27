package vo;

public class Counter {
	private String cntDAte;
	private int cntNum;
	public String getCntDAte() {
		return cntDAte;
	}
	public void setCntDAte(String cntDAte) {
		this.cntDAte = cntDAte;
	}
	public int getCntNum() {
		return cntNum;
	}
	public void setCntNum(int cntNum) {
		this.cntNum = cntNum;
	}
	
	@Override
	public String toString() {
		return "Counter [cntDAte=" + cntDAte + ", cntNum=" + cntNum + "]";
	}
}
