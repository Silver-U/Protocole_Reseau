package Paquets;

public class AcquittementPositif extends Paquet{
    //private int numeroConnexion;
    private String typePaquet ;
    private String numeroPaquet;
    private final  byte suiteBits=1;
    private byte pr;

    public AcquittementPositif(byte numero_Connexion, byte adresse_Source, byte adresse_Dest, byte pr)
    {
        super(numero_Connexion, adresse_Source, adresse_Dest);
        this.pr = pr;
    }

    public byte getSuiteBits()
    {
        return suiteBits;
    }

    @Override
    public String toString()
    {
        return numero_Connexion + "-" + adresse_Source + "-" + adresse_Dest + "-" + TYPEPAQUET + "-" + pr + suiteBits;
    }
}
