<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!--  JSP datei für die navgationsleiste in bootstrap --> 

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">Online Banking</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="index.jsp">Start</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="login.jsp">Login</a>
        </li>
        <li class="nav-item">
        	<a class="nav-link" href="registrierung.jsp">Registrierung</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="konto.jsp">Konto</a>
        </li>
      </ul>
      <span class="navbar-text">
       	Eingeloggt als: <b>${ sessionScope['bank.email'] }</b>
      </span>
    </div>
  </div>
</nav>