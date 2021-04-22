package CoucheTransport;

import Communication.ITransport;
import CoucheReseau.CoucheReseau;
import Primitives.Primitive;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CoucheTransport implements Runnable, ITransport
{
    /*Table controle de la couche transport, va contenir les etats(en attente,en cours de connexion)
    * numero de connexion
    * */
    private String cheminSlec = "";
    private  final String cheminSecr = "";
    private  CoucheReseau coucheReseau;
    private File fichierSLect;
    private File fichierSecr;

    public CoucheTransport (String cheminSlec)
    {
        fichierSecr = new File(cheminSecr);
        this.cheminSlec = cheminSlec;
    }

    public void setCoucheReseau(CoucheReseau coucheReseau)
    {
        this.coucheReseau = coucheReseau;
    }

    private List<ConnexionTCT> table_control;
    /*Verifie si la demande(l'identifiant de l'app) de l'application se trouve dans la table de controle*/
    public boolean verifierApplication(int id)
    {
        ConnexionTCT tct =null;
       if(table_control.size()==0){
           table_control = new ArrayList<ConnexionTCT>();
           tct = new ConnexionTCT();
           tct.setId(id);
           tct.getETAT1();
           table_control.add(tct);
       }
           for(int i=0;i<table_control.size();i++){
               if(table_control.get(i).getId()==id){
                   break;
               }else {
                   tct = new ConnexionTCT();
                   table_control.add(tct);
               }
           }


       return true;
    }

    //identifie la primitive en fonction du message recupere dans Slec
    public Primitive messageVersPrimitive(String message)
    {
        //convertir en primitive
        return null;
    }


    /*Genere les adresses sources et destinataires a travers un processus aleatoire*/
    public void genererAdresses()
    {

    }

    /*Demande de connection du processus ET a l'entite distante via
    * la primitive N_Connect_req
    * */
    public void demanderConnection(int adrSrc, int adrDest, Primitive primitive)
    {
        //
    }

    public String primitiveVersString(Primitive primitive)
    {
        //convertir la primitive en String
        return null;
    }

    @Override
    public void run()
    {
        //appeler la methode lire_de_couche_applicative
    }

    @java.lang.Override
    public void lire_de_couche_reseau(Primitive primitive)
    {
        //interprete la primitive
        //ouvrir/fermer/modifier les ressources (ligne dans la table TCT)
        //appel une methode ecrire_vers_couche_applicative en lui transmettant la primitive
    }

    @java.lang.Override
    public void ecrire_vers_couche_reseau(Primitive primitive)
    {
        //switch sur le type de la primitive
        //appel de la methode lire_de_couche_transport, en lui transmettant la primitive

    }

    //lire le fichier Slec
    @java.lang.Override
    public void lire_de_couche_applicative(String adresse_Slec)
    {
        BufferedReader entree;
        String ligne;
        StringTokenizer token;
        String message ;
        int id;
        String appName;
        fichierSLect = new File(adresse_Slec);
        //lire en bouche chaque ligne du fichier
        try {
            entree = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fichierSLect),
                            "cp1252"));
            ligne= entree.readLine();
            while (ligne!=null){
             token= new StringTokenizer(ligne,"-");
             id = Integer.parseInt(token.nextToken());
             appName = token.nextToken();
             message = token.nextToken();
             ligne = entree.readLine();
             messageVersPrimitive(message);
             if(verifierApplication(id)){

                }else{

             }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
    }
}
