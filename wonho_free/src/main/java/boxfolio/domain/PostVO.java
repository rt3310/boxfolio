package boxfolio.domain;

public class PostVO {
	
	private int postId;
	private String postTitle;
	private String postContent;
	private String postCreated;
	private String userId;
	private String userName;
	private int postViews;
	private int postLikes;
	private int postScraps;
	
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
	public int getpostViews() {
		return postViews;
	}
	public void setpostViews(int postViews) {
		this.postViews = postViews;
	}
	public int getPostLikes() {
		return postLikes;
	}
	public void setPostLikes(int postLikes) {
		this.postLikes = postLikes;
	}
	public int getPostScraps() {
		return postScraps;
	}
	public void setPostScraps(int postScraps) {
		this.postScraps = postScraps;
	}

}
