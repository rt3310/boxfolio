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
	String jdbc_url = "jdbc:mysql://localhost:3306/boxfoliodb?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC&autoReconnect=true";
	
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
	
	public PostVO searchPostById(int postId) {
		connect();
		String sql = "select * from post where id=?";
		PostVO pvo = new PostVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pvo.setPostId(rs.getInt("id"));
				pvo.setPostTitle(rs.getString("title"));
				pvo.setPostContent(rs.getString("content"));
				pvo.setPostCreated(rs.getString("created"));
				pvo.setUserId(rs.getString("user_id"));
				pvo.setUserName(rs.getString("user_name"));
				pvo.setpostViews(rs.getInt("views"));
				pvo.setPostLikes(rs.getInt("likes"));
				pvo.setPostScraps(rs.getInt("scraps"));
				pvo.setPostReplys(rs.getInt("replys"));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return pvo;
	}
	
	public boolean updatePostReply(PostVO vo) {
		connect();
		String sql = "update post set replys=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getPostReplys());
			pstmt.setInt(2, vo.getPostId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
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
				pvo.setPostId(rs.getInt("id"));
				pvo.setPostTitle(rs.getString("title"));
				pvo.setPostContent(rs.getString("content"));
				pvo.setPostCreated(rs.getString("created"));
				pvo.setUserId(rs.getString("user_id"));
				pvo.setUserName(rs.getString("user_name"));
				pvo.setpostViews(rs.getInt("views"));
				pvo.setPostLikes(rs.getInt("likes"));
				pvo.setPostScraps(rs.getInt("scraps"));
				pvo.setPostReplys(rs.getInt("replys"));
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
