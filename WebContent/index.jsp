<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Logowanie</title>
<body>

	<form action="rejestracja.jsp">
		<B> Logowanie / Rejestracja</B> <input type="submit"
			value="Rejestracja" style="height: 30px; margin-left: 15em">
		<br> <br>
	</form>
	<form action="LoginServlet" method="GET">
		Login: <input type="text" name="login"
			style="width: 150px; margin-left: 3.4em;"> <br> Hasło: <input
			type="password" name="haslo"
			style="width: 150px; margin-left: 3.5em;"><br> <br>
		<input type="submit" value="Zaloguj" style="margin-left: 5em;" />
	</form>
	<br>
	<%
		if (request.getAttribute("message") != null) {
	%>
	<%=request.getAttribute("message")%>
	<%
		}
	%>
</body>
</html>