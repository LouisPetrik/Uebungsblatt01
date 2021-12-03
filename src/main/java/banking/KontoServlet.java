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
		// Hier wird abgefangen, falls jemand nachdem Aufruf der KontoServlet URL die Seite neul�dt, also 
		// einen GET-Request an /KontoServlet durchf�hrt - in diesem Fall soll das direkt weiter an die konto.jsp, die alle 
		// Inhalte darstellt. 
		request.getRequestDispatcher("konto.jsp").forward(request, response);
	}
    
    
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession(); 
		
		// Das scheint irgendwie ekelig zu sein, klappt aber. 
		ArrayList<Kunde> kundenliste = (ArrayList<Kunde>) session.getAttribute("bank.kundenliste");
		
		ArrayList<String> kontennamenListe = new ArrayList<String>(); 
		
	
		
		// Der gew�nschte Kontenname des Users aus der konto.jsp 
		String kontoname = request.getParameter("kontoname"); 
		// Die Instanz des aktuellen Kunden samt email, vorname etc. 
		Kunde kundenInstanz = (Kunde) session.getAttribute("kunde"); 
		
		
		System.out.println("User will Konto " + kontoname); 
		System.out.println("Der user ist: " + kundenInstanz.getEmail()); 
		
		
	
		// Der Strinbuilder f�r das konstruieren der HTML Liste der Kontennamen des Kunden
		StringBuilder sb = new StringBuilder(); 
		
		// Das Kunden objekt des users, der aktuell angemeldet ist finden, um ein neues konto seiner 
		// Liste an Konten hinzuzuf�gen: 
		for (Kunde kunde : kundenliste) {
			if (kunde.email.equals(kundenInstanz.getEmail())) {
				System.out.println("Es handelt sich um Kunden " + kunde.vorname); 
				kunde.kontenliste.add(new Konto(kontoname, kundenInstanz.getEmail(), 22)); 
				
				for (Konto konto : kunde.kontenliste) {
					// f�gt der HTML Liste ein Item hinzu 
					sb.append("<li>" + konto.kontoname); 
					
					// f�ge dem Kundenobjekt das neu erstellte Konto hinzu
					kontennamenListe.add(konto.kontoname); 
				}
				
				System.out.println("Die Liste von Konten " + kunde.kontenliste.get(0)); 
			}
		}
		
		
		
		
		// Die fertige HTML liste die alle Namen von Konten enth�lt, die der User hat
		String kontennamenListeHTML = sb.toString(); 
		
		
		// jetzt muss noch die session geupdated werden mit dem konto, was hinzugef�gt wurde. 
		
		// what the fuck ist das? 
		session.setAttribute("bank.kundenliste", kundenliste); 
		
		
		session.setAttribute("bank.kontennamenListe", kontennamenListeHTML); 
		
		// Hier muss irgendwie ein redirect auf LoginServlet GET klappen 
		
		
		System.out.println("Neue kontenliste: "  + kontennamenListeHTML); 

		// Wichtig: Dies included nur den Inhalt der JSP, das hei�t s�mtliche Attribute die 
		// beim Redirect zur konto.jsp anf�nglich �bergeben werden, sind verloren. Daher wurden 
		// viele Daten in der Session gespeichert. 
		request.getRequestDispatcher("konto.jsp").include(request, response); 
	
		//doGet(request, response);
	}

}
