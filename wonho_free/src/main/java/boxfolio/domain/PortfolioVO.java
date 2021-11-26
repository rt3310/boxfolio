package boxfolio.domain;

public class PortfolioVO {
	private int PortfolioId;
	private String PortfolioTitle;
	private String PortfolioContent;
	private String userId;
	
	public int getPortfolioId() {
		return PortfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		PortfolioId = portfolioId;
	}
	public String getPortfolioTitle() {
		return PortfolioTitle;
	}
	public void setPortfolioTitle(String portfolioTitle) {
		PortfolioTitle = portfolioTitle;
	}
	public String getPortfolioContent() {
		return PortfolioContent;
	}
	public void setPortfolioContent(String portfolioContent) {
		PortfolioContent = portfolioContent;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
