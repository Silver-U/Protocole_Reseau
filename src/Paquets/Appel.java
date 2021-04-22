package Paquets;

public class Appel extends Paquet{
    private final  String TYPEPAQUET= "00001011";

    public Appel(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
        super(numero_Connexion, adresse_Source, adresse_Dest);
    }
}
