package Paquets;

public abstract class Paquet {
    byte numero_Connexion;
    byte adresse_Source;
    byte adresse_Dest;
    String TYPEPAQUET;
    String raison;
    byte suiteBits;
    String donnee;
    byte pr;
    public Paquet()
    {

    }

    public Paquet(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
        this.numero_Connexion = numero_Connexion;
        this.adresse_Source = adresse_Source;
        this.adresse_Dest = adresse_Dest;
    }

    public byte getAdresse_Source()
    {
        return adresse_Source;
    }

    public String getTYPEPAQUET()
    {
        return TYPEPAQUET;
    }

    public byte getAdresse_Dest()
    {
        return adresse_Dest;
    }

    public byte getNumero_Connexion()
    {
        return numero_Connexion;
    }

    public String getRaison()
    {
        return raison;
    }

    public byte getSuiteBits()
    {
        return suiteBits;
    }

    public String getDonnee()
    {
        return donnee;
    }

    public byte getPr()
    {
        return pr;
    }

    @Override
    public String toString()
    {
        return numero_Connexion + "-" + adresse_Source + "-" + adresse_Dest;
    }
}
