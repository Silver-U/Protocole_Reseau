package Primitives;

public class Primitive
{
    private TypePrimitive type;
    private byte addS;
    private byte addD;
    private byte numeroConnexion;
    private String message;

    public Primitive(byte numeroConnexion, byte adresseSource, byte adresseDestination, String typeprimitive, String message)
    {
        type = TypePrimitive.valueOf(typeprimitive);
        addD = adresseDestination;
        addS = adresseSource;
        this.numeroConnexion = numeroConnexion;
        this.message = message;
    }

    public byte getAddS()
    {
        return addS;
    }

    public byte getAddD()
    {
        return addD;
    }

    public byte getNumeroConnexion()
    {
        return numeroConnexion;
    }

    public TypePrimitive getType()
    {
        return type;
    }

    public String getMessage()
    {
        return message;
    }
}
