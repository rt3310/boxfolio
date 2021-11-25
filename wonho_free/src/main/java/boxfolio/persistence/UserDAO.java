package boxfolio.persistence;

import java.sql.*;
import java.util.ArrayList;

import boxfolio.domain.UserVO;

public class UserDAO {
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
	
	public boolean addUser( UserVO vo ) {
		connect();
		String sql = "insert into user values (?,?,?,?,?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserId());
			pstmt.setString(2, vo.getUserPasswd());
			pstmt.setString(3, vo.getUserName());
			pstmt.setString(4, vo.getUserBirth());
			pstmt.setString(5, vo.getUserEmail());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public UserVO readUser(String id) {
		connect();
		String sql = "select * from user where id=?";
		UserVO vo = new UserVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				vo.setUserId(rs.getString("id"));
				vo.setUserPasswd(rs.getString("passwd"));
				vo.setUserName(rs.getString("username"));
				vo.setUserBirth(rs.getString("birth"));
				vo.setUserEmail(rs.getString("email"));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return vo;
	}
	
	public boolean checkLoginInfo(String id, String passwd) {
		connect();
		String sql = "select * from user where id=?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				if (passwd.equals(rs.getString("passwd"))) {
					return true;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return false;
	}
	
	public boolean updateUser(UserVO vo) {
		connect();
		String sql = "update user set passwd=?, username=?, birth=?, email=? where id=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getUserPasswd());
			pstmt.setString(2, vo.getUserName());
			pstmt.setString(3, vo.getUserBirth());
			pstmt.setString(4, vo.getUserEmail());
			pstmt.setString(5, vo.getUserId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public ArrayList<UserVO> getUserList() {
		connect();
		ArrayList<UserVO> userList = new ArrayList<UserVO>();
		String sql = "select * from user";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				UserVO vo = new UserVO();
				vo.setUserId(rs.getString("id"));
				vo.setUserPasswd(rs.getString("passwd"));
				vo.setUserName(rs.getString("username"));
				vo.setUserBirth(rs.getString("birth"));
				vo.setUserEmail(rs.getString("email"));
				userList.add(vo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return userList;
	}
}
