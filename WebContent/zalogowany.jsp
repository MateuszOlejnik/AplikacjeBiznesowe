<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Zalogowany</title>
<body>
<form action="ZalogowanyServlet">
	Jesteś zalogowany jako : <% if ( request.getAttribute( "message" ) != null ) { %><%=request.getAttribute( "message" )%><% } %><input type="submit" value="Wyloguj" style="height: 30px; margin-left: 18em" />	
</form>
	
	
	
</body>
</html>