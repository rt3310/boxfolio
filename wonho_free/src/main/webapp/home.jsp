<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="resources/home.css">

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
            <div class="banner">
                <div class="wrap">
                    <div class="banner-container">
                        <h2>내 박스안의 포트폴리오 Boxfolio</h2>
                        <p>나만의 포트폴리오를 구성하고 관리하자!</p>
                    </div>
                </div>
            </div>
        </section>
        <section>
            <div class="wrap">
                <div class="contents-container">
                    <ul class="contents">
                        <li>
                            <a href="http://localhost:8080/wonho_free/PortfolioServlet?cmd=create">
                                <img id="content-drawup" src="resources/contract.png" alt="포트폴리오 작성하기"/>
                                <p>포트폴리오 작성</p>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img id="content-check" src="resources/box.png" alt="내 포트폴리오 확인"/>
                                <p>내 포트폴리오 확인</p>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img id="content-team" src="resources/people.png" alt="대외활동 팀원 찾기"/>
                                <p>대외활동 팀원 찾기</p>
                            </a>
                        </li>
                        <li>
                            <a href="#">
                                <img id="content-inquire" src="resources/question.png" alt="1:1 질문"/>
                                <p>1:1 질문</p>
                            </a>
                        </li>
                    </ul>
                </div>
                
            </div>
        </section>
        <section>
            <div class="wrap">
                <div class="top-list-container">
                    <h2>인기 대외활동</h2>
                    <div class="top-list-inner">
                        <i class="far fa-caret-square-left prev-btn"></i>
                        <div class="top-list">
                            
                        </div>
                        <i class="far fa-caret-square-right next-btn"></i>
                    </div>
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