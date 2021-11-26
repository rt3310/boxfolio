package boxfolio.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			RequestDispatcher view = request.getRequestDispatcher("signin.jsp");
			view.forward(request, response);
		}
		else if (cmdReq.equals("logout")) {
			HttpSession session = request.getSession();
			session.setAttribute("isLogined", "false");
			session.removeAttribute("userId");
			session.removeAttribute("userName");
			
			RequestDispatcher view = request.getRequestDispatcher("home.jsp");
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
		
		String message = "";
		
		if (cmdReq.equals("login")) {
			UserVO userVO = new UserVO();
			
			userVO.setUserId(request.getParameter("id"));
			userVO.setUserPasswd(request.getParameter("passwd"));
			
			UserDAO userDAO = new UserDAO();
			
			if (userDAO.checkLoginInfo(userVO.getUserId(), userVO.getUserPasswd())) {
				String username = userDAO.readUser(userVO.getUserId()).getUserName();
				HttpSession session = request.getSession();
				
				session.setAttribute("isLogined", "true");
				session.setAttribute("userId", userVO.getUserId());
				session.setAttribute("userName", username);
				
				RequestDispatcher view = request.getRequestDispatcher("home.jsp");
				view.forward(request, response);
			}
			else {
				message = "아이디 또는 비밀번호가 잘못 입력되었습니다.";
				request.setAttribute("loginError", message);
				
				RequestDispatcher view = request.getRequestDispatcher("login.jsp");
				view.forward(request, response);
			}
			
		}
		else if (cmdReq.equals("signin")) {
			UserVO uvo = new UserVO();
			UserDAO udao = new UserDAO();
			
			PrintWriter writer = response.getWriter();
			
			uvo.setUserId(request.getParameter("id"));
			uvo.setUserPasswd(request.getParameter("passwd"));
			uvo.setUserName(request.getParameter("username"));
			uvo.setUserBirth(request.getParameter("year") + "-" + request.getParameter("month") + "-" + request.getParameter("day"));
			uvo.setUserEmail(request.getParameter("email") + "@" + request.getParameter("address"));
			
			if (udao.addUser(uvo)) {
				RequestDispatcher view = request.getRequestDispatcher("signinresult.jsp");
				view.forward(request, response);
			}
			else {
				writer.println("<script>alert('업로드에 실패했습니다.');</script>");
				RequestDispatcher view = request.getRequestDispatcher("signin.jsp");
				view.forward(request, response);
			}
		}
		
	}

}
