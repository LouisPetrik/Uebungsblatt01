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
	 * sollte vielleicht in ein anderes Scope, aber für jetzt passt das.
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
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Die Logik für ein einzelnes Kundenobjekt findet sich in der entsprechenden Datei Kunde.java
		
		System.out.println("POST ist angekommen "); 
		
		// Sämtliche Daten des registrierenden Kundens in einzelnen Variablen speichern. 
		// Bis auf das doppelte Passwort und ob der Kunde die Geschäftsbedingungen akzeptiert, 
		// wird alles gespeichert. 
		String vorname = request.getParameter("vorname"); 
		String nachname = request.getParameter("nachname"); 
		Integer alter = Integer.parseInt(request.getParameter("alter")); 
		String email = request.getParameter("email"); 
		String bankinstitut = request.getParameter("bankinstitut"); 
		String passwort = request.getParameter("passwort"); 
		Boolean newsletter = false; 
		
		// Da ob der Kunde den Newsletter haben möchte in einem Bool gespeichert werden kann, 
		// das Form aber einen String sendet wird ob ja oder nein hier zu einem Boolean verwandelt. 
		if (request.getParameter("newsletter") != null) {
			newsletter = true; 
		} 
		
		
		System.out.println(vorname); 
		System.out.println(nachname); 
		System.out.println(alter); 
		System.out.println(email); 
		System.out.println(bankinstitut); 
		System.out.println(passwort); 
		System.out.println(newsletter); 
		
		// Erstellen des Kundenobjekts: 
		Kunde kunde = new Kunde(vorname, nachname, alter, email, bankinstitut, passwort, newsletter); 
		

		kundenliste.add(kunde); 
		
		HttpSession session = request.getSession(); 
		
		session.setAttribute("kundenliste", kundenliste); 
		
		System.out.println(kundenliste.get(0)); 
		
		// Nutzer wieder an index.jsp weiterleiten: 
		// Dies sollte nur geschehen, wenn die Registrierung faktisch erfolgreich ist 
		// Die notwendigen Bedingugen dafür sind noch nicht ganz fertig - z. B. confirm passwort. 
		request.getRequestDispatcher("index.jsp").forward(request, response); 
		
		doGet(request, response);
	}

}
