<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page session="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Community Page</title>
<style>
    body {
        margin: 0;
        font-family: sans-serif;
    }

    header {
        display: flex;
        justify-content: space-between;
        align-items: flex-start;
        padding: 20px 40px;
    }

    .logo {
        width: 100px;
    }

    .login {
        background-color: #fff;
        padding: 15px 25px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    .login table {
        width: 100%;
    }

    .login td {
        padding: 8px 5px;
    }

    .login label {
        display: inline-block;
        width: 70px;
        text-align: right;
    }

    .login input[type="text"],
    .login input[type="password"] {
        width: 180px;
        padding: 8px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    .button-container {
        display: flex;
        justify-content: center;
        gap: 10px;
        margin-top: 10px;
    }

    .btn,
    .btn-signup {
        padding: 8px 16px;
        background-color: #007bff;
        color: white;
        border: none;
        border-radius: 4px;
        font-size: 14px;
        cursor: pointer;
        text-decoration: none;
    }

    .btn:hover,
    .btn-signup:hover {
        background-color: #0056b3;
    }

    /* ✅ 검색창: 가운데 상단 고정 */
    .centerSearch {
        position: absolute;
        top: 20px;
        left: 50%;
        transform: translateX(-50%);
        background-color: #fff;
        padding: 10px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        z-index: 10;
    }

    /* ✅ 메뉴: 검색창 바로 아래 */
    nav {
        position: absolute;
        top: 88px; /* 간격 좁힘: 검색창 아래 12px 정도 */
        left: 50%;
        transform: translateX(-50%);
        z-index: 9;
    }

    .nav-menu {
        display: flex;
        gap: 20px;
        list-style: none;
        padding: 0;
        margin: 0;
    }

    .nav-menu li a {
        text-decoration: none;
        color: #333;
        font-size: 16px;
    }

    .nav-menu li a:hover {
        color: #007bff;
    }

    /* 검색창 select/input 정리 */
    .form-control {
        padding: 8px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 4px;
        margin-right: 10px;
    }

</style>
</head>
<body>

<!-- 상단: 로고 + 로그인 -->
<header>
    <div>
        <img src="${pageContext.request.contextPath}/resources/img/logo.png" alt="로고 이미지" class="logo" />
    </div>

    <form action="${pageContext.request.contextPath}/loginAccount" method="post" class="login">
        <table>
            <tr>
                <td><label for="userId">아이디</label></td>
                <td><input type="text" id="userId" name="userId" placeholder="아이디를 입력하세요"
                    onfocus="this.placeholder=''" onblur="this.placeholder='아이디를 입력하세요'"></td>
            </tr>
            <tr>
                <td><label for="userPassword">비밀번호</label></td>
                <td><input type="password" id="userPassword" name="userPassword" placeholder="비밀번호를 입력하세요"
                    onfocus="this.placeholder=''" onblur="this.placeholder='비밀번호를 입력하세요'"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div class="button-container">
                        <button type="submit" class="btn">로그인</button>
                        <a href="${pageContext.request.contextPath}/signup" class="btn-signup">회원가입</a>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</header>

<!-- ✅ 검색창 -->
<form action="${pageContext.request.contextPath}/searchContent" method="post" class="centerSearch">
    <table>
        <tr>
            <td>
                <select class="form-control" name="searchField">
                    <option value="0">선택</option>
                    <option value="title">제목</option>
                    <option value="userId">작성자</option>
                </select>
            </td>
            <td>
                <input type="text" class="form-control" placeholder="검색어를 입력하세요" name="searchText" maxlength="100"
                       onfocus="this.placeholder=''" onblur="this.placeholder='검색어를 입력하세요'">
            </td>
            <td>
                <button type="submit" class="btn">검색</button>
            </td>
        </tr>
    </table>
</form>

<!-- ✅ 메뉴: 검색창 바로 아래에 적당한 간격으로 배치 -->
<nav>
    <ul class="nav-menu">
        <li><a href="${pageContext.request.contextPath}/freeBoard">자유게시판</a></li>
        <li><a href="${pageContext.request.contextPath}/fleaMarket">중고장터</a></li>
        <li><a href="${pageContext.request.contextPath}/gallery">갤러리</a></li>
        <li><a href="${pageContext.request.contextPath}/notices">공지사항</a></li>
    </ul>
</nav>

</body>
</html>
