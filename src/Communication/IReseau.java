package Communication;

import Paquets.Paquet;
import Primitives.Primitive;

public interface IReseau
{
    void lire_de_couche_transport(Primitive primitive);
    void ecrire_vers_couche_transport(Primitive primitive);
    void lire_de_couche_liaison(String adresseFichier);
    void ecrire_vers_couche_liaison(Paquet paquet);
}
