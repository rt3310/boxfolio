<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="resources/textedit.css">

    <!--FontAwesome-->
    <script src="https://kit.fontawesome.com/5ac43742ba.js" crossorigin="anonymous"></script>

    <script defer src="js/textedit.js"></script>
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
                    </ul>
                </div>
                <div class="edit-head-container">
                    <select name="category" id="category" required>
                        <option value="free">자유</option>
                        <option value="info">정보</option>
                        <option value="anony">익명</option>
                    </select>
                </div>
                <div class="edit-container">
                    <textarea name="edit-title" id="edit-title" cols="30" placeholder="제목"></textarea>
                    <div class="edit-tool">
                        <button id="tool-bold">
                            <i class="fas fa-bold"></i>
                        </button>
                        <button id="tool-italic">
                            <i class="fas fa-italic"></i>
                        </button>
                        <button id="tool-under">
                            <i class="fas fa-underline"></i>
                        </button>
                        <button id="tool-cancel">
                            <i class="fas fa-strikethrough"></i>
                        </button>
                        <button id="tool-orderlist">
                            <i class="fas fa-list-ol"></i>
                        </button>
                        <button id="tool-unorderlist">
                            <i class="fas fa-list-ul"></i>
                        </button>
                        <span id="tool-image">
                            <label for="input-image">
                                <i class="far fa-image"></i>
                            </label>
                            <input type="file" id="input-image" accept="image/*" onchange="loadFile(this)">
                        </span>
                        <button id="tool-full">
                            <i class="fas fa-align-justify"></i>
                        </button>
                        <button id="tool-left">
                            <i class="fas fa-align-left"></i>
                        </button>
                        <button id="tool-center">
                            <i class="fas fa-align-center"></i>
                        </button>
                        <button id="tool-right">
                            <i class="fas fa-align-right"></i>
                        </button>
                        <button id="tool-undo">
                            <i class="fas fa-undo"></i>
                        </button>
                        <button id="tool-redo">
                            <i class="fas fa-redo"></i>
                        </button>
                        <select id="tool-fontsize">
                        	<option value="">폰트 사이즈</option>
                        	<option value="1">10px</option>
                        	<option value="2">13px</option>
                        	<option value="3">16px</option>
                        	<option value="4">18px</option>
                        	<option value="5">24px</option>
                        	<option value="6">32px</option>
                        	<option value="7">48px</option>
                        </select>
                        <select id="tool-font">
                        	<option value="">폰트</option>
                        	<option value="Black Han Sans">Black Han Sans</option>
                        	<option value="Noto Sans KR">Noto Sans KR</option>
                        	<option value="Nanum Gothic">Nanum Gothic</option>
                        	<option value="Nanum Myeongjo">Nanum Myeongjo</option>
                        	<option value="Nanum Pen Script">Nanum Pen Script</option>
                        </select>
                    </div>
                    <div id="editor" contenteditable="true">
                    </div>
                </div>
                <div class="access-container">
                    <button id="save-edit">저장</button>
                    <button id="cancel-edit">취소</button>
                </div>
            </div>
        </section>
        <footer>
            <div class="wrap">
                <div class="intro-container">
                    <div class="intro">
                        <ul>
                            <li>작성자: 서원호</li>
                            <li>tel: 010-4805-3273</li>
                            <li>
                                email:
                                <a href="mailto:btac3310@gmail.com">btac3310@gmail.com</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</body>
</html>