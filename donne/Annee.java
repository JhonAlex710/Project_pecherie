package donne;

import java.util.ArrayList;
/**
 * Une liste de 12 mois
 * @author jeanpaul
 * */
public final class Annee {
	private boolean MarreeBasseExp = true;
	private boolean MarreeHauteExp = true;
	private ArrayList<Mois> listeDeMois=new ArrayList<>(12);
	private final int numeroAnne;
	private final String lieu;
	private ReleveDateHeureEau marreHauteExceprion;
	private ReleveDateHeureEau marreBasseExceprion;
	public Annee(int numeroAnne) {
		// TODO Auto-generated constructor stub
		this.numeroAnne=numeroAnne;
		lieu="";
	}
	public Annee(int numeroAnne,String lieu) {
		// TODO Auto-generated constructor stub
		this.numeroAnne=numeroAnne;
		this.lieu=lieu;
	}
	/**
	 * Renvoie l'ensemble de la liste
	 * */
	public ArrayList<Mois> getListeDeMois() {
		return listeDeMois;
	}
	/**Renvoie le mois à la postion X*/
	public Mois getListeDeMois(int position) throws IndexOutOfBoundsException {
		return listeDeMois.get(position);
	}
	/**Renvoie la position du mois Y */
	public int getListeDeMois(Mois mois)  throws IndexOutOfBoundsException {
		int compteur=0;
		for (Mois mois2 : listeDeMois) {
			if (mois.getNumeroMois()==mois2.getNumeroMois()) {
				break;
			} else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	/**
	 * Renvoie le numéro de l'année
	 * */
	public int getNumeroAnne() {
		return numeroAnne;
	}
	/**
	 * @return the lieu
	 */
	public String getLieu() {
		return lieu;
	}
	/**
	 * renvoie le relevée de la maréé haute
	 * Equivalent du set = addMarreeHaute(ReleveDateHeureEau)
	 * @return {@link ReleveDateHeureEau} marreHauteExceprion
	 * */
	public ReleveDateHeureEau getMarreHauteExceprion() {
		return marreHauteExceprion;
	}
	/**
	 * renvoie le relevée de la maréé haute
	 * Equivalent du set = addMarreeBasse(ReleveDateHeureEau)
	 * @return {@link ReleveDateHeureEau} marreBasseExceprion
	 * */
	public ReleveDateHeureEau getMarreBasseExceprion() {
		return marreBasseExceprion;
	}
	/**
	 * Remplacement de liste intégrale du mois*/
	public void setListeDeMois(ArrayList<Mois> listeDeMois) {
		this.listeDeMois = listeDeMois;
	}
	/**Ajout d'un élément dans la liste*/
	public void setListeDeMois(Mois listeDeMois) {
		this.listeDeMois.add(listeDeMois);
	}
	/**Remplacement d'un élément dans la liste*/
	public void setListeDeMois(int position, Mois listeDeMois) throws IndexOutOfBoundsException{
		this.listeDeMois.set(position, listeDeMois);
	}
	/**
	 * Ajoute un la marée Exceptionnel
	 * 1 seul cas possible
	 * @param marreHauteExceprion marrée Haute
	 * */
	public void addMarreeHaute(ReleveDateHeureEau marreHauteExceprion) {
		// TODO Auto-generated method stub
		if (MarreeHauteExp==true) {
			this.marreHauteExceprion=marreHauteExceprion;
			MarreeHauteExp=false;
		}
	}
	/**
	 * Ajoute un la marée Exceptionnel
	 * 1 seul cas possible
	 * @param marreHauteExceprion marrée Basse
	 * */
	public void addMarreeBasse(ReleveDateHeureEau marreBasseExceprion) {
		// TODO Auto-generated method stub
		if (MarreeBasseExp==true) {
			this.marreBasseExceprion=marreBasseExceprion;
			MarreeBasseExp=false;
		}
	}
	
}
