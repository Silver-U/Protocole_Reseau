package Communication;

import Paquets.Paquet;
import Primitives.Primitive;

import java.io.UnsupportedEncodingException;

public interface IReseau
{
    void lire_de_couche_transport(Primitive primitive) throws UnsupportedEncodingException;
    void ecrire_vers_couche_transport(Primitive primitive) throws UnsupportedEncodingException;
    void lire_de_couche_liaison(String adresseFichier);
    void ecrire_vers_couche_liaison(Paquet paquet);
}
