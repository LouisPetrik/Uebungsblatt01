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
		// abfangen, wenn jemand die Konto.jsp neuladen will, weil die URL dazu tatsächlih 
		// die LoginServlet ist, und nicht /konto.jsp
	
		
		HttpSession session = request.getSession(); 
		// Das scheint irgendwie ekelig zu sein, klappt aber. 
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste"); 
		
		for (Kunde kunde : kundenliste) {
		
				// Vor und Nachname des erfolgreich eingeloggten Kunden zur Begrüßung übergeben 
				request.setAttribute("vorname", kunde.vorname);
				request.setAttribute("nachname", kunde.nachname); 
				request.setAttribute("email", kunde.email); 
				
				// Session für den angemeldeten Kunden anlegen. 
				// setzt die email des aktuell angemeldeten Kunden als Session wert
				// Die email weil vor und nachname nicht einzigartig sein müssen. 
				session.setAttribute("bank.email", kunde.email); 
				
				
				// übergeben einer Liste an Konten zur Kontrolle: 
				// dafür nur eine Liste von Namen der Konten anlegen: 
				ArrayList<String> kontennamenListe = new ArrayList<String>(); 
				
				for (Konto konto : kunde.kontenliste) {
					kontennamenListe.add(konto.kontoname); 
				}
				
				session.setAttribute("bank.kontennamenListe", kontennamenListe); 
				
				System.out.println("Liste von Konten: " + kontennamenListe); 
				
				request.setAttribute("kontennamenListe", kontennamenListe); 
				
				request.getRequestDispatcher("konto.jsp").forward(request, response);
			} 
		}


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

		 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
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
			
			// checken, ob nutzer wirklich registiert ist: 
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
					
					for (Konto konto : kunde.kontenliste) {
						kontennamenListe.add(konto.kontoname); 
					}
					
					session.setAttribute("bank.kontennamenListe", kontennamenListe); 
					
					System.out.println("Liste von Konten: " + kontennamenListe); 
					
					request.setAttribute("kontennamenListe", kontennamenListe); 
					
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
