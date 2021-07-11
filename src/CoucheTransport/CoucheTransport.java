package CoucheTransport;

import Communication.ITransport;
import CoucheReseau.CoucheReseau;
import Primitives.Primitive;
import Primitives.TypePrimitive;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CoucheTransport implements Runnable, ITransport
{
    /*Table controle de la couche transport, va contenir les etats(en attente,en cours de connexion)
    * numero de connexion
    * */
    private final String cheminSlec = "D:\\Project Ecole\\Reseau\\L_lec.txt";
    private  final String cheminSecr = "D:\\Project Ecole\\Reseau\\S_ecr.txt";
    private  CoucheReseau coucheReseau;
    private File fichierSLect ;
    private File fichierSecr ;

    public CoucheTransport (String cheminSlec, CoucheReseau coucheReseau)
    {
        fichierSecr = new File(cheminSecr);
        fichierSLect = new File(cheminSlec);
        this.coucheReseau = coucheReseau;
    }

    public void setCoucheReseau(CoucheReseau coucheReseau)
    {
        this.coucheReseau = coucheReseau;
    }

    private ArrayList<ConnexionTCT> table_control = new ArrayList<ConnexionTCT>();

    /*Verifie si la demande(l'identifiant de l'app) de l'application se trouve dans la table de controle*/
    public boolean verifierApplication(int id)
    {
        boolean retour = false;

       if(table_control.isEmpty())
       {
            return false;
       }
       else
       {
           for(int i = 0; i<table_control.size(); i++)
           {
               if(table_control.get(i).getNumeroConnexion() == id)
               {
                   retour = true;
               }
               else
               {
                   retour = false;
               }
           }

           return retour;
       }

    }

    /*Genere les adresses sources et destinataires a travers un processus aleatoire*/
    public int[] genererAdresse()
    {
        int[] adresse = new int[2];
        adresse[0] = (int) (Math.random() * 250);

        do
        {
            adresse[1] = (int) (Math.random() * 250);
        }
        while (adresse[0] == adresse[1]);

        return adresse;
    }



    public String primitiveVersString(Primitive primitive)
    {
        return primitive.getMessage();
    }

    @Override
    public void run()
    {
        //appeler la methode lire_de_couche_applicative
        lire_de_couche_applicative(cheminSlec);
        try
        {
            Thread.sleep(1000);
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        //fermer le fichier
    }

    @java.lang.Override
    public void lire_de_couche_reseau(Primitive primitive) throws UnsupportedEncodingException
    {
        //interprete la primitive
        //ouvrir/fermer/modifier les ressources (ligne dans la table TCT)
        //appel une methode ecrire_vers_couche_applicative en lui transmettant la primitive

        ecrire_vers_couche_applicative(primitive);

        switch (primitive.getType())
        {
            case N_CONNECT_req:
                break;
            case N_DATA_ind:
                coucheReseau.lire_de_couche_transport(primitive);
                break;
            case N_DATA_req:
                coucheReseau.lire_de_couche_transport(primitive);
                break;
            case N_CONNECT_ind:
                coucheReseau.lire_de_couche_transport(primitive);
                break;
            case N_CONNECT_conf:
                coucheReseau.lire_de_couche_transport(primitive);
                break;
            case N_CONNECT_resp:
                coucheReseau.lire_de_couche_transport(primitive);
                break;

        }
    }

    @java.lang.Override
    public void ecrire_vers_couche_reseau(Primitive primitive) throws UnsupportedEncodingException
    {
        //switch sur le type de la primitive
        //appel de la methode lire_de_couche_transport, en lui transmettant la primitive
        coucheReseau.lire_de_couche_transport(primitive);
    }

    //lire le fichier Slec
    @java.lang.Override
    public void lire_de_couche_applicative(String adresse_Slec)
    {
        BufferedReader entree;
        String ligne;
        StringTokenizer token;
        String message ;
        byte id;
        String appName;
        fichierSLect = new File(adresse_Slec);

        //lire en bouche chaque ligne du fichier
        try {

            entree = new BufferedReader(new FileReader("D:\\Project Ecole\\Reseau\\S_lect.txt"));
            // Read first line
            ligne = entree.readLine();
            //format de la ligne est id-appname-addS-addD-typePrimitive-message

            while (ligne!=null)
            {
             token= new StringTokenizer(ligne,"-");
             //id = (byte) genererNumeroConnexion();
                id = (byte) Integer.parseInt(token.nextToken());
             appName = token.nextToken();
             byte addS = (byte) Integer.parseInt(token.nextToken());
             byte addD = (byte) Integer.parseInt(token.nextToken());
             String typePrimitive = token.nextToken();
             message = token.nextToken();
             ligne = entree.readLine();
             Primitive primitive = new Primitive(id, addS, addD, typePrimitive, message);


             if(!verifierApplication(id))
             {
                 if (typePrimitive.equals(TypePrimitive.N_CONNECT_req.name()))
                 {
                     ConnexionTCT connexionTCT = new ConnexionTCT(id, addS,addD, "EN_ATTENTE_DE_COMFIRMATION_ETABLISSEMENT");
                     table_control.add(connexionTCT);
                 }
             }
             else
             {
                 getConnexion(id).setEtat(Etat.CONNEXION_ETABLIE);

                 if (typePrimitive.equals(TypePrimitive.N_DATA_req.name()))
                 {
                     if (getConnexion(id).getEtat() == Etat.CONNEXION_ETABLIE)
                     {
                         table_control.size();
                         ecrire_vers_couche_reseau(primitive);
                     }
                 }
                 else if(typePrimitive.equals(TypePrimitive.N_DISCONNECT_req.name()))
                 {
                        table_control.remove(getConnexion(id));
                 }
             }

            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //fermer le fichier
        //fichierSLect.

        // appel la methode messageVersPrimitive en lui transmettant le message
        //methode verifie application
        //ifelse pour creation applicaiton dans le tableau
        //appel la methode ecrire vers la couche reseau en lui transmettant la primitive
    }

    @java.lang.Override
    public void ecrire_vers_couche_applicative(Primitive primitive)
    {
        // appel de la methode primitiveVersString
        //stocke dans Secr

        String primitveConvertir;
        PrintWriter ecriture;
        primitveConvertir = primitiveVersString(primitive);

        try {
            ecriture = new PrintWriter(fichierSecr,"UTF-16");
            while (primitveConvertir!=null){
                ecriture.println(primitveConvertir);
                primitveConvertir = primitiveVersString(primitive);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }




    }

    public int genererNumeroConnexion()
    {
        boolean presence = false;
        int numeroConnexion = 0;
        do
        {
            numeroConnexion = (int) (Math.random() * 250);

            if (getConnexion(numeroConnexion) != null)
            {
                presence = true;
            }
        }
        while (presence);

        return numeroConnexion;
    }

    private ConnexionTCT getConnexion(int numeroConnexion)
    {
        ConnexionTCT connexion = null;

        for (int i = 0; i < table_control.size(); i++)
        {
            if (table_control.get(i).getNumeroConnexion() == numeroConnexion)
            {
                connexion = table_control.get(i);
                break;
            }
        }

        return  connexion;
    }
}
