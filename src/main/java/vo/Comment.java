package vo;

// Notice 댓글
public class Comment {
	// FK : notice_no , member_id
	private int commentNo;
	private int noticeNo;
	private String memberId;
	private String commentPw;
	private String commentContent;
	private boolean isSecret; // true : 비밀글(본인,관리자)
	private String createdate;
	
	@Override
	public String toString() {
		return "Comment [commentNo=" + commentNo + ", noticeNo=" + noticeNo + ", memberId=" + memberId + ", commentPw="
				+ commentPw + ", commentContent=" + commentContent + ", isSecret=" + isSecret + ", createdate="
				+ createdate + "]";
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getNoticeNo() {
		return noticeNo;
	}

	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getCommentPw() {
		return commentPw;
	}

	public void setCommentPw(String commentPw) {
		this.commentPw = commentPw;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public boolean isSecret() {
		return isSecret;
	}

	public void setSecret(boolean isSecret) {
		this.isSecret = isSecret;
	}

	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	
	
}
