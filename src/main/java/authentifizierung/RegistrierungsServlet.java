package authentifizierung;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import banking.Kunde;
import java.util.ArrayList; 
import javax.servlet.http.HttpSession;
/**
 * Servlet implementation class RegistrierungsServlet
 */


@WebServlet("/RegistrierungsServlet")
public class RegistrierungsServlet extends HttpServlet {
	
	/* Speichert die Liste aller registrierten Kunden. 
	 * Eventuell ist ein normales Array hinreichend, aber mal sehen. 
	 * sollte vielleicht in ein anderes Scope, aber f�r jetzt passt das.
	 * Diese Liste soll noch in der Session gespeichert werden. 
	 */ 
	ArrayList<Kunde> kundenliste = new ArrayList<Kunde>(); 
	
	
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrierungsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}	 */
	
	/**
	 * 	Eine Funktion die an die registrierung.jsp zur�ckgibt, dass bei der Registrierung ein 
	 * Fehler aufgetreten ist - z. B. Passwort best�tigung falsch, Mail bereits vergeben, oder nicht
	 * alle Pflichtfehler ausgef�llt. 
	 * @param fehlertyp ist um welches Problem es sich genau handelt. 
	 * @throws IOException 
	 * @throws ServletException 
	 * Die funktion muss auhc noch sicherstellen, dass alle korrekten Fehler wird angezeigt werden. 
	 * Daf�r kann man die templating language nutzen. 
	 */
	public void sendeFehlerMeldung(String fehlertyp, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("sendeFehlerFunktion gecalled"); 
		request.setAttribute("fehlertyp", fehlertyp);
		request.getRequestDispatcher("registrierung.jsp").forward(request, response); 
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Die Logik f�r ein einzelnes Kundenobjekt findet sich in der entsprechenden Datei Kunde.java
		
		System.out.println("POST ist angekommen "); 
		
		// S�mtliche Daten des registrierenden Kundens in einzelnen Variablen speichern. 
		// Bis auf das doppelte Passwort und ob der Kunde die Gesch�ftsbedingungen akzeptiert, 
		// wird alles gespeichert. 
		String vorname = request.getParameter("vorname"); 
		String nachname = request.getParameter("nachname"); 
		Integer alter = Integer.parseInt(request.getParameter("alter")); 
		String email = request.getParameter("email"); 
		String bankinstitut = request.getParameter("bankinstitut"); 
		
		// Sowohl das gew�nschte Passwort, als auch die eingegeben Best�tigung wird gespeichert
		String passwort = request.getParameter("passwort"); 
		String passwortBest�tigung = request.getParameter("passwortBest�tigung"); 
		
		Boolean newsletter = false; 
		
		// Zum registrieren ob irgendwo ein Fehler aufgetreten ist - falls ja wird �ber diese
		// var entschieden, dass der standard redirect zur index.jsp nicht durchgef�hrt wird. 
		// Ist nicht sch�n, alles andere f�hrt zu einem Fehler atm. 
		Boolean fehlerAufgetreten = false; 
		
		
		// Da ob der Kunde den Newsletter haben m�chte in einem Bool gespeichert werden kann, 
		// das Form aber einen String sendet wird ob ja oder nein hier zu einem Boolean verwandelt. 
		if (request.getParameter("newsletter") != null) {
			newsletter = true; 
		} 
		
		//  Testen, ob der das gew�nschte Passwort gleich der Best�tigung ist. 
		if (passwort.equals(passwortBest�tigung)) {
			System.out.println("Passw�rter sind gleich!"); 
		} else {
			// aufrufen von funktion die fehlermelduing gibt, wenn solch ein Fehler an 
			// mehreren stellen notwendig ist 
			System.out.println("Passw�rt sind nicht gleich!"); 
			fehlerAufgetreten = true; 
			sendeFehlerMeldung("Passw�rter sind nicht gleich!", request, response); 
		}
		
		// Testen, ob die Email bereits im System registriert ist: 
		for (Kunde kunde : kundenliste) {
			if (kunde.email.equals(email)) {
				System.out.println("Mail wurde bereits benutzt!"); 
				fehlerAufgetreten = true; 
				sendeFehlerMeldung("Diese Mail wird bereits verwendet!", request, response); 
			} else {
				System.out.println("Mail ist noch verf�gar"); 
			}
		}
		
		
		System.out.println(vorname); 
		System.out.println(nachname); 
		System.out.println(alter); 
		System.out.println(email); 
		System.out.println(bankinstitut); 
		System.out.println(passwort); 
		System.out.println(newsletter); 
		System.out.println(passwortBest�tigung);
		
		

		if(!fehlerAufgetreten) {
			// Erstellen des Kundenobjekts: 
			Kunde kunde = new Kunde(vorname, nachname, alter, email, bankinstitut, passwort, newsletter); 

			kundenliste.add(kunde); 
			
			HttpSession session = request.getSession(); 
			
			session.setAttribute("bank.kundenliste", kundenliste); 
			
			System.out.println("Kundenliste ist da " + kundenliste.get(0)); 
			
			// Nutzer wieder an index.jsp weiterleiten: 
			// Dies sollte nur geschehen, wenn die Registrierung faktisch erfolgreich ist 
			// Die notwendigen Bedingugen daf�r sind noch nicht ganz fertig - z. B. confirm passwort. 
			// Dieser redirect muss noch verschachtelt werden, damit er nicht direkt nach dem redirect 
			// im Falle eines Fehlers durchgef�hrt wird. 
			request.getRequestDispatcher("index.jsp").forward(request, response); 
		}
		
		
		//doGet(request, response);
	}

}
