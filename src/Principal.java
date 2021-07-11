import CoucheReseau.CoucheReseau;
import CoucheTransport.CoucheTransport;
import Paquets.AcquittementPositif;


import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Principal {
    public static void main(String[] args) throws UnsupportedEncodingException
    {
/*        String chemin = "D:\\Project Ecole\\Reseau\\S_lect.txt";
         CoucheTransport transport = new CoucheTransport(chemin);
         transport.lire_de_couche_applicative(chemin);*/
      /* byte val = Byte.parseByte("00001000");
        System.out.println(val);*/
        String chemin = "D:\\Project Ecole\\Reseau\\S_lect.txt";
        CoucheTransport transport = new CoucheTransport(chemin, null);
        /*transport.lire_de_couche_applicative(chemin);*/
        CoucheReseau reseau = new CoucheReseau(transport);
        transport.setCoucheReseau(reseau);;
        Thread threadTransport = new Thread(new CoucheTransport(chemin, reseau));
        Thread threadReseau = new Thread(new CoucheReseau(transport));
        threadTransport.start();
        threadReseau.start();

    }
}
