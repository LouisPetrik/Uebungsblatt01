package banking;

import java.util.UUID;

public class Konto {
    public String kontoname;
    public String besitzerEmail;
    public String ID;

    public Konto(String kontoname, String besitzerEmail) {
        this.kontoname = kontoname;
        this.besitzerEmail = besitzerEmail;
        // statt über den Konstrukor einen Wert für die ID entgegenzunehmen wird bei
        // jeder erstellen Instanz eines Kontos eine einzigartige ID generiert.
        this.ID = UUID.randomUUID().toString();
    }
}
