<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp" />
    <body>
        <jsp:include page="navigation.jsp" />

        <div class="container">
            <h1>Sie haben noch kein Konto bei uns?</h1>

            <h2>Dann ist es an der Zeit das zu ändern</h2>

            <form method="POST" action="RegistrierungsServlet">
                <div class="mb-3">
                    <input type="text" name="vorname" placeholder="Vorname" class="form-control" required>
                </div>

                <div class="mb-3">
                    <input type="text" name="nachname" placeholder="Nachname" class="form-control" required>
                </div>

                <div class="mb-3">
                    <input type="number" name="alter" placeholder="Alter" class="form-control"  aria-describedby="alterHinweis" required>
                    <div id="alterHinweis" class="form-text">Sie müssen volljährig sein.</div>
                </div>

                <div class="mb-3">
                    <input type="email" name="email" placeholder="E-Mail Adresse" class="form-control"  aria-describedby="emailHinweis" required>
                    <div id="emailHinweis" class="form-text">Ihre E-Mail wird nicht an Dritte weitergegeben.</div>
                </div>

                <div class="mb-3">
                    <input type="text" name="bankinstitut" placeholder="Bankinstitut" class="form-control" required>
                </div>

                <div class="mb-3">
                    <input type="password" name="passwort" placeholder="Wählen Sie ein Passwort" class="form-control" required>
                </div>

                <div class="mb-3">
                    <input type="password" name="passwortBestaetigung" placeholder="Passwort wiederholen" class="form-control" required>
                </div>

                <div class="mb-3 form-check">
                    <input type="checkbox" name="bedingungen" class="form-check-input " id="check1" required>
                    <label class="form-check-label" for="bedingungen">Ich akzeptiere die Geschäftsbedingungen</label>
                </div>

                <div class="mb-3 form-check">
                    <input type="checkbox" name="newsletter" class="form-check-input " id="check2">
                    <label class="form-check-label" for="newsletter">Ich möchte den Newsletter erhalten (optional)</label>
                </div>

                <button type="submit" class="btn btn-primary">Registrieren</button>
            </form>

            <p class="text-danger">${ fehlertyp }</p>
        </div>

        <jsp:include page="footer.jsp" />
    </body>
</html>
