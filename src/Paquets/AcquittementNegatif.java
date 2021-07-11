package Paquets;

public class AcquittementNegatif extends Paquet{
    //private int numeroConnexion;
    private byte typePaquet ;
    private String numeroPaquet;
    private final  byte suiteBits= 9;
    private byte pr;

    public AcquittementNegatif(byte numero_Connexion, byte adresse_Source, byte adresse_Dest, byte pr)
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
        return numero_Connexion + "-" + adresse_Source + "-" + adresse_Dest + "-" + TYPEPAQUET + "-" + pr + suiteBits ;
    }
}
