package banking;

import java.util.UUID;
import java.util.ArrayList;
import java.util.Random;

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
    
    public void loadCSV(String path) {
    	System.out.println("loadCSV!");
    	
    	txs.add(new Transaktion(path, "zu_id", "FOLGELASTSCHRIFT", "amazon", "zu_iban", 86.86f, "EUR"));
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
