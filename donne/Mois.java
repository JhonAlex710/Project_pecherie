package donne;

import java.util.ArrayList;
/**
 * Un max de 31 jours
 * @author jeanpaul
 * */
public final class Mois {
	private ArrayList<Jours> listedeJours=new ArrayList<>(31);
	private final int numeroMois;
	private final int numeroMoisAnnee;
	private final String lieu;
	private ReleveDateHeureEau marreHauteExceprion;
	private ReleveDateHeureEau marreBasseExceprion;
	private boolean marreHauteExp=true, marreBasseExp=true;
	/*public Mois(int numeroMois) {
		// TODO Auto-generated constructor stub
		this.numeroMois=numeroMois;
		numeroMoisAnnee=0;
		lieu=null;
	}*/
	public Mois(int numeroMois,int numeroMoisAnnee,String lieu) {
		// TODO Auto-generated constructor stub
		this.numeroMois=numeroMois;
		this.numeroMoisAnnee=numeroMoisAnnee;
		this.lieu=lieu;
	}
	/**
	 * Renvoie la liste de tous les jours
	 * */
	public ArrayList<Jours> getListedeJours() {
		return listedeJours;
	}
	/**
	 * renvoie un seul élément de la liste celui de la position X
	 * @param position position dans la liste
	 * @return {@link Jours} jours indiqué
	 * */
	public Jours getListedeJours(int position) throws IndexOutOfBoundsException{
		return listedeJours.get(position);
	}
	/**
	 * Renvoie la position de l"élement X
	 * */
	public int getListedeJours(Jours position) throws IndexOutOfBoundsException {
		int compteur = 0;
		for (Jours jours : listedeJours) {
			if (jours.getNumeroJours()==position.getNumeroJours()) {
				break;
			}else {
				compteur=compteur+1;
			}
		}
		return compteur;
	}
	/**
	 * Renvoie le numero du mois
	 * */
	public int getNumeroMois() {
		return numeroMois;
	}
	/**renvoie l'annee*/
	public int getNumeroMoisAnnee() {
		return numeroMoisAnnee;
	}
	/**Renvoie le lieu*/
	public String getLieu() {
		return lieu;
	}
	/**
	 * Pour insérer se référer addMarreHauteExeceptionnel(marreHauteExceprion)
	 * @return {@link ReleveDateHeureEau}
	 * */
	public ReleveDateHeureEau getMarreHauteExceprion() {
		return marreHauteExceprion;
	}
	/**
	 * Pour insérer se référer addMarreBasseExeceptionnel(marreBasseExceprion)
	 * @return {@link ReleveDateHeureEau}
	 * */
	public ReleveDateHeureEau getMarreBasseExceprion() {
		return marreBasseExceprion; 
	}
	/**
	 * Posiblité de changer la collection
	 * */
	public void setListedeJours(ArrayList<Jours> listedeJours) {
		this.listedeJours = listedeJours;
	}
	/**Ajouter un élément à la collection
	 * @param listedeJours jour à ajouter*/
	public void setListedeJours(Jours listedeJours) {
		this.listedeJours.add(listedeJours);
	}
	/**Ajouter un élément à la collection
	 * @param position dans la liste
	 * @param listedeJours jour à insérer la liste
	 * @throws IndexOutOfBoundsException au cas ou la postion est à l'extérieur de la liste
	 * */
	public void setListedeJours(int position, Jours listedeJours) throws IndexOutOfBoundsException{
		this.listedeJours.set(position,listedeJours);
	}
	/**
	 * On saisi la marée la plus haut relevé 
	 * 1 insertion possible
	 * @param marreHauteExceprion 
	 * */
	public void addMarreHauteExeceptionnel(ReleveDateHeureEau marreHauteExceprion) {
		// TODO Auto-generated method stub
		if (marreHauteExp== true) {
			this.marreHauteExceprion=marreHauteExceprion;
			marreHauteExp=false;
		}
	}
	/**
	 * On saisi le relevé le plus bas.
	 * 1 seul insertion possible
	 * @param marreBasseExceprion
	 * */
	public void addMarreBasseExeceptionnel(ReleveDateHeureEau marreBasseExceprion) {
		// TODO Auto-generated method stub
		if (marreBasseExp==true) {
			this.marreBasseExceprion=marreBasseExceprion;
			marreBasseExp=false;
		}
		
	}
	
}
