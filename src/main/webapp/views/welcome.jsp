<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head><title>Spring Boot with JSP</title></head>
<body>
<h2>Welcome User</h2>
<c:if test="${isAuthenticated}">
<h3><c:out value="${username}"/>, This is guest page.</h3>
</c:if>
<h3>Please click <a href="http://localhost:8080/home">here</a> to access Employee Details</h3>
</body>
</html>