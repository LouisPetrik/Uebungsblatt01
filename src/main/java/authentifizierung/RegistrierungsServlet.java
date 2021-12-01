package authentifizierung;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrierungsServlet
 */

@WebServlet("/RegistrierungsServlet")
public class RegistrierungsServlet extends HttpServlet {
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
		String vorname = request.getParameter("vorname"); 
		String nachname = request.getParameter("nachname"); 
		Integer alter = Integer.parseInt(request.getParameter("alter")); 
		String email = request.getParameter("email"); 
		String bankinstitut = request.getParameter("bankinstitut"); 
		String passwort = request.getParameter("passwort"); 
		Boolean newsletter = false; 
		
		// Ob der user newsletter haben will oder nicht zu einem Bool machen 
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

		
		doGet(request, response);
	}

}
