package Paquets;

public class Liberation extends  Paquet{
    private final  String TYPEPAQUET= "00010011";

    public Liberation(byte numero_Connexion, byte adresse_Source, byte adresse_Dest)
    {
        super(numero_Connexion, adresse_Source, adresse_Dest);
    }

    @Override
    public String getTYPEPAQUET()
    {
        return TYPEPAQUET;
    }

    @Override
    public String toString()
    {
        return numero_Connexion + "-" + adresse_Source + "-" + adresse_Dest + "-" + TYPEPAQUET ;
    }
}
