package banking;

public class Kunde {
	public String vorname; 
	public String nachname; 
	public Integer alter; 
	public String email; 
	public String bankinstitut; 
	public String passwort; 
	public Boolean newsletter; 
	
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
