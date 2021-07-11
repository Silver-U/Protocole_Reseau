package Communication;

import Primitives.Primitive;
import Primitives.TypePrimitive;

import java.io.UnsupportedEncodingException;

public interface ITransport
{
    void lire_de_couche_reseau(Primitive primitive) throws UnsupportedEncodingException;
    void ecrire_vers_couche_reseau(Primitive primitive) throws UnsupportedEncodingException;
    void lire_de_couche_applicative(String adresse_Slec);
    void ecrire_vers_couche_applicative(Primitive primitive);
}
