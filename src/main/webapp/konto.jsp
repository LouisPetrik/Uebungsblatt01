<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<jsp:include page="head.jsp" />
<body>
	<jsp:include page="navigation.jsp" />
	<p>Hallo! ${ vorname } ${ nachname }</p>
	<p>Ihre email über session: ${ sessionScope['bank.email'] }</p>
	
	
	<p>Hier können Sie ein Konto anlegen:</p>
	
	<p>Liste der konten in der session ${ sessionScope['bank.kontennamenListe'] }</p>
	
	
	<form method="POST" action="KontoServlet">
		<input type="text" name="kontoname" placeholder="Kontoname" /> 
		<input type="submit" value="Konto anlegen" /> 
	</form>
	
	<form method="POST" action="LogoutServlet">
		<input type="submit" value="Ausloggen" />
		<!--  Das Form sendet ebenfalls die email des nutzers, der sich abmelden will, damit 
		er darüber identifiziert und abgemeldet werden kann. Die Email deshalb, weil sie im Gegensatz 
		zum Namen eines Kunden einzigartig sein MUSS -->
		<input type="hidden" name="email" value="${ email }" />
	</form>
	
	<h2>Kontostand.xls Datei hochladen</h2>

	<jsp:include page="footer.jsp" />
</body>
</html>