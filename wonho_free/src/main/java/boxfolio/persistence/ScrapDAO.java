package boxfolio.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import boxfolio.domain.LikeVO;
import boxfolio.domain.ScrapVO;

public class ScrapDAO {
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
	
	public boolean addScrap( ScrapVO vo ) {
		connect();
		String sql = "insert into scraps(user_id, post_id) values (?,?)";
		
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
	
	public boolean deleteScrap( ScrapVO vo ) {
		connect();
		String sql = "delete from scraps where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getScrapId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean setScrapIdAutoIncrement(int scrapSize) {
		connect();
		String sql = "alter table scraps auto_increment=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, scrapSize);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public boolean sortScrapId() {
		connect();
		String sql1 = "set @count=0";
		String sql2 = "update scraps set scraps.id = @count:=@count+1";
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
	
	public boolean initScrapId(int scrapSize) {
		if (sortScrapId()) {
			if (setScrapIdAutoIncrement(scrapSize)) {
				return true;
			}
		}
		return false;
	}
	
	public ScrapVO searchLikeByUserIdAndPostId(String userId, int postId) {
		connect();
		String sql = "select * from scraps where user_id=? and post_id=?";
		ScrapVO svo = new ScrapVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setInt(2, postId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				svo.setScrapId(rs.getInt("id"));
				svo.setUserId(rs.getString("user_id"));
				svo.setPostId(rs.getInt("post_id"));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return svo;
		
	}
	
	public ArrayList<ScrapVO> getScrapList() {
		connect();
		ArrayList<ScrapVO> scrapList = new ArrayList<ScrapVO>();
		String sql = "select * from scraps";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				ScrapVO svo = new ScrapVO();
				svo.setScrapId(rs.getInt("id"));
				svo.setUserId(rs.getString("user_id"));
				svo.setPostId(rs.getInt("post_id"));
				scrapList.add(svo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return scrapList;
	}
}
