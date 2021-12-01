package banking;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;


import banking.Konto; 
import banking.Kunde; 




/**
 * Servlet implementation class KontoServlet
 */
@WebServlet("/KontoServlet")
public class KontoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KontoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
		 */

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(); 
		
		// Das scheint irgendwie ekelig zu sein, klappt aber. 
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste"); 
		
		// Der gewünschte Kontenname des Users aus der konto.jsp 
		String kontoname = request.getParameter("kontoname"); 
		// Die Email des aktuell angemeldten Kunden
		String kundenEmail = (String) session.getAttribute("bank.email"); 
		
		
		System.out.println("User will Konto " + kontoname); 
		System.out.println("Der user ist: " + kundenEmail); 
		
		// Das Kunden objekt des users, der aktuell angemeldet ist finden, um ein neues konto seiner 
		// Liste an Konten hinzuzufügen: 
		for (Kunde kunde : kundenliste) {
			if (kunde.email.equals(kundenEmail)) {
				System.out.println("Es handelt sich um Kunden " + kunde.vorname); 
				kunde.kontenliste.add(new Konto(kontoname, kundenEmail, 22)); 
			}
		}
		
		// jetzt muss noch die session geupdated werden mit dem konto, was hinzugefügt wurde. 
		session.setAttribute("bank.kundenliste", kundenliste); 
		
		request.getRequestDispatcher("konto.jsp").forward(request, response); 
	
		//doGet(request, response);
	}

}
