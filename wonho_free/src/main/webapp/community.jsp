<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="boxfolio.domain.*, java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mytag" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>박스폴리오</title>
    <meta name="description" content="포트폴리오 관리 커뮤니티">
    <meta name="keywords" content="포트폴리오, 스펙, 대외활동, 동아리, 공모전">
    <meta name="author" content="WonHo Seo">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!--Open Graph-->
    <meta property="og:type" content="website">
    <meta property="og:title" content="포트폴리오 관리 커뮤니티 사이트">
    <meta property="og:description" content="포트폴리오를 작성 및 관리하고 커뮤니티를 통해 소통하며 활동할 수 있는 사이트입니다.">
    <meta property="og:site_name" content="박스폴리오" />
    <!-- <meta property="og:image" content="resources/-"> -->
    <!-- <meta property="og:url" content="https://www.kpu.ac.kr"> -->

    <!--fabicon-->
    <link rel="icon" href="resources/magic-box.png">
    <link rel="apple-touch-icon" href="resources/magic-box.png">
    <link rel="short icon" type="image/x-icon" href="resources/magic-box.png">

    <!--CSS-->
    <link rel="stylesheet" href="resources/community.css">

    <!--FontAwesome-->
    <script src="https://kit.fontawesome.com/5ac43742ba.js" crossorigin="anonymous"></script>
</head>
<body>
    <div class="root-wrap">
        <header>
            <div class="wrap">
                <div class="head-container">
                    <h1 class="logo">
                        <a href="http://localhost:8080/wonho_free/MainServlet?cmd=home">BOXFOLIO</a>
                    </h1>
                    <ul class="navi">
                        <li>
                            <a href="http://localhost:8080/wonho_free/MainServlet?cmd=portfolio">포트폴리오</a>
                        </li>
                        <li>
                            <a href="#">대외활동</a>
                        </li>
                        <li>
                            <a href="http://localhost:8080/wonho_free/MainServlet?cmd=community">커뮤니티</a>
                        </li>
                        <li>
                            <a href="#">문의하기</a>
                        </li>
                    </ul>
                    
                    <div class="menu">
                        <div class="search">
                            <form id="search-form" action="#" method="get" role="search">
                                <input id="query" type="text" title="검색" maxlength="255">
                                <button id="search-btn" type="submit" title="검색">
                                	<i class="fas fa-search search-icon"></i>
                                </button>
                            </form>
                        </div>
                        <% if (session.getAttribute("isLogined") == "true") { %>
                        	<div class="user-area">
                            	<i class="far fa-user-circle profile-icon"></i>
                            	<p><%= session.getAttribute("userName") + "님" %> </p>
                            <a href="http://localhost:8080/wonho_free/UserServlet?cmd=logout" class="logout">로그아웃</a>
                        	</div>
                        <%} else { %>
                        	<div class="user-area">
                                <a href="http://localhost:8080/wonho_free/UserServlet?cmd=login" class="login">로그인</a>
                                <i class="menu-div-bar"></i>
                                <a href="http://localhost:8080/wonho_free/UserServlet?cmd=signin" class="signin">회원가입</a>
                            </div>
                        <%} %>
                    </div>
                </div>
            </div>
        </header>
        <section>
            <div class="wrap">
                <div class="notice-title-container">
                    <h2 class="notice-title">자유게시판</h2>
                    <ul class="notice-category">
                        <li class="free-notice">
                            <a href="#">자유</a>
                        </li>
                        <li class="info-notice">
                            <a href="#">정보</a>
                        </li>
                        <li class="anony-notice">
                            <a href="#">익명</a>
                        </li>
                    </ul>
                </div>
                <div class="notice-container">
                    <table class="notice">
                        <colgroup>
                            <col style="width: 70px;">
                            <col>
                            <col style="width: 110px;">
                            <col style="width: 100px;">
                            <col style="width: 80px;">
                            <col style="width: 60px;">
                            <col style="width: 60px;">
                        </colgroup>
                        <thead>
                            <tr class="notice-head">
                                <th>#</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회</th>
                                <th>추천</th>
                                <th>스크랩</th>
                            </tr>
                        </thead>
                        <tbody>
                        	<%
                        		List<PostVO> postList = (List<PostVO>)request.getAttribute("postList");
                        		for (PostVO vo : postList) {
                        	%>
                            <tr>
                                <td class="td-num"><%=vo.getPostId() %></td>
                                <td class="td-title">
                                	<a href="http://localhost:8080/wonho_free/EditServlet?cmd=inBoard&postId=<%=vo.getPostId() %>&act=atCommunity">
                                		<%=vo.getPostTitle() %>
                                	</a>
                                	<% if (vo.getPostReplys() > 0) { %>
                                	<span class="td-replys"><%= "[" + vo.getPostReplys() + "]"  %></span>
                                	<% } %>
                                </td>
                                <td class="td-name"><a href="#"><%=vo.getUserName() %></a></td>
                                <td class="td-date"><%=vo.getPostCreated() %></td>
                                <td class="td-view"><%=vo.getpostViews() %></td>
                                <td class="td-like"><%=vo.getPostLikes() %></td>
                                <td class="td-scrap"><%=vo.getPostScraps() %></td>
                            </tr>
                            <%
                        		}
                            %>
                        </tbody>
                    </table>
                </div>
                <div class="access-container">
                    <button class="write-notice" type="button" title="글쓰기">
                        <i class="far fa-edit"></i>
                        <a href="http://localhost:8080/wonho_free/EditServlet?cmd=textedit">글쓰기</a>
                    </button>
                </div>
            </div>
        </section>
        <footer>
            <div class="wrap">
                <div class="intro-container">
                    <div class="intro">
                        <mytag:printfooter/>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>