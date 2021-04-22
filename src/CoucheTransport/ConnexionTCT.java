package CoucheTransport;

public class ConnexionTCT {
    //private Etat etat;
    private final String ETAT1 ="EN_ATTENTE_DE_COMFIRMATION_ETABLISSEMENT";
    private final String ETAT2 ="CONNEXION_ETABLIE";
    private int Id;

    public int getId() {
        return Id;
    }

    public String getETAT1() {
        return ETAT1;
    }

    public String getETAT2() {
        return ETAT2;
    }

   public void setId(int id) {
        Id = id;
    }

}
