package donne;

import java.util.ArrayList;

/**
 * Paramètre commun à l'arbre de Donneé Marée
 * @author jeanpaul
 * */
public interface test /*extends Arbre*/ {
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
	
	
	public ArrayList<CommunAuxDonne> getListeDeDonne2();
	public void setListeDeDonne2(ArrayList<CommunAuxDonne> listedeDonne);
	
}
