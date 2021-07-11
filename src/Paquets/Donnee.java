package Paquets;

public class Donnee extends Paquet
{
    private byte numero_Connexion;
    /* Attribut qui va contenir la donnee de l'application
    * qui envoie des donnees a la couche de transport.La taille maximale des donnees
    * doit etre egale a 128 octets
    * */
    private String donnee;
    private byte typePaquet;
    /*Numero du paquet */
    private byte pr;

    /*Numero du prochain paquet attendu*/
    private byte ps;

    private byte m;

    public Donnee(byte numero_Connexion, byte adresse_Source,byte adresse_Dest, byte ps, byte pr, byte m, String donnee)
    {
        super(numero_Connexion, adresse_Source, adresse_Dest);
        this.ps = ps;
        this.pr = pr;
        this.m = m;
        this.donnee = donnee;
    }

    public byte getPs()
    {
        return ps;
    }

    public String getDonnee()
    {
        return donnee;
    }

    public byte getTypePaquet()
    {
        return typePaquet;
    }

    @Override
    public String toString()
    {
        return numero_Connexion + "-" + adresse_Source + "-" + adresse_Dest + "-" + TYPEPAQUET + "-" + ps + "-" + pr+ "-" + m + "-" + donnee ;
    }
}
