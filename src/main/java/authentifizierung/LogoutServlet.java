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
		
		System.out.println("User m�chte sich abmelden"); 
		
		String email = request.getParameter("email"); 
		
		HttpSession session = request.getSession(); 
		
		String emailSession = (String) session.getAttribute("email"); 
		
		System.out.println("Email in der Session: " + emailSession); 
		

		
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste"); 
		
		for (Kunde kunde : kundenliste) {
			if (kunde.email.equals(email)) {
				
			}
		}
		
		// muss noch gegen richtigen vor und nachnamen getauscht werden. 
		request.setAttribute("email", emailSession); 
		request.getRequestDispatcher("logout.jsp").forward(request, response); 
		
		// session invalidieren 
		
		session.invalidate(); 
				
		doGet(request, response);
	}

}
