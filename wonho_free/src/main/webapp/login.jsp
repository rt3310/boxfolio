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
    <link rel="stylesheet" href="resources/login.css">

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
                        <a href="http://localhost:8080/wonho_free/UserServlet?cmd=login" class="login">로그인</a>
                        <i class="menu-div-bar"></i>
                        <a href="http://localhost:8080/wonho_free/UserServlet?cmd=signin" class="signin">회원가입</a>
                    </div>
                </div>
            </div>
        </header>
        <section>
            <div class="login-title">
                <h1>Login for BOXFOLIO</h1>
            </div>
        </section>
        <section>
            <div class="wrap">
                <div class="login-container">
                    <form class="login-form" action="http://localhost:8080/wonho_free/UserServlet?cmd=login" method="post">
                        <div class="id-area">
                            <i class="fas fa-user id-icon"></i>
                            <input type="text" name="id" autofocus required placeholder="아이디"/>
                        </div>
                        <div class="pw-area">
                            <i class="fas fa-key pw-icon"></i>
                            <input type="password" name="passwd" required placeholder="비밀번호"/>
                        </div>
                        <div class="keep-login-area">
                            <label for="keep-login" style="cursor: pointer;"><input id="keep-login" type="checkbox" name="keep-login" value="로그인 상태 유지"/>로그인 상태 유지</label>
                        </div>
                        <div class="keep-id-area">
                            <label for="keep-id" style="cursor: pointer;"><input id="keep-id" type="checkbox" name="keep-id" value="아이디 유지"/>아이디 유지</label>
                        </div>
                        <div class="login-message-area">
                        	<%
                        		String loginMsg = (String)request.getAttribute("loginError");
                        		if (loginMsg == null) {
                        			loginMsg = "";
                        		}
                        	%>
                            <p><%=loginMsg %></p>
                        </div>
                        <div class="login-btn-area">
                            <input class="login-btn" type="submit" name="submit" value="로그인"/>
                        </div>
                        <div class="login-manage-area">
                            <a href="#" class="seek-id">아이디 찾기</a>
                            <i class="menu-div-bar"></i>
                            <a href="#" class="seek-pw">비밀번호 찾기</a>
                            <i class="menu-div-bar"></i>
                            <a href="http://127.0.0.1:8080/wonho_free/signin.html" class="signin">회원가입</a>
                        </div>
                    </form>
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