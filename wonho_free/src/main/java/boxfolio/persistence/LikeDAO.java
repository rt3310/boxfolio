package boxfolio.persistence;

import java.sql.*;
import java.util.ArrayList;

import boxfolio.domain.LikeVO;
import boxfolio.domain.PostVO;
import boxfolio.domain.ScrapVO;
import boxfolio.domain.UserVO;

public class LikeDAO {
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
	
	public boolean addLike( LikeVO vo ) {
		connect();
		String sql = "insert into likes(user_id, post_id) values (?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
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
	
	public boolean deleteLike( LikeVO vo ) {
		connect();
		String sql = "delete from likes where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getLikeId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean setLikeIdAutoIncrement(int likeSize) {
		connect();
		String sql = "alter table likes auto_increment=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, likeSize);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean sortLikeId() {
		connect();
		String sql1 = "set @count=0";
		String sql2 = "update likes set likes.id = @count:=@count+1";
		try {
			Statement stmt = conn.createStatement();
			stmt.addBatch(sql1);
			stmt.addBatch(sql2);
			stmt.executeBatch();
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean initLikeId(int likeSize) {
		if (sortLikeId()) {
			if (setLikeIdAutoIncrement(likeSize)) {
				return true;
			}
		}
		return false;
	}
	
	public LikeVO searchLikeByUserIdAndPostId(String userId, int postId) {
		connect();
		String sql = "select * from likes where user_id=? and post_id=?";
		LikeVO lvo = new LikeVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, postId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				lvo.setLikeId(rs.getInt("id"));
				lvo.setUserId(rs.getString("user_id"));
				lvo.setPostId(rs.getInt("post_id"));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return lvo;
		
	}
	
	public ArrayList<LikeVO> getLikeList() {
		connect();
		ArrayList<LikeVO> likeList = new ArrayList<LikeVO>();
		String sql = "select * from likes";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				LikeVO lvo = new LikeVO();
				lvo.setLikeId(rs.getInt("id"));
				lvo.setUserId(rs.getString("user_id"));
				lvo.setPostId(rs.getInt("post_id"));
				likeList.add(lvo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return likeList;
	}
}
