package Paquets;

public abstract class Paquet {
    byte numero_Connexion;
    byte adresse_Source;
    byte adresse_Dest;


    public Paquet(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
        this.numero_Connexion = numero_Connexion;
        this.adresse_Source = adresse_Source;
        this.adresse_Dest = adresse_Dest;
    }
}
