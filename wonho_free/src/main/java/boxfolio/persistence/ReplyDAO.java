package boxfolio.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boxfolio.domain.PostVO;
import boxfolio.domain.ReplyVO;

public class ReplyDAO {
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
	
	public boolean addReply(ReplyVO rvo) {
		connect();
		String sql = "insert into reply(content, created, post_id, user_id, user_name) values(?, now(), ?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rvo.getReplyContent());
			pstmt.setInt(2, rvo.getPostId());
			pstmt.setString(3, rvo.getUserId());
			pstmt.setString(4, rvo.getUserName());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public ArrayList<ReplyVO> searchReplyListByPostId(int postId) {
		connect();
		ArrayList<ReplyVO> replyList = new ArrayList<ReplyVO>();
		String sql = "select * from reply where post_id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, postId);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ReplyVO rvo = new ReplyVO();
				rvo.setReplyId(rs.getInt("id"));
				rvo.setReplyContent(rs.getString("content"));
				rvo.setReplyCreated(rs.getString("created"));
				rvo.setPostId(rs.getInt("post_id"));
				rvo.setUserId(rs.getString("user_id"));
				rvo.setUserName(rs.getString("user_name"));
				replyList.add(rvo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return replyList;
	}
}
