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

    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Hier wird abgefangen, falls jemand nachdem Aufruf der KontoServlet URL die Seite neulädt, also 
		// einen GET-Request an /KontoServlet durchführt - in diesem Fall soll das direkt weiter an die konto.jsp, die alle 
		// Inhalte darstellt. 
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
    
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Damit wir die Session hier auslesen und bearbeiten können 
		HttpSession session = request.getSession(); 
		
		// Das scheint irgendwie ekelig zu sein, klappt aber. 
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste");

		

		// Der gewünschte Kontenname des Users aus der konto.jsp 
		String kontoname = request.getParameter("kontoname"); 
		
		// Die Instanz des aktuellen Kunden samt email, vorname etc. 
		Kunde kundenInstanz = (Kunde) session.getAttribute("kunde"); 
		
		
		System.out.println("User will Konto " + kontoname); 
		System.out.println("Der user ist: " + kundenInstanz.getEmail()); 
		
	
		// Der Strinbuilder für das konstruieren der HTML Liste der Kontennamen des Kunden
		StringBuilder sb = new StringBuilder(); 
		
		
		// Das gewünschte Konto der Kontoliste des Users adden: 
		kundenInstanz.kontenliste.add(new Konto(kontoname, kundenInstanz.getEmail(), 22)); 
		
		for (Konto konto : kundenInstanz.kontenliste) {
			// fügt der HTML Liste ein Item hinzu 
			sb.append("<li>" + konto.kontoname); 
		}
		
		
		
		// Die fertige HTML liste die alle Namen von Konten enthält, die der User hat
		String kontennamenListeHTML = sb.toString(); 
		
		
		// what the fuck ist das? 
		session.setAttribute("bank.kundenliste", kundenliste); 
		
		// Die fertige HTML Liste der Konten in der Session speichern, damit konkto.jsp darauf zugreifen kann. 
		session.setAttribute("bank.kontennamenListe", kontennamenListeHTML); 
		
		

		// Wichtig: Dies included nur den Inhalt der JSP, das heißt sämtliche Attribute die 
		// beim Redirect zur konto.jsp anfänglich übergeben werden, sind verloren. Daher wurden 
		// viele Daten in der Session gespeichert. 
		request.getRequestDispatcher("konto.jsp").include(request, response); 
	
		//doGet(request, response);
	}

}
