package CoucheLiaison;

import Paquets.Paquet;

import java.io.File;

public class CoucheLiaison implements  Runnable
{
    private final String cheminLlec = "";
    private  final String cheminLecr = "";

    private File fichierLlec;
    private File fichierLecr;

    public CoucheLiaison ()
    {
        fichierLecr = new File(cheminLecr);
        fichierLlec = new File(cheminLlec);
    }

    public void lire_de_couche_reseau(Paquet paquet)
    {
        //extraire l'adresse source du paquet
        //appel la methode reponse en lui transmettant l'adresse source
        //ecrire la reponse dans Llec
    }

/*    Verifier le numero de paquet pour envoyer soit un acquittement
    * positif ou negatif.Pour ce faire, il faut verifier le numero du paquet
    * avec une valeur generer aleatoirement
    *  */
    public void genererAcquitement()
    {
        //switch pour determiner l'acquitement
        //construire le paquet acquitement correspondant
        //ecrire dans llec
    }


    /*Permet de generer aleatoirement une valeur*/
    public Paquet reponse(byte adresseSource)
    {
        //switch pour deteminer la reponse(page 10), construire le paquet a l'interieur
       return null;
    }

    /*On copie la reponse envoyee a ER dans le fichier L_ecr*/
    public void ecrireDansFichierL(){

    }
    /*Repondre suite a la reception d'un paquet*/
    public void envoyerReponse(){

    }

    @Override
    public void run() {

    }
}
