package authentifizierung;


/**
 * Dieses Servlet ist dafür da, damit sich bestehende Nutzer über die login.jsp anmelden können. 
 * 
 */
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.Konto;
import banking.Kunde; 


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        System.out.println("LoginServlet wird genutzt!"); 
    }
    

    /* 
     * 	ACHTUNG: DIESE METHODE IST EXPERIMENTEL und soll verhindern, dass beim neuladen der LoginServlet Seite 
     * die Daten verloren gehen - was leider ziemlich nervig ist. 
     */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email"); 
		String passwort = request.getParameter("passwort");
		
		System.out.println(email); 
		System.out.println(passwort); 
		
		// aus der session die kunden in eine arrayliste laden: 
		HttpSession session = request.getSession(); 
		// Das scheint irgendwie ekelig zu sein, klappt aber. 
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste"); 
		
		
		// Falls auf den zustand noch mehrfach zugegriffen werden muss. 
		Boolean eingeloggt = false; 

		
		// Falls die Kundenliste leer ist, und somit kein Benutzer registiert ist, 
		// können wir uns das natürlich alles sparen. 
		if (!(kundenliste == null)) {
			// ab hier alles für den Fall, dass es überhaupt Kunden in der DB gibt
			
			/*
			 * Testen, ob der Nutzer der sich anmelden will, wirklich registiert ist. 
			 * Dabei nutzen wir die ArrayListe, die aus der Session geladen wurde und als DB dient. 
			 */
			for (Kunde kunde : kundenliste) {
				if (kunde.email.equals(email) && kunde.passwort.equals(passwort)) {
					eingeloggt = true; 
						
					// Vor und Nachname des erfolgreich eingeloggten Kunden zur Begrüßung übergeben 
					request.setAttribute("vorname", kunde.vorname);
					request.setAttribute("nachname", kunde.nachname); 
					request.setAttribute("email", kunde.email); 
					
					session.setAttribute("bank.vorname", kunde.vorname); 
					session.setAttribute("bank.nachname", kunde.nachname); 
					session.setAttribute("email", kunde.email); 
					
					// Session für den angemeldeten Kunden anlegen. 
					// setzt die email des aktuell angemeldeten Kunden als Session wert
					// Die email weil vor und nachname nicht einzigartig sein müssen. 
					session.setAttribute("bank.email", kunde.email); 
					
					
					// übergeben einer Liste an Konten zur Kontrolle: 
					// dafür nur eine Liste von Namen der Konten anlegen: 
					ArrayList<String> kontennamenListe = new ArrayList<String>(); 
					
					
					// auch hier muss noch die Logik für das Erstellen einer HTML Liste an Kontennamen 
					// durchgeführt werden, da man anfangs von LoginServlet direkt auf die konto.jsp kommt. 
					// Dieses Servlet muss also eine liste bereitstellen, genauso wie die KontoServlet, 
					// die nach jedem hinzugefügten Konto aufgerufen wird um eine neue HTML Liste der Konten 
					// zu erstellen
			
					// Falls die Liste der Konten des Kundens NICHT leer ist
					if (!kunde.kontenliste.isEmpty()) {
						//  Der Strinbuilder für das konstruieren der HTML Liste der Kontennamen des Kunden
						StringBuilder sb = new StringBuilder(); 
						
						for (Konto konto : kunde.kontenliste) {
							// Der HTML Liste ein Listeneintrag mit namen des Kontos hinzufügen 
							sb.append("<li>" + konto.kontoname + "</li>"); 
							
							// Hinzufügen der Konten in die Liste die zur Kontrolle dient 
							kontennamenListe.add(konto.kontoname); 
						}
						
						String kontennamenListeHTML = sb.toString(); 
						
						session.setAttribute("bank.kontennamenListe", kontennamenListeHTML); 
					} else {
						System.out.println("Beim Einloggen: Der Kunde hat noch keine Konten."); 
						// Falls der Kunde noch keine Konten hat: 
						session.setAttribute("bank.kontennamenListe", "<b>Sie haben bisher keine Konten bei uns</b>"); 
					}
					
			
					// nur zur überprüfung
					System.out.println("Liste von Konten: " + kontennamenListe); 
					
					request.getRequestDispatcher("konto.jsp").forward(request, response);
				} 
			}
			
			if (!eingeloggt) {
				System.out.println("Email oder passwort ist falsch"); 
				request.setAttribute("fehlertyp", "E-Mail oder Passwort ist falsch"); 
				
				request.getRequestDispatcher("login.jsp").forward(request, response); 
			} 
			
		// Falls es überhaupt keine Kunden gibt
		} else {
			System.out.println("Keine Kudnen registriert"); 
			request.setAttribute("fehlertyp", "Es gibt keine registrierten Kunden"); 
			
			request.getRequestDispatcher("login.jsp").forward(request, response); 
		}
		

	}

}
