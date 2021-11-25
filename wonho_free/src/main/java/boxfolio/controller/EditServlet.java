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

import boxfolio.domain.LikeVO;
import boxfolio.domain.PostVO;
import boxfolio.domain.ReplyVO;
import boxfolio.domain.ScrapVO;
import boxfolio.domain.UserVO;
import boxfolio.persistence.LikeDAO;
import boxfolio.persistence.PostDAO;
import boxfolio.persistence.ReplyDAO;
import boxfolio.persistence.ScrapDAO;
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
		
		HttpSession session = request.getSession();
		
		if (cmdReq.equals("textedit")) {
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
			int postId = Integer.parseInt(request.getParameter("postId"));
			String act = request.getParameter("act");
			
			PostDAO pdao = new PostDAO();
			ReplyDAO rdao = new ReplyDAO();
			LikeDAO ldao = new LikeDAO();
			ScrapDAO sdao = new ScrapDAO();
			
			PostVO pvo = new PostVO();
			LikeVO lvo = new LikeVO();
			ScrapVO svo = new ScrapVO();
			ArrayList<ReplyVO> replyList = new ArrayList<ReplyVO>();

			
			if (session.getAttribute("isLogined") == "true") {
				pvo = pdao.searchPostById(postId);
				replyList = rdao.searchReplyListByPostId(postId);
				
				if (act.equals("atCommunity")) { // 커뮤니티에서 게시판 입장 시
					pvo.setpostViews(pvo.getpostViews() + 1);
					pdao.updatePostViews(pvo);
				}
				else if (act.equals("likes")) { // likes 처리 구문
					lvo = ldao.searchLikeByUserIdAndPostId(session.getAttribute("userId").toString(), postId);
					
					if (lvo.getLikeId() == 0) {
						lvo.setUserId(session.getAttribute("userId").toString());
						lvo.setPostId(postId);
						
						if (ldao.addLike(lvo)) {
							pvo.setPostLikes(pvo.getPostLikes() + 1);
							pdao.updatePostLikes(pvo);
						}
					}
					else {
						if (ldao.deleteLike(lvo)) {
							pvo.setPostLikes(pvo.getPostLikes() - 1);
							pdao.updatePostLikes(pvo);
							ldao.initLikeId(ldao.getLikeList().size());
						}
					}
				}
				else if (act.equals("scraps")) { // scraps 처리 구문
					svo = sdao.searchLikeByUserIdAndPostId(session.getAttribute("userId").toString(), postId);
					
					if (svo.getScrapId() == 0) {
						svo.setUserId(session.getAttribute("userId").toString());
						svo.setPostId(postId);
						
						if (sdao.addScrap(svo)) {
							pvo.setPostScraps(pvo.getPostScraps() + 1);
							pdao.updatePostScraps(pvo);
						}
					}
					else {
						if (sdao.deleteScrap(svo)) {
							pvo.setPostScraps(pvo.getPostScraps() - 1);
							pdao.updatePostScraps(pvo);
							sdao.initScrapId(sdao.getScrapList().size());
						}
					}
				}
				request.setAttribute("board", pvo);
				request.setAttribute("replyList", replyList);
				
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
		HttpSession session = request.getSession();
		
		String cmdReq = "";
		cmdReq = request.getParameter("cmd");
		
		if(cmdReq.equals("uploadBoard")) {
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
		else if (cmdReq.equals("uploadReply")) {
			ReplyVO rvo = new ReplyVO();
			PostDAO pdao = new PostDAO();
			ReplyDAO rdao = new ReplyDAO();
			PostVO pvo = new PostVO();
			ArrayList<ReplyVO> replyList = new ArrayList<ReplyVO>();
			
			int postId = Integer.parseInt(request.getParameter("postId"));
			
			rvo.setReplyContent(request.getParameter("reply"));
			rvo.setPostId(postId);
			rvo.setUserId(session.getAttribute("userId").toString());
			rvo.setUserName(session.getAttribute("userName").toString());
			
			PrintWriter writer = response.getWriter();
			if (rdao.addReply(rvo) ) {
				pvo = pdao.searchPostById(postId);
				replyList = rdao.searchReplyListByPostId(postId);
				
				pvo.setPostReplys(replyList.size());
				if (pdao.updatePostReply(pvo)) {
					request.setAttribute("board", pvo);
					request.setAttribute("replyList", replyList);
					
					RequestDispatcher view = request.getRequestDispatcher("board.jsp");
					view.forward(request, response);
				}
			}
		}
	}

}
