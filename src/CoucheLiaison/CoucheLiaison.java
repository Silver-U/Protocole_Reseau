package CoucheLiaison;

import CoucheReseau.CoucheReseau;
import Paquets.*;
import Primitives.Primitive;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Objects;

public class CoucheLiaison
{
    private final String cheminLlec = "D:\\Project Ecole\\Reseau\\L_lec.txt";
    private  final String cheminLecr = "D:\\Project Ecole\\Reseau\\L_ecr.txt";

    private File fichierLlec;
    private File fichierLecr;

    public CoucheLiaison ()
    {
        fichierLecr = new File(cheminLecr);
        fichierLlec = new File(cheminLlec);
    }

    public void lire_de_couche_reseau(Paquet paquet) throws FileNotFoundException, UnsupportedEncodingException
    {
        //extraire l'adresse source du paquet
        //appel la methode reponse en lui transmettant l'adresse source
        //ecrire la reponse dans Llec
        ecrireDansFichierL(reponse(Objects.requireNonNull(CoucheReseau.paquetVersPrimitive(paquet))));

    }

/*    Verifier le numero de paquet pour envoyer soit un acquittement
    * positif ou negatif.Pour ce faire, il faut verifier le numero du paquet
    * avec une valeur generer aleatoirement
    *  */
    public Paquet genererAcquitement(Donnee donnee) throws FileNotFoundException, UnsupportedEncodingException
    {
        //switch pour determiner l'acquitement
        //construire le paquet acquitement correspondant
        //ecrire dans llec

        if ((donnee.getAdresse_Source() % 15) == 0)
        {
            // pas d'acquitement
        }
        else if (donnee.getPs() == Math.random() * 8)
        {
            AcquittementNegatif acquittementNegatif = new AcquittementNegatif(donnee.getNumero_Connexion(), donnee.getAdresse_Source(), donnee.getAdresse_Dest(), donnee.getPs());
            ecrireDansFichierL(acquittementNegatif);
            return acquittementNegatif;
        }
        else
        {
             AcquittementPositif acquittementPositif = new AcquittementPositif(donnee.getNumero_Connexion(), donnee.getAdresse_Source(), donnee.getAdresse_Dest(), (byte) (donnee.getPs() + (byte) 1));
             ecrireDansFichierL(acquittementPositif);
             return acquittementPositif;
        }

        return null;
    }


    /*Repondre suite a la reception d'un paquet*/
    public Paquet reponse(Primitive primitive) throws FileNotFoundException, UnsupportedEncodingException
    {
        Indication indication;
        //switch pour deteminer la reponse(page 10), construire le paquet a l'interieur
        if ((primitive.getAddS() % 19) == 0)
        {
            // pas de reponse
        }
        else if ((primitive.getAddS() % 13) == 0)
        {
            indication = new Indication(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD());
            indication.setRaison(indication.getRaisons()[1]);
            ecrireDansFichierL(indication);
            return indication;
        }
        else
        {
            Communication communication = new Communication(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD());
            ecrireDansFichierL(communication);
            return communication;
        }

       return null;
    }

    /*On copie la reponse envoyee a ER dans le fichier L_lec*/
    public void ecrireDansFichierL(Paquet paquet) throws FileNotFoundException, UnsupportedEncodingException
    {
        PrintWriter ecriture;

        ecriture = new PrintWriter(fichierLecr,"UTF-16");
        ecriture.println(paquet.toString());

    }
}
