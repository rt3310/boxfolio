package boxfolio.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boxfolio.domain.PostVO;
import boxfolio.persistence.PostDAO;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
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
		
		if (cmdReq.equals("home")) {
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("portfolio")) {
			RequestDispatcher view = request.getRequestDispatcher("portfolio.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("community")) {
			PostDAO pdao = new PostDAO();
			ArrayList<PostVO> postList = pdao.getPostList();
			request.setAttribute("postList", postList);
			
			RequestDispatcher view = request.getRequestDispatcher("community.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
