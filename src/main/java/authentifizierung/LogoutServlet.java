package authentifizierung;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import banking.Kunde;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet("/LogoutServlet")
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogoutServlet() {
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
	
		// bisher wird hier die komplette User-Datenbank gel�scht. Aber nur die Session vom User soll gel�scht werden. 
		System.out.println("User m�chte sich abmelden"); 
		
		
		HttpSession session = request.getSession(); 
		// redirecten auf die logout.jsp wo der Kunde noch verabschiedet wird. 
		request.getRequestDispatcher("logout.jsp").forward(request, response); 
		
		// Damit wird aus der Session nur das Kundenobjekt, also der aktuell eingeloggte Kunde entfernt 
		// Die gesamte Kundendatenbank die in der Sessino die registrierten Kunden verzeichnet, bleibt erhalten. 
		// Der Kunde, der sich ausloggen will bleibt also registiert und kann sich wieder anmelden. 
		session.removeAttribute("kunde"); 			
		
	}

}
