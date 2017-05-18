package calcul;

import java.util.ArrayList;

import donne.NiveauMarin;

/**
 * @author jeanpaul
 * */
public class CourbeDeGausse {
	private final ArrayList<NiveauMarin> donnneNiveauMaree;
	//private double fonctionDeGausse;
	/**
	 * On receptionne les donnes marine à destion de toute la classe
	 * @param donnneNiveauMaree ensemble du fichier Donnees_Niveau Marin_BZH
	 * */
	public CourbeDeGausse(ArrayList<NiveauMarin> donnneNiveauMaree) {
		// TODO Auto-generated constructor stub
		this.donnneNiveauMaree=donnneNiveauMaree;
	}
	
	public void calculDeGausse() {
		for (NiveauMarin niveauMarin : donnneNiveauMaree) {
			
		}
	}
	/**
	 * Fonction de gausse
	 * @param x variable
	 * @param o écart type
	 * @param u espérence mathèmatique
	 * @return {@link Double} resultat de la donction de gausse
	 * */
	private double fonctionDeGausse (double x,double o, double u){
		return (1/(o*Math.sqrt(2*Math.PI)))*Math.exp(-1*(Math.pow((x-u), 2)/2*Math.pow(o, 2)));
	}
	/**
	 * calcul la largeur à mi vie de la fonction de gausse
	 * est environ égake à 2.3548o
	 * @param o écart type
	 * */
	private double largeurAMisVis(double o){
		return 2*(Math.sqrt(2*Math.log(2))*o);
	}
}
