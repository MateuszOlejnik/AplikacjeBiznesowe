<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Rejestracja</title>
<body>
	<B>Rejestracja nowego użytkownika: <br> <br></B>
	<form action="RejestracjaServlet" method="GET">
		Imię : <input type="text" name="imie"
			style="width: 150px; margin-left: 4.8em;"> <br> Nazwisko
		: <input type="text" name="nazwisko"
			style="width: 150px; margin-left: 2.2em"> <br> E-mail :<input
			type="text" name="email" style="width: 150px; margin-left: 4.05em;">
		<br> Login : <input type="text" name="login"
			style="width: 150px; margin-left: 4.15em;"> <br> Hasło :<input
			type="password" value="" name="haslo" style="width: 150px; margin-left: 4.5em;">
		<br> <br> <input type="submit" value="Zarejestruj"
			style="margin-left: 10em;" />
	</form>
	<form action="index.jsp" method="GET">
		 <input type="submit" value="Powrót" />
	</form>
	<br><br>
	<%
		if (request.getAttribute("message") != null) {
	%>
	<%=request.getAttribute("message")%>
	<%
		}
	%>
</body>
</html>