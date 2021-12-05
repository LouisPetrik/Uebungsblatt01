<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />
    <body>
        <jsp:include page="navigation.jsp" />

        Bis zum nächsten Mal, ${ kunde.getVorname() } ${ kunde.getNachname() }!

        <jsp:include page="footer.jsp" />
    </body>
</html>
