package boxfolio.domain;

public class ReplyVO {
	
	private int replyId;
	private String replyContent;
	private String replyCreated;
	private int postId;
	private String userId;
	private String userName;
	
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String getReplyCreated() {
		return replyCreated;
	}
	public void setReplyCreated(String replyCreated) {
		this.replyCreated = replyCreated;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
