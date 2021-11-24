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

import boxfolio.domain.PostVO;
import boxfolio.domain.UserVO;
import boxfolio.persistence.PostDAO;
import boxfolio.persistence.UserDAO;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
		
		if (cmdReq.equals("textedit")) {
			HttpSession session = request.getSession();
			
			if (session.getAttribute("isLogined") == "true") {
				RequestDispatcher view = request.getRequestDispatcher("textedit.jsp");
				view.forward(request, response);
			}
			else {
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('로그인 후 이용해주세요'); location.href='http://localhost:8080/wonho_free/login.jsp'</script>");
			}
		}
		else if (cmdReq.equals("inBoard")) {
			String title = request.getParameter("title");
			String name = request.getParameter("name");
			
			HttpSession session = request.getSession();
			PostDAO pdao = new PostDAO();
			PostVO pvo = new PostVO();
			
			if (session.getAttribute("isLogined") == "true") {
				pvo = pdao.searchPostByTitleAndName(title, name);
				
				request.setAttribute("board", pvo);
				RequestDispatcher view = request.getRequestDispatcher("board.jsp");
				view.forward(request, response);
			}
			else {
				PrintWriter writer = response.getWriter();
				writer.println("<script>alert('로그인 후 이용해주세요'); location.href='http://localhost:8080/wonho_free/login.jsp'</script>");
			}
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
		
		if(cmdReq.equals("uploadBoard")) {
			HttpSession session = request.getSession();
			PostVO pvo = new PostVO();
			
			pvo.setPostTitle(request.getParameter("title"));
			pvo.setPostContent(request.getParameter("content"));
			pvo.setUserId(session.getAttribute("userId").toString());
			pvo.setUserName(session.getAttribute("userName").toString());
			
			PostDAO pdao = new PostDAO();
			
			PrintWriter writer = response.getWriter();
			if (pdao.addPost(pvo)) {
				writer.println("<script>alert('업로드 완료.');</script>");
				ArrayList<PostVO> postList = pdao.getPostList();
				request.setAttribute("postList", postList);
				RequestDispatcher view = request.getRequestDispatcher("community.jsp");
				view.forward(request, response);
			}
			else {
				writer.println("<script>alert('업로드에 실패했습니다.');</script>");
				RequestDispatcher view = request.getRequestDispatcher("textedit.jsp");
				view.forward(request, response);
			}
			
			
		}
	}

}
