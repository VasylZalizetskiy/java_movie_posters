<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>AccessDenied page</title>
</head>
<body>
	Dear <strong><c:out value="${pageContext.request.remoteUser}"></c:out></strong>, You are not authorized to perform this action.
	<a href="<c:url value="/logout" />">Logout</a>
</body>
</html>