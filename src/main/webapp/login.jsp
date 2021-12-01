<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	hier können sie sich anmelden 
	
	<form method="POST" action="LoginServlet">
		<input type="text" name="email" placeholder="Ihre E-Mail Adresse" required />
		<input type="passwort" name="passwort" placeholder="Ihr Passwort" required /> 
	</form>
</body>
</html>