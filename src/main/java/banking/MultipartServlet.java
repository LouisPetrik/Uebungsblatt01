package banking;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import banking.Konto; 
import banking.Kunde; 

@WebServlet("/MultipartServlet")
@MultipartConfig
public class MultipartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public MultipartServlet() {
        super();
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Kunde kunde = (Kunde) session.getAttribute("kunde");
        System.out.println("Der user ist: " + kunde.getEmail());

    	if (ServletFileUpload.isMultipartContent(request)) {
        	final Part filePart = request.getPart("csvFile");
        	
        	InputStream filecontent = filePart.getInputStream();
        	String csvFile = new String(filecontent.readAllBytes());
        	String selectedKonto = request.getParameter("selectedKonto");
        	
            System.out.println("selectedKonto " + selectedKonto);
            if (selectedKonto != null) {
                int konto_idx = Integer.parseInt(selectedKonto);
  
                System.out.println("csvFile '" + csvFile + "'");
                if (csvFile != "") {	// input type="file" has always default value "" 
                    						// so instead of null it would return ""
                    	
                	// sollte immer true sein aber zur sicherheit
                	if (konto_idx >= 0 && konto_idx < kunde.kontenliste.size()) {
                		kunde.kontenliste.get(konto_idx).loadCSV(csvFile);
                    }	              		
                }
                    
                StringBuilder sb = new StringBuilder();
                sb.append("<div class=\"card\"> <div class=\"card-body\">");
                    
                if (kunde.kontenliste.get(konto_idx).hasTxs()) {
                	sb.append(kunde.kontenliste.get(konto_idx).txsAsHTML());
                } else {
                	sb.append("<b>keine Transaktionen (laden sie eine CSV hoch)</b>");
                }    
                    
                sb.append("</div></div>");
                    
                session.setAttribute("showKonto", sb.toString());
            }
    	}

        request.getRequestDispatcher("konto.jsp").include(request, response);
    }
}

