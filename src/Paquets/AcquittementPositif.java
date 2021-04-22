package Paquets;

public class AcquittementPositif extends Paquet{
    //private int numeroConnexion;
    private String typePaquet ;
    private String numeroPaquet;
    private final  byte suiteBits=1;

    public AcquittementPositif(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
        super(numero_Connexion, adresse_Source, adresse_Dest);
    }
}
