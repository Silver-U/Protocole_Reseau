package Paquets;

public class Donnee {
    private byte numero_Connexion;
    /* Attribut qui va contenir la donnee de l'application
    * qui envoie des donnees a la couche de transport.La taille maximale des donnees
    * doit etre egale a 128 octets
    * */
    private String donnee;
    private byte typePaquet;
    /*Numero du prochain paquet attendu*/
    private String numeroSeq;
    private char M;
    /*Numero de paquet envoye*/
    private String numeroPaquet;
}
