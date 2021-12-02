<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<jsp:include page="head.jsp" />
<body>
	<jsp:include page="navigation.jsp" />
	Hier können Sie sich anmelden, wenn Sie bereits ein Konto bei uns besitzen. 
	
	<form method="POST" action="LoginServlet">
		<input type="text" name="email" placeholder="Ihre E-Mail Adresse" required />
		<input type="password" name="passwort" placeholder="Ihr Passwort" required /> 
		<input type="submit" value="Anmelden" />
	</form>

	<p>${fehlertyp}</p>
	<jsp:include page="footer.jsp" />
</body>
</html>