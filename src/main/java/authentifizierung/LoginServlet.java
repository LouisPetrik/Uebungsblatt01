package authentifizierung;


//darmian ist ein fascho 


/**
 * Dieses Servlet ist dafür da, damit sich bestehende Nutzer über die login.jsp anmelden können. 
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
     *  ACHTUNG: DIESE METHODE IST EXPERIMENTEL und soll verhindern, dass beim neuladen der LoginServlet Seite 
     * die Daten verloren gehen - was leider ziemlich nervig ist. 
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("konto.jsp").forward(request, response);
    }

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
        if (kundenliste != null) {
            // ab hier alles für den Fall, dass es überhaupt Kunden in der DB gibt

            /*
             * Testen, ob der Nutzer der sich anmelden will, wirklich registiert ist. 
             * Dabei nutzen wir die ArrayListe, die aus der Session geladen wurde und als DB dient. 
             */
            for (Kunde kunde : kundenliste) {
                if (kunde.email.equals(email) && kunde.passwort.equals(passwort)) {
                    eingeloggt = true; 

                    session.setAttribute("kunde", kunde); 

                    request.getRequestDispatcher("konto.jsp").forward(request, response);
                } 
            }

            if (!eingeloggt) {
                System.out.println("Email oder passwort ist falsch"); 
                request.setAttribute("fehlertyp", "E-Mail oder Passwort ist falsch"); 

                request.getRequestDispatcher("login.jsp").forward(request, response); 
            }
        } else {
            System.out.println("Keine Kudnen registriert"); 
            request.setAttribute("fehlertyp", "Es gibt keine registrierten Kunden"); 

            request.getRequestDispatcher("login.jsp").forward(request, response); 
        }
    }
}
