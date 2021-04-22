package Paquets;

public class Communication extends Paquet {
    private final  String TYPEPAQUET= "00001111";

    public Communication(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
        super(numero_Connexion, adresse_Source, adresse_Dest);
    }
}
