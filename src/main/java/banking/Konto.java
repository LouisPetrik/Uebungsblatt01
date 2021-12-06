package banking;

import java.util.UUID;

import java.util.ArrayList;
import java.util.Scanner;

import banking.Transaktion;

public class Konto {
    public final String name;
    public final String email;

    private final String ID;
    private ArrayList<Transaktion> txs = new ArrayList<>();

    public Konto(String name, String email) {
        this.name = name;
        this.email = email;

        // statt über den Konstrukor einen Wert für die ID entgegenzunehmen wird bei
        // jeder erstellen Instanz eines Kontos eine einzigartige ID generiert.
        this.ID = UUID.randomUUID().toString();
    }
    
    public void loadCSV(String csvFile) {
    	System.out.println("loadCSV!");
    	
        	Scanner scanner = new Scanner(csvFile);
        	
        	// um den header zu überspringen
        	if (scanner.hasNextLine()) {        		
        		scanner.nextLine();
        	}
        	
        	if (scanner.hasNextLine()) {        		
        		scanner.nextLine();
        	}
        	
        	while (scanner.hasNextLine()) {
        		String txString = scanner.nextLine();
        		
        		String fields[] = txString.split(",");
        		
        		// überprüfen ob "," in einem String vorkommen
        		
        		
        		if (fields.length <= 15) {
        			System.out.println("CSV hat keine gültiges Format (sollte mind./genau 16 Felder haben)!");
        			return;
        		}
        		
        		System.out.println(fields[0] + " | " + fields[5] + " | " + fields[3] + " | " + fields[4] + " | " + fields[12] + " | " + fields[14] + " | " + fields[15]);
        		
        		txs.add(new Transaktion(fields[0], fields[5], fields[3], fields[4], fields[12], Float.parseFloat(fields[14]), fields[15]));
        	}
        	
        	scanner.close();
    }
    
    public boolean hasTxs() {
    	return !txs.isEmpty();
    }
    
    public String txsAsHTML() {
    	StringBuilder sb = new StringBuilder();
    	
    	for (Transaktion tx : txs) {
    		sb.append(tx.asHTML() + "<br/>");
    	}
    	
    	return sb.toString();
    }
}
