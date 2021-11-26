package boxfolio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import boxfolio.domain.PortfolioVO;
import boxfolio.domain.PostVO;
import boxfolio.persistence.PortfolioDAO;
import boxfolio.persistence.PostDAO;

/**
 * Servlet implementation class PortfolioServlet
 */
@WebServlet("/PortfolioServlet")
public class PortfolioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PortfolioServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		HttpSession session = request.getSession();
		
		PortfolioVO pfvo = new PortfolioVO();
		PortfolioDAO pfdao = new PortfolioDAO();
		
		if (cmdReq.equals("create")) {
			if (session.getAttribute("isLogined") == "true") {
				RequestDispatcher view = request.getRequestDispatcher("portfolioboard.jsp");
				view.forward(request, response);
			}
			else {
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('로그인 후 이용해주세요'); location.href='http://localhost:8080/wonho_free/login.jsp'</script>");
			}
		}
		else if (cmdReq.equals("inPortfolio")) {
			int id = Integer.parseInt(request.getParameter("id"));
			
			pfvo = pfdao.searchPortfolioById(id);
			request.setAttribute("portfolioBoard", pfvo);
			RequestDispatcher view = request.getRequestDispatcher("portfolioresult.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		HttpSession session = request.getSession();
		
		if (cmdReq.equals("uploadPortfolio")) {
			PortfolioVO pfvo = new PortfolioVO();
			
			pfvo.setPortfolioTitle(request.getParameter("title"));
			pfvo.setPortfolioContent(request.getParameter("content"));
			pfvo.setUserId(session.getAttribute("userId").toString());
			
			PortfolioDAO pfdao = new PortfolioDAO();
			
			PrintWriter writer = response.getWriter();
			if (pfdao.addPortfolio(pfvo)) {
				writer.println("<script>alert('업로드 완료.');</script>");
				
				ArrayList<PortfolioVO> portfolioList = pfdao.getPortfolioList();
				request.setAttribute("portfolioList", portfolioList);
				
				RequestDispatcher view = request.getRequestDispatcher("portfolio.jsp");
				view.forward(request, response);
			}
			else {
				writer.println("<script>alert('업로드에 실패했습니다.');</script>");
				RequestDispatcher view = request.getRequestDispatcher("portfolioboard.jsp");
				view.forward(request, response);
			}
		}
	}

}
