package boxfolio.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boxfolio.domain.PostVO;
import boxfolio.domain.UserVO;

public class PostDAO {
	Connection conn = null;
	PreparedStatement pstmt = null;
	
	String jdbc_driver = "com.mysql.cj.jdbc.Driver";
	String jdbc_url = "jdbc:mysql://localhost:3306/boxfoliodb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC";
	
	void connect() {
		try {
			Class.forName(jdbc_driver);
			conn = DriverManager.getConnection(jdbc_url, "boxfoliopm", "bf1234");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	void disconnect() {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean addPost(PostVO pvo) {
		connect();
		String sql = "insert into post(title, content, created, user_id, user_name) values(?, ?, now(), ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pvo.getPostTitle());
			pstmt.setString(2, pvo.getPostContent());
			pstmt.setString(3, pvo.getUserId());
			pstmt.setString(4, pvo.getUserName());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public PostVO searchPostByTitleAndName(String title, String userName) {
		connect();
		String sql = "select * from post where title=? and user_name=?";
		PostVO pvo = new PostVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			pstmt.setString(2, userName);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pvo.setPostTitle(rs.getString("title"));
				pvo.setPostContent(rs.getString("content"));
				pvo.setPostCreated(rs.getString("created"));
				pvo.setUserId(rs.getString("user_id"));
				pvo.setUserName(rs.getString("user_name"));
				pvo.setpostViews(rs.getInt("views"));
				pvo.setPostLikes(rs.getInt("likes"));
				pvo.setPostScraps(rs.getInt("scraps"));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return pvo;
	}
	
	public ArrayList<PostVO> getPostList() {
		connect();
		ArrayList<PostVO> postList = new ArrayList<PostVO>();
		String sql = "select * from post";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PostVO pvo = new PostVO();
				pvo.setPostId(rs.getInt("post_id"));
				pvo.setPostTitle(rs.getString("title"));
				pvo.setPostContent(rs.getString("content"));
				pvo.setPostCreated(rs.getString("created"));
				pvo.setUserId(rs.getString("user_id"));
				pvo.setUserName(rs.getString("user_name"));
				pvo.setpostViews(rs.getInt("views"));
				pvo.setPostLikes(rs.getInt("likes"));
				pvo.setPostScraps(rs.getInt("scraps"));
				postList.add(pvo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return postList;
	}
	
}
