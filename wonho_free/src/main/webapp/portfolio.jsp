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
        <link rel="stylesheet" href="resources/portfolio.css">
    
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
                <div class="access-container">
                    <div class="pofol-search">
                        <form id="pofol-search-form" action="#" method="get" role="search">
                            <input id="pofol-query" type="text" title="포트폴리오검색" maxlength="255">
                            <button id="pofol-search-btn" type="submit" title="포트폴리오검색">
                            	<i class="fas fa-search search-icon"></i>
                            </button>
                        </form>
                    </div>
                    <div class="to-mypofol">
                        <a href="http://localhost:8080/wonho_free/PortfolioServlet?cmd=create">내 포트폴리오 만들기</a>
                        <i class="fas fa-arrow-right"></i>
                    </div>
                </div>
                <div class="pofol-container">
                    <ul class="pofol-list">
                        <c:forEach var="pofol" items="${portfolioList}">
                        <li class="pofol-card">
                            <a href="http://localhost:8080/wonho_free/PortfolioServlet?cmd=inPortfolio&id=<c:out value="${pofol.portfolioId}"/>">
                                <img src="resources/computer.jpeg" alt="portfolio" class="pofol-image"/>
                                <div class="pofol-info">
                                    <div class="pofol-title">
                                        <h3><c:out value="${pofol.portfolioTitle}"/></h3>
                                    </div>
                                    <div class="pofol-depart">
                                        <p><c:out value="${pofol.userId}"/></p>
                                    </div>
                                    <div class="pofol-hope">
                                        <p>컴퓨터공학과</p>
                                    </div>
                                    <div class="pofol-intro">
                                        <p>한줄 소개</p>
                                    </div>
                                </div>
                            </a>
                            <ul class="pofol-icons">
                                <li class="pofol-star">
                                    <i class="far fa-star"></i>
                                </li>
                                <li class="pofol-mark">
                                    <i class="far fa-bookmark"></i>
                                </li>
                            </ul>
                        </li>
                        </c:forEach>
                    </ul>
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