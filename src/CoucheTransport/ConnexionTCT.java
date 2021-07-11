package CoucheTransport;

public class ConnexionTCT
{
    private  Etat etat;
    private byte numeroConnexion;
    private byte addD;// todo utiliser avec des bytes
    private byte addS;// todo utiliser avec des bytes

    public ConnexionTCT (byte numeroConnexion, byte adresseSource, byte adresseDestination, String etat)
    {
        addD = adresseDestination;
        addS = adresseSource;
        this.etat = Etat.valueOf(etat);
        this.numeroConnexion = numeroConnexion;
    }


    public void setEtat(Etat etat)
    {
        this.etat = etat;
    }

    public int getAddD()
    {
        return addD;
    }

    public int getAddS()
    {
        return addS;
    }

    public Etat getEtat()
    {
        return etat;
    }

    public int getNumeroConnexion()
    {
        return numeroConnexion;
    }

    @Override
    public String toString()
    {
        return "ConnexionTCT{" +
                "etat=" + etat +
                ", numeroConnexion=" + numeroConnexion +
                ", addD=" + addD +
                ", addS=" + addS +
                '}';
    }
}
