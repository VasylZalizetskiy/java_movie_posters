<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<link rel="stylesheet" href="/resources/css/denied.css" type="text/css" media="all" />
</head>
<body>
<div class="error-main">
	<h1>Oops!</h1>
	<div class="error-heading">403</div>
	<p>Dear <strong><c:out value="${pageContext.request.remoteUser}"></c:out></strong>, You do not have permission to access the document or program that you requested.</p>
	<a href="<c:url value="/" />">Home</a>
	<a href="<c:url value="/logout" />">Logout</a>
</div>
</body>
</html>