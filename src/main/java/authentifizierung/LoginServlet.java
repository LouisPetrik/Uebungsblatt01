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
		// TODO Auto-generated method stub
		String email = request.getParameter("email"); 
		String passwort = request.getParameter("passwort");
		
		System.out.println(email); 
		System.out.println(passwort); 
		
		// aus der session die kunden in eine arrayliste laden: 
		HttpSession session = request.getSession(); 
		// Das scheint irgendwie ekelig zu sein, klappt aber. 
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste"); 
		
		
		System.out.println(kundenliste.get(0)); 
		
		// Falls auf den zustand noch mehrfach zugegriffen werden muss. 
		Boolean eingeloggt = false; 

		
		// checken, ob nutzer wirklich registiert ist: 
		for (Kunde kunde : kundenliste) {
			if (kunde.email.equals(email) && kunde.passwort.equals(passwort)) {
				eingeloggt = true; 
					
				// Vor und Nachname des erfolgreich eingeloggten Kunden zur Begrüßung übergeben 
				request.setAttribute("vorname", kunde.vorname);
				request.setAttribute("nachname", kunde.nachname); 
				
				request.getRequestDispatcher("konto.jsp").forward(request, response);
			} 
		}
		
		if (!eingeloggt) {
			System.out.println("Email oder passwort ist falsch"); 
			request.setAttribute("fehlertyp", "E-Mail oder Passwort ist falsch"); 
			
			request.getRequestDispatcher("login.jsp").forward(request, response); 
		} 
		
		
		doGet(request, response);
	}

}
