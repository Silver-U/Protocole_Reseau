package CoucheReseau;

import Communication.IReseau;
import CoucheTransport.CoucheTransport;
import Paquets.*;
import Primitives.Primitive;

import java.io.*;
import java.util.ArrayList;

public class CoucheReseau implements Runnable, IReseau
{
    private CoucheTransport coucheTransport;
    private String[] table_de_Controle;
    String cheminLLect = "D:\\Project Ecole\\Reseau\\L_lec.txt";
    String cheminLecr ="D:\\Project Ecole\\Reseau\\L_ecr.txt";

    public CoucheReseau (CoucheTransport coucheTransport)

    {
        this.coucheTransport = coucheTransport;
    }

    /*Attribue le numero de connection dans la table de controle*/
    void attribuerNumero(){

    }

    //decortique la primitive pour construire le paquet
    public Paquet primitiveVersPaquet(Primitive primitive) throws UnsupportedEncodingException
    {
        Paquet paquet = null;
        switch (primitive.getType())
        {
            case N_CONNECT_req:
                return new Appel(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD());
            case N_DATA_req:
/*                byte[] b = primitive.getMessage().getBytes("UTF-8");*/

                final int maxOctect = 128;
                Donnee donneeTemp;
                ArrayList<Donnee> paquetDonne = new ArrayList<Donnee>();
                int maxlength = primitive.getMessage().length();
                int k = maxOctect -1;
                int nbPaquet = (int)Math.ceil((double)(maxlength / maxOctect));
                if (maxlength > maxOctect)
                {
                    ArrayList<String> message = new ArrayList<String>();

                    for (int i = 0;i < nbPaquet; i += maxOctect)
                    {
                        if (i == nbPaquet - 1)
                        {
                            donneeTemp = new Donnee(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD(), (byte)i, (byte)i, (byte) 0, primitive.getMessage().substring(i, maxlength));
                        }
                        else
                        {
                            donneeTemp = new Donnee(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD(), (byte) i, (byte)(i + 1), (byte) 1, primitive.getMessage().substring(i, k));
                            k += maxOctect;
                        }
                        paquetDonne.add(donneeTemp);
                        return donneeTemp;

                    }
                }
                else
                {
                    donneeTemp = new Donnee(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD(), (byte)0, (byte)0, (byte) 0, primitive.getMessage());
                    paquetDonne.add(donneeTemp);
                }

            /*case N_DATA_ind:
                if (primitive.getMessage())
                break;*/
            case N_CONNECT_ind:
                return new Communication(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD());
            case N_DISCONNECT_ind:
                Indication indication= new Indication(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD());
                indication.setRaison(primitive.getMessage());
                return indication;
            case N_DISCONNECT_req:
                return new Liberation(primitive.getNumeroConnexion(), primitive.getAddS(), primitive.getAddD());
        }

        return null;
    }

    public static Primitive paquetVersPrimitive(Paquet paquet)
    {
        if (paquet.getTYPEPAQUET().isEmpty())
        {
            if (paquet.getSuiteBits() == 1 || paquet.getSuiteBits() == 9)
            {
                return new Primitive(paquet.getNumero_Connexion(), paquet.getAdresse_Source(), paquet.getAdresse_Dest(), "N_DATA_ind", "");
            }
            else
            {
                return new Primitive(paquet.getNumero_Connexion(), paquet.getAdresse_Source(), paquet.getAdresse_Dest(), "N_DATA_req", paquet.getDonnee());
            }
        }
        else
        {
            switch (paquet.getTYPEPAQUET())
            {
                case "00001011":
                    return new Primitive(paquet.getNumero_Connexion(), paquet.getAdresse_Source(), paquet.getAdresse_Dest(), "N_CONNECT_req", "");
                    //appel
                case "00001111":
                    //communication
                    return new Primitive(paquet.getNumero_Connexion(), paquet.getAdresse_Source(), paquet.getAdresse_Dest(), "N_CONNECT_conf", "");
                case "00010011":
                    //indication ou lib
                    if (paquet.getRaison().isEmpty())
                    {
                        return new Primitive(paquet.getNumero_Connexion(), paquet.getAdresse_Source(), paquet.getAdresse_Dest(), "N_DISCONNECT_req", "");
                    }
                    else
                    {
                        return new Primitive(paquet.getNumero_Connexion(), paquet.getAdresse_Source(), paquet.getAdresse_Dest(), "N_DISCONNECT_ind", "");
                    }
            }
        }

        return null;
    }

    public Paquet stringVersPaquet(String ligneFichier)
    {
        String[] parametre = ligneFichier.split("-");

        if (parametre.length == 4)
        {
            switch (parametre[3])
            {
                case "00001011":
                    return new Appel((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0]);
                case "00001111":
                    return new Communication((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0]);
                case "00010011":
                    return new Liberation((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0]);
            }
        }
        else
        {
            if (parametre[3].equals("00010011"))
            {
                Indication indication = new Indication((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0]);
                indication.setRaison(parametre[4]);
                return indication;
            }

            if (parametre.length == 6)
            {
                if (Integer.parseInt(parametre[5]) == 1)
                {
                    return new AcquittementPositif((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0], (parametre[4].getBytes())[0]);
                }
                else
                {
                    return new AcquittementNegatif((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0], (parametre[4].getBytes())[0]);
                }
            }
            else
            {
                return new Donnee((parametre[0].getBytes())[0], (parametre[1].getBytes())[0], (parametre[2].getBytes())[0], (parametre[3].getBytes())[0],(parametre[4].getBytes())[0], (parametre[5].getBytes())[0], parametre[6]);
            }
        }

        return null;

    }

    @Override
    public void run()
    {
        //appel de la methode lire couche liaison
        lire_de_couche_liaison(cheminLLect);
    }

    @java.lang.Override
    public void lire_de_couche_transport(Primitive primitive) throws UnsupportedEncodingException
    {
        //appel de PrimitiveVersPaquet
        // appel methode ecrire_vers_couche_liaison en lui transmettant le paquet
        ecrire_vers_couche_liaison(primitiveVersPaquet(primitive));

    }

    @java.lang.Override
    public void ecrire_vers_couche_transport(Primitive primitive) throws UnsupportedEncodingException
    {
        //appel de la methode lire_de_couche_reseau de l'entitre couche transport en lui transmettant la primitive
        coucheTransport.lire_de_couche_reseau(primitive);

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

        BufferedReader entree;
        String ligne;
        //StringTokenizer token;
        String message;
        File fichierLLect = new File(cheminLLect);
        //Paquet paquet = new Paquet();
        Primitive primitive = null;
        //lire en bouche chaque ligne du fichier
        try
        {
            entree = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(fichierLLect),
                            "cp1252"));
            //format de la ligne est id-appname-addS-add

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /*Ecrire dans le fichier L_ecr correspond a l'envoie du paquet a la couche de liason*/
    @java.lang.Override
    public void ecrire_vers_couche_liaison(Paquet paquet)
    {
        //ecrire dans le fichier lecri
        Primitive primitveConvertir=null;
        PrintWriter ecriture;
        primitveConvertir = paquetVersPrimitive(paquet);

        try {
            File fichierLecr = new File(cheminLecr);
            ecriture = new PrintWriter(fichierLecr,"UTF-16");
            while (primitveConvertir!=null){
                ecriture.println(primitveConvertir.getMessage());
                primitveConvertir = paquetVersPrimitive((Paquet) paquet);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
