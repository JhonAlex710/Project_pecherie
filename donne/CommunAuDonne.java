package donne;

import java.util.ArrayList;
/**
 * Paramètre commun à l'arbre de Donneé Marée
 * @author jeanpaul
 * */
public interface CommunAuDonne {
	/**
	 * Renvoie l'ensemble des données
	 * */
	public ArrayList<CommunAuDonne> getListeDeDonne();
	/**
	 * renvoie une donne à la position 
	 * @param position place dans la liste
	 * @throws IndexOutOfBoundsException attention à la taille
	 * */
	public CommunAuDonne getListeDeDonne(int position) ;
	/***/
	public int getListeDeDonne(CommunAuDonne mois);
	/**
	 * Renvoie le numero de l'instance A prendre comme une date
	 * */
	public int getNumero() ;
	/**
	 * Renvoie la maré Haute extr	ordianore
	 * @return {@link ReleveDateHeureEau}
	 * */
	public ReleveDateHeureEau getMarreHauteExceprion();
	/**
	 * Renvoie la maré Haute extr	ordianore
	 * @return {@link ReleveDateHeureEau}
	 * */
	public ReleveDateHeureEau getMarreBasseExceprion() ;
	/**
	 * Renvoie l'hauteur D'eau moyenne
	 * */
	public double getHauteurEauMoyenne();
	/**
	 * Saisie de l'Hauteur d'eau
	 * @param hauteurEauMoyenne 1 seul fois permise
	 * */
	public void setHauteurEauMoyenne(double hauteurEauMoyenne) ;
	/**
	 * Remplacement de liste intégrale du mois*/
	public void setListeDeDonne(ArrayList<CommunAuDonne> listeDonnee) ;
	/**Ajout d'un élément dans la liste*/
	public void setListeDeDonne(CommunAuDonne listeDonnee) ;
	
	/**Remplacement d'un élément dans la liste*/
	public void setListeDeDonne(int position, CommunAuDonne listeDonnee);
	/**
	 * Ajoute un la marée Exceptionnel
	 * 1 seul cas possible
	 * @param marreHauteExceprion marrée Haute
	 * */
	public void addMarreeHaute(ReleveDateHeureEau marreHauteExceprion) ;
	/**
	 * Ajoute un la marée Exceptionnel
	 * 1 seul cas possible
	 * @param marreHauteExceprion marrée Basse
	 * */
	public void addMarreeBasse(ReleveDateHeureEau marreBasseExceprion);
	/**
	 * Génére un affichage avec le nom de l'instance
	 * */
	public void afficher();
	
}
