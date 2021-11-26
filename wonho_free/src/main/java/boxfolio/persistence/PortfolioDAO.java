package boxfolio.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import boxfolio.domain.PortfolioVO;
import boxfolio.domain.PostVO;

public class PortfolioDAO {
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
	
	public boolean addPortfolio(PortfolioVO pfvo) {
		connect();
		String sql = "insert into portfolio(title, content, user_id) values(?, ?, ?)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pfvo.getPortfolioTitle());
			pstmt.setString(2, pfvo.getPortfolioContent());
			System.out.println(pfvo.getPortfolioContent());
			pstmt.setString(3, pfvo.getUserId());
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			disconnect();
		}
		return true;
	}
	
	public ArrayList<PortfolioVO> getPortfolioList() {
		connect();
		ArrayList<PortfolioVO> portfolioList = new ArrayList<PortfolioVO>();
		String sql = "select * from portfolio";
		
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				PortfolioVO pfvo = new PortfolioVO();
				pfvo.setPortfolioId(rs.getInt("id"));
				pfvo.setPortfolioTitle(rs.getString("title"));
				pfvo.setPortfolioContent(rs.getString("content"));
				pfvo.setUserId(rs.getString("user_id"));
				portfolioList.add(pfvo);
			}
			rs.close();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return portfolioList;
	}
	
	public PortfolioVO searchPortfolioById(int portfolioId) {
		connect();
		String sql = "select * from portfolio where id=?";
		PortfolioVO pfvo = new PortfolioVO();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, portfolioId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pfvo.setPortfolioId(rs.getInt("id"));
				pfvo.setPortfolioTitle(rs.getString("title"));
				pfvo.setPortfolioContent(rs.getString("content"));
				pfvo.setUserId(rs.getString("user_id"));
			}
		
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return pfvo;
	}
}
