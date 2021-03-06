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
    <link rel="stylesheet" href="resources/signin.css">

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
            <div class="signin-title">
                <h1>Signin for BOXFOLIO</h1>
            </div>
        </section>
        <section>
            <div class="wrap">
                <div class="signin-container">
                    <form class="signin-form" action="http://localhost:8080/wonho_free/UserServlet?cmd=signin" method="post">
                        <div class="id-area">
                            <label for="input-id">아이디</label>
                            <input class="input-id" type="text" name="id" autofocus required/>
                        </div>
                        <div class="pw-area">
                            <label for="input-pw">비밀번호</label>
                            <input class="input-pw" type="password" name="passwd" required/>
                        </div>
                        <div class="pwcheck-area">
                            <label for="input-pwcheck">비밀번호 확인</label>
                            <input class="input-pwcheck" type="password" name="passwdcheck" required/>
                        </div>
                        <div class="name-area">
                            <label for="input-name">이름</label>
                            <input class="input-name" type="text" name="username" required/>
                        </div>
                        <div class="birth-area">
                            <label for="input-birth">생년월일</label>
                            <div class="input-birth">
                                <input class="input-year" type="text" name="year" required placeholder="년"/>
                                <input class="input-month" type="text" name="month" required placeholder="월"/>
                                <input class="input-day" type="text" name="day" required placeholder="일"/>
                            </div>
                        </div>
                        <div class="email-area">
                            <label for="input-email">이메일</label>
                            <div class="input-email">
                                <input class="input-email-id" type="text" name="email" required/>
                                <span>@</span>
                                <select name="address" id="address" required>
                                    <option value="directly">직접입력</option>
                                    <option value="naver">naver.com</option>
                                    <option value="google">gmail.com</option>
                                    <option value="kakao">kakao.com</option>
                                </select>
                            </div>
                        </div>
                        <div class="signin-btn-area">
                            <input class="signin-btn" type="submit" name="submit" value="회원가입"/>
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