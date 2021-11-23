package boxfolio.domain;

public class PostVO {
	
	private int postId;
	private String postTitle;
	private String postContent;
	private String postCreated;
	private String userId;
	private String userName;
	private int postView;
	private int postLike;
	private int postScrap;
	
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostCreated() {
		return postCreated;
	}
	public void setPostCreated(String postCreated) {
		this.postCreated = postCreated;
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
	public int getpostView() {
		return postView;
	}
	public void setpostView(int postView) {
		this.postView = postView;
	}
	public int getPostLike() {
		return postLike;
	}
	public void setPostLike(int postLike) {
		this.postLike = postLike;
	}
	public int getPostScrap() {
		return postScrap;
	}
	public void setPostScrap(int postScrap) {
		this.postScrap = postScrap;
	}

}
