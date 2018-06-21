import iut.algo.*;

/** Programme produit
  * date    : le 07/09/2017
  * @author : Schneider
  */
public class Calculproduit
{
   public static void main(String[] arg)
   {
      /*----------------*/
      /* Données        */
      /*----------------*/

      int val1, val2;

      int resultat;

      /*----------------*/
      /* Instructions   */
      /*----------------*/
      System.out.println("quelle est la première valeur ?"); 
      val1 = Clavier.lire_int() ;
      System.out.println("quelle est la deuxiéme valeur ?");
      val2 = Clavier.lire_int();

      resultat = val1 * val2;

      System.out.println ("Le produit de " + val1 + " et " + val2 + " donnent " + resultat );
     }
}
