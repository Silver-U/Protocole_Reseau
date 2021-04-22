package Paquets;

public class AcquittementNegatif extends Paquet{
    //private int numeroConnexion;
    private byte typePaquet ;
    private String numeroPaquet;
    private final  byte suiteBits= 9;

    public AcquittementNegatif(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
        super(numero_Connexion, adresse_Source, adresse_Dest);
    }
}
