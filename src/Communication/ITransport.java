package Communication;

import Primitives.Primitive;

public interface ITransport
{
    void lire_de_couche_reseau(Primitive primitive);
    void ecrire_vers_couche_reseau(Primitive primitive);
    void lire_de_couche_applicative(String adresse_Slec);
    void ecrire_vers_couche_applicative(Primitive primitive);
}
