package banking;

import banking.Konto; 
import java.util.ArrayList;

public class Kunde {
	public String vorname; 
	public String nachname; 
	public Integer alter; 
	public String email; 
	public String bankinstitut; 
	public String passwort; 
	public Boolean newsletter; 
	
	// Ein nutzer kann mehrere Konten haben, die als Liste von Konten-Objekten gespeichert werden. 
	public ArrayList<Konto> kontenliste = new ArrayList<>();
	
	
	public Kunde(String vorname, String nachname, Integer alter, String email, String bankinstitut, String passwort, Boolean newsletter) {
		this.vorname = vorname; 
		this.nachname = nachname; 
		this.alter = alter; 
		this.email = email; 
		this.bankinstitut = bankinstitut; 
		this.passwort = passwort; 
		this.newsletter = newsletter; 
	}
}
