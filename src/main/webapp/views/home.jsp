<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring Boot with JSP</title>
    <style type="text/css">
        label {
            display: inline-block;
            width: 200px;
            margin: 5px;
            text-align: left;
        }
        input[type=text], input[type=password], select {
            width: 200px;  
        }
        input[type=radio] {
            display: inline-block;
            margin-left: 45px;
        }
        input[type=checkbox] {
            display: inline-block;
            margin-right: 190px;
        }  
         
        button {
            padding: 10px;
            margin: 10px;
        }
    </style>
</head>
<body>
<h2>Welcome <c:out value="${username}"/></h2>
<div float="right"><h1> <form action="logout" method="post">
    <input type="submit" value="Sign Out"/>
</form></h1></div>
<c:if test="${employees!=null}">
<h3 text-align="center">Employee List</h3>   
<table border="1" cellpadding="10">
    <tr>
        <th>Employee ID</th>
        <th>Employee Name</th>
        <th>Experience</th>
    </tr>
    <c:forEach items="${employees}" var="employee"> 
      <tr>
        <td><c:out value="${employee.employeeId}" /></td>
        <td><c:out value="${employee.employeeName}" /></td>
        <td><c:out value="${employee.experience}" /></td>
      </tr>
    </c:forEach>
  </table>
</c:if>
<br/>
<c:if test="${isadmin}">
  <h3 text-align="center">Add Employee</h3>
  <c:if test="${employeeAdded!=null}">
    <c:out value="Employee Added successfully"/>
  </c:if>
  <form action="add" method="POST">
    <label>Employee Name <input type="text" name="employeeName" id="employeeName" /></label><br />
    <label>Experience <input type="text" name="experience" id="experience" /></label><br />
    <br/>
    <input type="submit" value="Submit" name="submit" />
</form>
</c:if>
</body>
</html>