package vo;

public class Schedule {
	private int scheduleNo;
	private String memberId;
	private String scheduleDate;
	private String scheduleEmoji;
	private String scheduleMemo;
	private String createdate;
	public String getScheduleEmoji() {
		return scheduleEmoji;
	}
	public void setScheduleEmoji(String scheduleEmoji) {
		this.scheduleEmoji = scheduleEmoji;
	}
	public int getScheduleNo() {
		return scheduleNo;
	}
	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getScheduleDate() {
		return scheduleDate;
	}
	public void setScheduleDate(String scheduleDate) {
		this.scheduleDate = scheduleDate;
	}
	public String getScheduleMemo() {
		return scheduleMemo;
	}
	public void setScheduleMemo(String scheduleMemo) {
		this.scheduleMemo = scheduleMemo;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	@Override
	public String toString() {
		return "Schedule [scheduleNo=" + scheduleNo + ", memberId=" + memberId + ", scheduleDate=" + scheduleDate
				+ ", scheduleEmoji=" + scheduleEmoji + ", scheduleMemo=" + scheduleMemo + ", createdate=" + createdate
				+ "]";
	}
}
