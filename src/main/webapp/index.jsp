<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

	<jsp:include page="head.jsp" />
<body>
	<jsp:include page="navigation.jsp" />
	
	<!--  falls der user noch nicht eingeloggt ist -->
	
	<div class="container">
		<h1>Hallo ${ sessionScope.kunde.getVorname() } ${ sessionScope.kunde.getNachname() }</h1>
		<div class="card">
		  <div class="card-body">
		   	Falls Sie bereits angemeldet sind, erscheint ihre E-Mail hier: 
		  </div>
		</div>
		
		<div class="alert alert-success" role="alert">
			${ sessionScope.kunde.getEmail() }
		</div>

		<div class="card">
		  <div class="card-body">
		    Bitte <a href="login.jsp">melden Sie sich an</a>, oder <a href="registrierung.jsp">registrieren Sie sich</a>, falls Sie noch kein Konto bei uns besitzen.
		  </div>
		</div>
	</div>

	<jsp:include page="footer.jsp" />
</body>
</html>