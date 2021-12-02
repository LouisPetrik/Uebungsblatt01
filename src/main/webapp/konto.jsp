<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<jsp:include page="head.jsp" />
<body>
	<jsp:include page="navigation.jsp" />
	
	<div class="container">
		<h1>Hallo ${ sessionScope['bank.vorname'] } ${ sessionScope['bank.nachname'] }</h1>
		
		<div class="card">
		  <div class="card-body">
		   	Sie sind angemeldet als: ${ sessionScope['bank.email'] }
		  </div>
		</div>
		
		<h2>Hier können sie weitere Konten anlegen:</h2>
		
		<p>Liste ihrer Konten bei uns: </p>
		<ul>
			${ sessionScope['bank.kontennamenListe'] }
		</ul>
		
		
		<form method="POST" action="KontoServlet">
			<input type="text" name="kontoname" placeholder="Kontoname" /> 
			<input type="submit" value="Konto anlegen" /> 
		</form>
		
		<form method="POST" action="LogoutServlet">
			<input type="submit" value="Ausloggen" />
			<!--  Das Form sendet ebenfalls die email des nutzers, der sich abmelden will, damit 
			er darüber identifiziert und abgemeldet werden kann. Die Email deshalb, weil sie im Gegensatz 
			zum Namen eines Kunden einzigartig sein MUSS -->
			<input type="hidden" name="email" value="${ sessionScope['bank.email'] }" />
		</form>
		
		<h2>Kontostand.xls Datei hochladen</h2>
	</div>
	
	

	<jsp:include page="footer.jsp" />
</body>
</html>