<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	hier können sie sich registrieren 
	
	<form method="POST" action="RegistrierungsServlet">
		<input type="text" name="vorname" placeholder="Vorname"required/>
		<input type="text" name="nachname" placeholder="Nachname" required/>
		<input type="number" name="alter" placeholder="Alter" required/> 
		<input type="email" name="email" placeholder="E-Mail Adresse" required/> 
		<input type="text" name="bankinstitut" placeholder="Bankinstitut" required/> 
		<input type="password" name="passwort" placeholder="Wählen Sie ihr Passwort" required/> 
		<!--  hier fehlt noch funktionalität für wirklichen password check - dürfen wir JS nutzen? -->
		<input type="password" placeholder="Passwort wiederholen" required/>
		
		<br/>

		<label for="bedingungen">Ich akzeptiere die Geschäftsbedingungen</label>
		<input type="checkbox" name="bedingungen" required/> 
		<br/>
		<label for="newsletter">Ich möchte den Newsletter erhalten (optional)</label>
		<input type="checkbox" name="newsletter" />
		
		<br/>
		<input type="submit" value="Registrieren"/>
	</form>
</body>
</html>