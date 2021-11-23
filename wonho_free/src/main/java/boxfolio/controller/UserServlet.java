package boxfolio.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import boxfolio.domain.UserVO;
import boxfolio.persistence.UserDAO;

/**
 * Servlet implementation class userServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public UserServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
//	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		if (cmdReq.equals("login")) {
			RequestDispatcher view = request.getRequestDispatcher("login.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("signin")) {
			response.sendRedirect("signin.html");
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
		
		String message = "";
		
		if (cmdReq.equals("login")) {
			UserVO userVO = new UserVO();
			
			userVO.setId(request.getParameter("id"));
			userVO.setPasswd(request.getParameter("passwd"));
			
			UserDAO userDAO = new UserDAO();
			
			if (userDAO.checkLoginInfo(userVO.getId(), userVO.getPasswd())) {
				message = "success";
			}
			else {
				message = "아이디 또는 비밀번호가 잘못 입력되었습니다.";
				request.setAttribute("loginError", message);
				
				RequestDispatcher view = request.getRequestDispatcher("login.jsp");
				view.forward(request, response);
			}
			
		}
		
	}

}
