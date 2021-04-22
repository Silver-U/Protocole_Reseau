package CoucheReseau;

import Communication.IReseau;
import CoucheTransport.CoucheTransport;
import Paquets.Paquet;
import Primitives.Primitive;

public class CoucheReseau implements Runnable, IReseau
{
    private CoucheTransport coucheTransport;
    private String[] table_de_Controle;

    public CoucheReseau (CoucheTransport coucheTransport)
    {
        this.coucheTransport = coucheTransport;
    }

    /*Attribue le numero de connection dans la table de controle*/
    void attribuerNumero(){

    }
    /*Construis le paquet d'appel avant de l'envoyer*/
    void construirePaquetAppel(){

    }

    //decortique la primitive pour construire le paquet
    public Paquet primitiveVersPaquet(Primitive primitive)
    {
        return null;
    }

    public Primitive paquetVersPrimitive(Paquet paquet)
    {
        return null;
    }

    public Paquet stringVersPaquet(String ligneFichier)
    {
        //decortique et construire le bon paquet
        return null;
    }

    @Override
    public void run()
    {
        //appel de la methode lire couche liaison
    }

    @java.lang.Override
    public void lire_de_couche_transport(Primitive primitive)
    {
        //appel de PrimitiveVersPaquet
        // appel methode ecrire_vers_couche_liaison en lui transmettant le paquet
    }

    @java.lang.Override
    public void ecrire_vers_couche_transport(Primitive primitive)
    {
        //appel de la methode lire_de_couche_reseau de l'entitre couche transport en lui transmettant la primitive
    }

    @java.lang.Override
    public void lire_de_couche_liaison(String adresseFichier)
    {
        //lire llec en boucle, et decortique chaque ligne
        //reconstituer le paquet en appelant la methode stringVersPaquet
        //switch sur le type du paquet pour savoir quel reaction on doit faire
        //reaction possible : reemettre le paquet, mettre fin a la connexion, etc
        //convertir le paquet en primitive
        //selon les cas ecrire_vers_couche_transport en lui transmettant la primitive
    }

    /*Ecrire dans le fichier L_ecr correspond a l'envoie du paquet a la couche de liason*/
    @java.lang.Override
    public void ecrire_vers_couche_liaison(Paquet paquet)
    {
        //ecrire dans le fichier lecri
        //
    }
}
