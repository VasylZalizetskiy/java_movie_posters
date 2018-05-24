<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all" />
    <script type="text/javascript" src="/resources/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-func.js"></script>
    <script type="text/javascript" src="/resources/js/modal.js"></script>
    <!--[if IE 6]><link rel="stylesheet" href="/resources/css/ie6.css" type="text/css" media="all" /><![endif]-->
</head>
<body>
<!-- START PAGE SOURCE -->
<c:if test="${param.error ne null}">
    <div>
        Invalid username and password.
    </div>
</c:if>
<c:if test="${param.logout ne null}">
    <div>
        You have been logged out.
    </div>
</c:if>

<form class="modal-content animate" action="/login" method="post">
    <div class="imgcontainer">
        <img src="/resources/images/img_avatar2.png" alt="Avatar" class="avatar">
    </div>

    <div class="container">
        <label for="username"><b>Username</b></label>
        <input type="text" placeholder="Enter Username" name="username" required>

        <label for="password"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" name="password" required>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        <button type="submit">Login</button>

    </div>

    <div class="container" style="background-color:#f1f1f1">
        <button type="button" class="cancelbtn">Cancel</button>
        <span class="psw">Forgot <a href="#">password?</a></span>
    </div>
</form>

</body>
</html>