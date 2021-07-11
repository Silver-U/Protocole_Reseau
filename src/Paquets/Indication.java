package Paquets;

public class Indication extends Paquet
{
   private final  String[] raisons=new String[]{"00000001","00000010"};
   private final  String TYPEPAQUET= "00010011";
   private String raison;


   public Indication(byte numero_Connexion, byte adresse_Source, byte adresse_Dest) {
      super(numero_Connexion, adresse_Source, adresse_Dest);
   }

   public void setRaison(String raison)
   {
      this.raison = raison;
   }

   public String[] getRaisons()
   {
      return raisons;
   }

   public String getTYPEPAQUET()
   {
      return TYPEPAQUET;
   }

   @Override
   public String toString()
   {
      return numero_Connexion + "-" + adresse_Source + "-" + adresse_Dest + "-" + TYPEPAQUET + "-" + raison ;
   }
}
